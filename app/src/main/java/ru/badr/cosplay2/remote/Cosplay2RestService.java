package ru.badr.cosplay2.remote;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.badr.cosplay2.api.cards.info.GetCardResult;
import ru.badr.cosplay2.api.cards.list.TopicsAndCards;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:21
 */
public interface Cosplay2RestService {
    @GET("/api/cards/get_topics_and_cards")
    TopicsAndCards getTopicsAndCards();
    /*todo http://opencon14.cosplay2.ru/api/cards/get_card?request_id={card_id}*/
    @GET("/api/cards/get_card")
    GetCardResult getCard(@Query("request_id") long cardId);
}
