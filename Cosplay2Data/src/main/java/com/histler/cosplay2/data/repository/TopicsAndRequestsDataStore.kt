package com.histler.cosplay2.data.repository

import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.TopicEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
interface TopicsAndRequestsDataStore {

    fun clearTopicsAndRequests(): Completable

    fun saveTopicsAndRequests(topicsAndRequests: Map<TopicEntity, List<RequestEntity>>): Completable

    fun getTopicsAndRequests(): Observable<Map<TopicEntity, List<RequestEntity>>>
}