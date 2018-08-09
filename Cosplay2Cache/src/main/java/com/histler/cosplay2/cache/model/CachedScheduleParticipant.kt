package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import com.histler.cosplay2.cache.db.ParticipantConstants
import com.histler.cosplay2.cache.db.ScheduleParticipantConstants
import java.util.*

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(tableName = ScheduleParticipantConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedParticipant::class,
                    parentColumns = [ParticipantConstants.COLUMN_ID],
                    childColumns = [ScheduleParticipantConstants.COLUMN_PARTICIPANT_ID],
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [ScheduleParticipantConstants.COLUMN_TIME]))
        ])
data class CachedScheduleParticipant(
        @ColumnInfo(name = ScheduleParticipantConstants.COLUMN_PARTICIPANT_ID)
        var participantId: Long,
        @ColumnInfo(name = ScheduleParticipantConstants.COLUMN_TIME)
        var time: Date
)