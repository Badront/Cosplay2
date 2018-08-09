package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.ParticipantConstants

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(
        tableName = ParticipantConstants.TABLE_NAME,
        indices = [
            (Index(value = [ParticipantConstants.COLUMN_ID])),
            (Index(value = [ParticipantConstants.COLUMN_NAME]))
        ]
)
data class CachedParticipant(
        @PrimaryKey
        @ColumnInfo(name = ParticipantConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = ParticipantConstants.COLUMN_NAME)
        var name: String,
        @ColumnInfo(name = ParticipantConstants.COLUMN_IMAGE)
        var image: String?
)