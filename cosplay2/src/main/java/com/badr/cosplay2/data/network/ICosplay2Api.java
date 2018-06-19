package com.badr.cosplay2.data.network;

import com.badr.cosplay2.model.schedule.Plan;

import io.reactivex.Single;

/**
 * Created by abadretdinov
 * on 19.06.2018
 */
public interface ICosplay2Api {
    Single<Plan> getSchedule();
}
