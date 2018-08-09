package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.NominationConstants

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(
        tableName = NominationConstants.TABLE_NAME,
        indices = [
            (Index(value = [NominationConstants.COLUMN_ID])),
            (Index(value = [NominationConstants.COLUMN_NAME]))
        ]
)
data class CachedNomination(
        @PrimaryKey
        @ColumnInfo(name = NominationConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = NominationConstants.COLUMN_NAME)
        var name: String
)