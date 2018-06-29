package com.badr.cosplay2.data.network;

import com.badr.cosplay2.model.cards.info.json.GetCardResult;
import com.badr.cosplay2.model.cards.info.json.GetUserResult;
import com.badr.cosplay2.model.cards.list.TopicsAndCards;
import com.badr.cosplay2.model.event.network.EventType;
import com.badr.cosplay2.model.event.network.Events;
import com.badr.cosplay2.model.media.AlbumsAndPhotos;
import com.badr.cosplay2.model.schedule.Plan;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abadretdinov
 * on 19.06.2018
 */
public interface ICosplay2Api {
    @GET("/events/get_plan")
    Single<Plan> getSchedule();

    @GET("/cards/get_topics_and_cards")
    Single<TopicsAndCards> getTopicsAndCards();

    @GET("/cards/get_card")
    Single<GetCardResult> getCard(@Query("request_id") long cardId);

    @GET("/users/get")
    Single<GetUserResult> getUser(@Query("user_id") long userId);

    @GET("/media/get_data")
    Single<AlbumsAndPhotos> getAlbumsAndPhotos();

    @GET("/events/get_types")
    Single<List<EventType>> getEventTypes();

    @GET("/events/filter_list")
    Single<Events> getEvents();
}
