package com.histler.cosplay2.cache.dao

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.RequestConstants
import com.histler.cosplay2.cache.model.CachedRequest
import com.histler.cosplay2.cache.model.CachedRequestWithVoting
import com.histler.cosplay2.cache.model.CachedVotingRequest
import io.reactivex.Flowable

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
@Dao
abstract class CachedRequestDao {
    @Query(RequestConstants.QUERY_REQUESTS)
    abstract fun getRequests(): Flowable<List<CachedRequestWithVoting>>

    @Query(RequestConstants.QUERY_REQUESTS_BY_TOPIC)
    abstract fun getRequestsByTopic(topicId: Long): Flowable<List<CachedRequestWithVoting>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertRequests(requests: List<CachedRequest>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertRequestsVotings(votings: List<CachedVotingRequest>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateRequests(requests: List<CachedRequest>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateRequestsVotings(votings: List<CachedVotingRequest>)

    @Transaction
    open fun upsertRequests(requestsWithVotings: List<CachedRequestWithVoting>) {
        val requests = requestsWithVotings.map { it.request }
        val votings = requestsWithVotings.map { it.voting }
        insertRequests(requests)
        updateRequests(requests)
        insertRequestsVotings(votings)
        insertRequestsVotings(votings)
    }

    @Query(RequestConstants.DELETE_REQUESTS)
    abstract fun deleteRequests()
}