package ru.badr.cosplay2.task;

import android.content.Context;

import com.octo.android.robospice.retry.DefaultRetryPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.Topic;
import ru.badr.cosplay2.api.cards.list.ListCard;
import ru.badr.cosplay2.api.cards.list.TopicsAndCards;

/**
 * Created by ABadretdinov
 * 13.11.2015
 * 19:56
 */
public class SectionedCardsLoadRequest extends TaskRequest<Topic.List> {
    private Context mContext;

    public SectionedCardsLoadRequest(Context context) {
        super(Topic.List.class);
        this.mContext = context;
        setRetryPolicy(new DefaultRetryPolicy(1, DefaultRetryPolicy.DEFAULT_DELAY_BEFORE_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Topic.List loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        TopicsAndCards result = container.getCosplay2RestService().getTopicsAndCards();
        Properties properties = container.getProperties();
        List<String> notToIncludeTags = Arrays.asList(properties.getProperty("opencon.tag.photo"), properties.getProperty("opencon.tag.digital_fanart"), properties.getProperty("opencon.tag.traditional_fanart"));


        Topic.List list = new Topic.List();
        if (result != null && result.getCards() != null && result.getTopics() != null) {
            List<Topic> baseTopics = result.getTopics();
            List<ListCard> allCards = result.getCards();

            for (Topic topic : baseTopics) {
                if (!notToIncludeTags.contains(topic.getTitle())) {
                    topic.setCards(new ArrayList<ListCard>());
                    Iterator<ListCard> iterator = allCards.iterator();
                    while (iterator.hasNext()) {
                        ListCard card = iterator.next();
                        if (card.getTopicId() == topic.getId()) {
                            card.setTopicName(topic.getTitle());
                            topic.getCards().add(card);
                            iterator.remove();
                        }
                    }
                    list.add(topic);
                }
            }
        }
        return list;
    }
}
