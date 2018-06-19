package ru.badr.cosplay2.task;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retry.DefaultRetryPolicy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ru.badr.base.CurrentTimeTask;
import ru.badr.base.util.json.DateLongSerializer;
import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.list.ListCard;
import ru.badr.cosplay2.api.cards.list.TopicsAndCards;
import ru.badr.cosplay2.api.schedule.Plan;
import ru.badr.cosplay2.api.schedule.ScheduleNode;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by Badr on 16.11.2015.
 */
public class ScheduleLoadRequest extends TaskRequest<ScheduleNode.List> {
    private Context mContext;

    public ScheduleLoadRequest(Context context) {
        super(ScheduleNode.List.class);
        this.mContext = context.getApplicationContext();
        setRetryPolicy(new DefaultRetryPolicy(1, DefaultRetryPolicy.DEFAULT_DELAY_BEFORE_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public ScheduleNode.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        try {
            Utils.APP_START_TIME = new CurrentTimeTask(mContext).loadData();
            if (!Utils.isTimeHasCome()) {
                return new ScheduleNode.List();
            }
        } catch (Exception ignored) {
        }
        Plan result = container.getCosplay2RestService().getSchedule().execute().body();

        TopicsAndCards tac = container.getCosplay2RestService().getTopicsAndCards().execute().body();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateLongSerializer());
        ScheduleNode.List resultList = gsonBuilder.create().fromJson(result.getPlan(), ScheduleNode.List.class);

        ScheduleNode.List list = new ScheduleNode.List();
        if (resultList != null && !resultList.isEmpty()) {
            list.addAll(parseNodes(resultList, tac != null ? tac.getCards() : new ArrayList<ListCard>()));
        }
        return list;
    }

    private Collection<? extends ScheduleNode> parseNodes(ScheduleNode.List list, List<ListCard> cards) {
        ScheduleNode.List resultList = new ScheduleNode.List();
        for (ScheduleNode node : list) {
            ScheduleNode.List inner = node.getNodes();
            node.setNodes(null);
            if (node.getCardId() != null) {
                for (ListCard card : cards) {
                    if (card.getId() == node.getCardId()) {
                        node.setCard(card);
                        break;
                    }
                }
            }
            resultList.add(node);
            if (inner != null && !inner.isEmpty()) {
                resultList.addAll(parseNodes(inner, cards));
            }
        }
        return resultList;
    }
}
