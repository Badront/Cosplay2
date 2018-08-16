package com.histler.cosplay2.cache.dao

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.TopicConstants
import com.histler.cosplay2.cache.model.CachedTopic
import io.reactivex.Flowable

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
@Dao
abstract class CachedTopicDao {
    @Query(TopicConstants.QUERY_TOPICS)
    abstract fun getTopics(): Flowable<List<CachedTopic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTopics(topics: List<CachedTopic>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateTopics(topics: List<CachedTopic>)

    @Transaction
    open fun upsertTopics(topics: List<CachedTopic>) {
        insertTopics(topics)
        updateTopics(topics)
    }

    @Query(TopicConstants.DELETE_TOPICS)
    abstract fun deleteTopics()
}