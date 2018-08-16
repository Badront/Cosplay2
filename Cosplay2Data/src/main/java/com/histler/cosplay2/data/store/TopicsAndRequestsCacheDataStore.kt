package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.TopicEntity
import com.histler.cosplay2.data.repository.TopicsAndRequestsCache
import com.histler.cosplay2.data.repository.TopicsAndRequestsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class TopicsAndRequestsCacheDataStore @Inject constructor(
        private val topicsAndRequestsCache: TopicsAndRequestsCache
) : TopicsAndRequestsDataStore {
    override fun clearTopicsAndRequests(): Completable {
        return topicsAndRequestsCache.clearTopicsAndRequests()
    }

    override fun saveTopicsAndRequests(topicsAndRequests: Map<TopicEntity, List<RequestEntity>>): Completable {
        return topicsAndRequestsCache.saveTopicsAndRequests(topicsAndRequests)
    }

    override fun getTopicsAndRequests(): Observable<Map<TopicEntity, List<RequestEntity>>> {
        return topicsAndRequestsCache.getTopicsAndRequests()
    }
}