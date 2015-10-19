package ru.badr.cosplay2.task;

import android.content.Context;

import java.util.List;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.Topic;
import ru.badr.cosplay2.api.cards.TopicsAndCards;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 15:56
 */
public class TaggedCardsLoadRequest extends TaskRequest<Card.List> {
    private Context mContext;
    private String mPropertyTag;

    public TaggedCardsLoadRequest(Context context, String propertyTag) {
        super(Card.List.class);
        this.mContext = context;
        this.mPropertyTag = propertyTag;
    }

    @Override
    public Card.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        String topicName = container.getProperties().getProperty(mPropertyTag);
        TopicsAndCards result = container.getCosplay2RestService().getTopicsAndCards();
        Card.List list = new Card.List();
        if (result != null && result.getCards() != null && result.getTopics() != null) {
            List<Topic> topics = result.getTopics();
            Long topicId = null;
            for (Topic topic : topics) {
                if (topic.getTitle() != null && topic.getTitle().contains(topicName)) {
                    topicId = topic.getId();
                    break;
                }
            }
            if (topicId != null) {
                for (Card card : result.getCards()) {
                    if (card.getTopicId() == topicId) {
                        list.add(card);
                    }
                }
            }
        }
        return list;
    }
}
