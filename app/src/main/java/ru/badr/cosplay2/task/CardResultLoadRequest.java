package ru.badr.cosplay2.task;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.CardImage;
import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.Field;
import ru.badr.cosplay2.api.cards.info.GetCardResult;
import ru.badr.cosplay2.api.cards.info.InfoCard;
import ru.badr.cosplay2.api.cards.info.ReqValue;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 17:54
 */
public class CardResultLoadRequest extends TaskRequest<ReqValuesHolder.List> {
    private Context mContext;
    private Card mCard;

    public CardResultLoadRequest(Context context, Card card) {
        super(ReqValuesHolder.List.class);
        this.mContext = context;
        this.mCard = card;
    }

    @Override
    public ReqValuesHolder.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        GetCardResult result = container.getCosplay2RestService().getCard(mCard.getId());
        ReqValuesHolder.List list = new ReqValuesHolder.List();
        if (result != null) {
            List<Field> fields = result.getFields();
            InfoCard defCard = result.getCard();
            List<ReqValue> reqValues = result.getReqValues();
            List<User> users = result.getUsers();
            if (fields != null && reqValues != null) {
                for (Field field : fields) {
                    Iterator<ReqValue> reqValuesIterator = reqValues.iterator();
                    while (reqValuesIterator.hasNext()) {
                        ReqValue value = reqValuesIterator.next();
                        if (value.getFieldId() == field.getId()) {
                            ReqValuesHolder holder = new ReqValuesHolder();
                            holder.setTitle(field.getTitle());
                            if ("text".equals(field.getType())) {
                                holder.setType(ReqValuesHolder.Types.text);
                                holder.setValue(value.getValue());
                            } else if ("link".equals(field.getType())) {
                                holder.setType(ReqValuesHolder.Types.link);
                                holder.setValue(value.getValue());
                            } else if ("user".equals(field.getType())) {
                                holder.setType(ReqValuesHolder.Types.user);
                                long userId = Long.valueOf(value.getValue());
                                for (User user : users) {
                                    if (user.getId() == userId) {
                                        holder.setValue(user);
                                        break;
                                    }
                                }
                            } else if ("image".equals(field.getType())) {
                                holder.setType(ReqValuesHolder.Types.image);
                                CardImage cardImage = new Gson().fromJson(value.getValue(), CardImage.class);
                                InfoCard card = new InfoCard(defCard);
                                card.setImage(cardImage);
                                holder.setValue(card);
                            } else {
                                System.out.println("new undefined field.type = " + field.getTitle());
                                break;
                            }
                            list.add(holder);
                            reqValuesIterator.remove();
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }
}
