package ru.badr.cosplay2.remote;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.badr.cosplay2.api.cards.info.GetCardResult;
import ru.badr.cosplay2.api.cards.list.TopicsAndCards;
import ru.badr.cosplay2.api.media.AlbumsAndPhotos;
import ru.badr.cosplay2.api.schedule.base.Plan;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:21
 */
public interface Cosplay2RestService {
    @GET("/api/events/get_plan")
    Plan getSchedule();
    @GET("/api/cards/get_topics_and_cards")
    TopicsAndCards getTopicsAndCards();

    @GET("/api/cards/get_card")
    GetCardResult getCard(@Query("request_id") long cardId);

    @GET("/api/media/get_data")
    AlbumsAndPhotos getAlbumsAndPhotos();
    /*/api/users/get_events*/
}
