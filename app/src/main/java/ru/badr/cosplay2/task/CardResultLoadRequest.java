package ru.badr.cosplay2.task;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.CardImage;
import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.Badge;
import ru.badr.cosplay2.api.cards.info.Field;
import ru.badr.cosplay2.api.cards.info.GetCardResult;
import ru.badr.cosplay2.api.cards.info.InfoCard;
import ru.badr.cosplay2.api.cards.info.ReqValue;
import ru.badr.cosplay2.api.cards.info.json.ReqSectionHolder;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 17:54
 */
public class CardResultLoadRequest extends TaskRequest<ReqSectionHolder.List> {
    private Context mContext;
    private Card mCard;

    public CardResultLoadRequest(Context context, Card card) {
        super(ReqSectionHolder.List.class);
        this.mContext = context;
        this.mCard = card;
    }

    @Override
    public ReqSectionHolder.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        GetCardResult result = container.getCosplay2RestService().getCard(mCard.getId());
        ReqSectionHolder.List list = new ReqSectionHolder.List();
        if (result != null) {
            List<Field> fields = result.getFields();
            InfoCard defCard = result.getCard();
            List<ReqValue> reqValues = result.getReqValues();
            List<User> users = result.getUsers();
            if (fields != null && reqValues != null) {
                ReqSectionHolder reqSectionHolder = null;
                Iterator<ReqValue> reqValuesIterator = reqValues.iterator();
                while (reqValuesIterator.hasNext()) {
                    ReqValue value = reqValuesIterator.next();
                    boolean found = false;
                    for (int i = 0, size = fields.size(); i < size && !found; i++) {
                        Field field = fields.get(i);
                        if (value.getFieldId() == field.getId()) {
                            found = true;
                            if (reqSectionHolder == null) {
                                reqSectionHolder = new ReqSectionHolder();
                                reqSectionHolder.setId(value.getRequestSectionId());
                                Badge badge = getBadgeIfHolds(value.getRequestSectionId(), result.getBadges());
                                if (badge != null) {
                                    reqSectionHolder.setTitle(badge.getCard());
                                }
                            } else {
                                if (reqSectionHolder.getId() != value.getRequestSectionId()) {
                                    Badge newBadge = getBadgeIfHolds(value.getRequestSectionId(), result.getBadges());
                                    if (!TextUtils.isEmpty(reqSectionHolder.getTitle()) || newBadge != null) {
                                        list.add(reqSectionHolder);
                                        reqSectionHolder = new ReqSectionHolder();
                                        reqSectionHolder.setId(value.getRequestSectionId());
                                        if (newBadge != null) {
                                            reqSectionHolder.setTitle(newBadge.getCard());
                                        }
                                    } else {
                                        reqSectionHolder.setId(value.getRequestSectionId());
                                    }
                                }
                            }
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
                                if (!TextUtils.isEmpty(value.getValue())) {
                                    long userId = Long.valueOf(value.getValue());
                                    for (User user : users) {
                                        if (user.getId() == userId) {
                                            holder.setValue(user);
                                            break;
                                        }
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
                            reqSectionHolder.getList().add(holder);
                            reqValuesIterator.remove();
                            break;
                        }
                    }
                }
                if (reqSectionHolder != null) {
                    list.add(reqSectionHolder);
                }
            }
        }
        return list;
    }

    private Badge getBadgeIfHolds(long sectionId, List<Badge> badges) {
        if (badges != null && !badges.isEmpty()) {
            for (Badge badge : badges) {
                if (badge.getSectionId() == sectionId) {
                    return badge;
                }
            }
        }
        return null;
    }
}
