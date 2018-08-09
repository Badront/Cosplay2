package com.histler.base.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.histler.base.cache.db.ConfigConstants
import com.histler.base.cache.model.Config
import io.reactivex.Maybe

/**
 * Created by abadretdinov
 * on 09.07.2018
 */
@Dao
abstract class ConfigDao {
    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(request: String): Maybe<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}