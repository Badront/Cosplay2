package com.histler.cosplay2.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.histler.base.cache.dao.ConfigDao
import com.histler.cosplay2.cache.dao.CachedRequestDao
import com.histler.cosplay2.cache.dao.CachedScheduleDao
import com.histler.cosplay2.cache.dao.CachedTopicDao
import com.histler.cosplay2.cache.model.*

/**
 * Created by abadretdinov
 * on 13.08.2018
 */
@Database(
        entities = [
            (CachedTopic::class),
            (CachedRequest::class),
            (CachedVotingRequest::class),
            (CachedNode::class),
            (CachedSchedule::class),
            (CachedTopicNode::class),
            (CachedRequestNode::class)
        ],
        version = 1
)
abstract class Cosplay2Database : RoomDatabase() {

    abstract fun cachedTopicsDao(): CachedTopicDao

    abstract fun cachedRequestsDao(): CachedRequestDao

    abstract fun cachedScheduleDao(): CachedScheduleDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: Cosplay2Database? = null
        private val lock = Any()

        fun getInstance(context: Context): Cosplay2Database {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room
                                .databaseBuilder(
                                        context.applicationContext,
                                        Cosplay2Database::class.java,
                                        "cosplay2.db"
                                )
                                .build()
                    }
                    return INSTANCE as Cosplay2Database
                }
            }
            return INSTANCE as Cosplay2Database
        }
    }
}