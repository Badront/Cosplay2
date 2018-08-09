package com.histler.base.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.base.cache.db.ConfigConstants

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(
        tableName = ConfigConstants.TABLE_NAME,
        indices = [
            (Index(value = [
                (ConfigConstants.COLUMN_TABLE_REQUEST)
            ]))]
)
data class Config(
        var lastCacheTime: Long,
        @PrimaryKey
        @ColumnInfo(name = ConfigConstants.COLUMN_TABLE_REQUEST)
        var tableRequest: String)