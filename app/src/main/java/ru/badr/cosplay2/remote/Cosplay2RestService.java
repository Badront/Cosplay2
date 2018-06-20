package ru.badr.cosplay2.remote;

import com.badr.cosplay2.model.cards.info.json.GetCardResult;
import com.badr.cosplay2.model.cards.info.json.GetUserResult;
import com.badr.cosplay2.model.cards.list.TopicsAndCards;
import com.badr.cosplay2.model.media.AlbumsAndPhotos;
import com.badr.cosplay2.model.schedule.Plan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:21
 */
public interface Cosplay2RestService {
    @GET("/api/events/get_plan")
    Call<Plan> getSchedule();
    @GET("/api/cards/get_topics_and_cards")
    Call<TopicsAndCards> getTopicsAndCards();

    @GET("/api/cards/get_card")
    Call<GetCardResult> getCard(@Query("request_id") long cardId);

    @GET("/api/users/get")
    Call<GetUserResult> getUser(@Query("user_id") long userId);

    @GET("/api/media/get_data")
    Call<AlbumsAndPhotos> getAlbumsAndPhotos();
    /*/api/users/get_events*/
}
