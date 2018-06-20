package ru.badr.cosplay2.task;

import android.content.Context;

import com.badr.cosplay2.model.cards.Topic;
import com.badr.cosplay2.model.cards.list.ListCard;
import com.badr.cosplay2.model.cards.list.TopicsAndCards;
import com.octo.android.robospice.retry.DefaultRetryPolicy;

import java.util.List;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 15:56
 */
public class TaggedCardsLoadRequest extends TaskRequest<ListCard.List> {
    private Context mContext;
    private String mPropertyTag;

    public TaggedCardsLoadRequest(Context context, String propertyTag) {
        super(ListCard.List.class);
        this.mContext = context.getApplicationContext();
        this.mPropertyTag = propertyTag;
        setRetryPolicy(new DefaultRetryPolicy(1, DefaultRetryPolicy.DEFAULT_DELAY_BEFORE_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public ListCard.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        String topicName = container.getProperties().getProperty(mPropertyTag);
        TopicsAndCards result = container.getCosplay2RestService().getTopicsAndCards().execute().body();
        ListCard.List list = new ListCard.List();
        if (result != null && result.getCards() != null && result.getTopics() != null) {
            List<Topic> topics = result.getTopics();
            Long topicId = null;
            for (Topic topic : topics) {
                if (topic.getTitle() != null && topic.getTitle().equalsIgnoreCase(topicName)) {
                    topicId = topic.getId();
                    break;
                }
            }
            if (topicId != null) {
                for (ListCard card : result.getCards()) {
                    if (card.getTopicId() == topicId) {
                        list.add(card);
                    }
                }
            }
        }
        return list;
    }
}
