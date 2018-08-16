package com.histler.cosplay2.cache.dao

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.ScheduleConstants
import com.histler.cosplay2.cache.model.*
import io.reactivex.Flowable

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
@Dao
abstract class CachedScheduleDao {

    @Query(ScheduleConstants.QUERY_SCHEDULE_WITH_NODES)
    abstract fun getSchedule(): Flowable<List<CachedScheduleWithNode>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertSchedules(schedules: List<CachedSchedule>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertNodes(nodes: List<CachedNode>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertRequestNodes(requestNodes: List<CachedRequestNode>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTopicNodes(topicNodes: List<CachedTopicNode>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateSchedules(schedules: List<CachedSchedule>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateNodes(nodes: List<CachedNode>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateRequestNodes(requestNodes: List<CachedRequestNode>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateTopicNodes(topicNodes: List<CachedTopicNode>)

    @Transaction
    open fun upsertScheduleWithNodes(scheduleWithNodes: List<CachedScheduleWithNode>) {
        val nodes = scheduleWithNodes.map { it.node }
        val schedule = scheduleWithNodes.map { it.schedule }
        val requestNodes = scheduleWithNodes.mapNotNull { it.requestNode }
        val topicNodes = scheduleWithNodes.mapNotNull { it.topicNode }

        insertNodes(nodes)
        updateNodes(nodes)

        insertSchedules(schedule)
        updateSchedules(schedule)

        insertTopicNodes(topicNodes)
        updateTopicNodes(topicNodes)

        insertRequestNodes(requestNodes)
        updateRequestNodes(requestNodes)
    }

    @Query(ScheduleConstants.DELETE_SCHEDULE_WITH_NODES)
    abstract fun deleteScheduleWithNodes()
}