package com.histler.cosplay2.cache.model

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.NominationConstants
import com.histler.cosplay2.cache.db.ParticipantConstants
import com.histler.cosplay2.cache.db.ParticipantNominationConstants
import com.histler.cosplay2.data.model.WinTypeEntity

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(
        tableName = ParticipantNominationConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedNomination::class,
                    parentColumns = arrayOf(NominationConstants.COLUMN_ID),
                    childColumns = arrayOf(ParticipantNominationConstants.COLUMN_NOMINATION_ID),
                    onDelete = ForeignKey.CASCADE
            )),
            (ForeignKey(
                    entity = CachedParticipant::class,
                    parentColumns = arrayOf(ParticipantConstants.COLUMN_ID),
                    childColumns = arrayOf(ParticipantNominationConstants.COLUMN_PARTICIPANT_ID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [
                ParticipantNominationConstants.COLUMN_NOMINATION_ID,
                ParticipantNominationConstants.COLUMN_PARTICIPANT_ID
            ]))
        ],
        primaryKeys = [
            (ParticipantNominationConstants.COLUMN_NOMINATION_ID),
            (ParticipantNominationConstants.COLUMN_PARTICIPANT_ID)
        ]
)
data class CachedParticipantNomination(
        @ColumnInfo(name = ParticipantNominationConstants.COLUMN_PARTICIPANT_ID)
        var participantId: Long,
        @ColumnInfo(name = ParticipantNominationConstants.COLUMN_NOMINATION_ID)
        var nominationId: Long,
        @TypeConverters(WineTypeConverter::class)
        var winType: WinTypeEntity?
)