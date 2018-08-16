package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.TopicEntity
import com.histler.cosplay2.data.repository.TopicsAndRequestsDataStore
import com.histler.cosplay2.data.repository.TopicsAndRequestsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class TopicsAndRequestsRemoteDataStore @Inject constructor(
        private val topicsAndRequestsRemote: TopicsAndRequestsRemote
) : TopicsAndRequestsDataStore {
    override fun clearTopicsAndRequests(): Completable {
        throw UnsupportedOperationException("Clearing participants isn't supported here...")
    }

    override fun saveTopicsAndRequests(topicsAndRequests: Map<TopicEntity, List<RequestEntity>>): Completable {
        throw UnsupportedOperationException("Saving participants isn't supported here...")
    }

    override fun getTopicsAndRequests(): Observable<Map<TopicEntity, List<RequestEntity>>> {
        return topicsAndRequestsRemote.getTopicsAndRequests()
    }
}