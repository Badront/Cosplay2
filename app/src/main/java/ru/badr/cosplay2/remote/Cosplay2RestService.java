package ru.badr.cosplay2.remote;

import retrofit.http.GET;
import ru.badr.cosplay2.api.cards.TopicsAndCards;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:21
 */
public interface Cosplay2RestService {
    @GET("/api/cards/get_topics_and_cards")
    TopicsAndCards getTopicsAndCards();
    /*todo http://opencon14.cosplay2.ru/api/cards/get_card?request_id={card_id}*/
}
