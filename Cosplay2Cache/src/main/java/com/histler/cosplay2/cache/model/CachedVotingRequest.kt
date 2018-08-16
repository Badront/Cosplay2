package com.histler.cosplay2.cache.model

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.RequestConstants
import com.histler.cosplay2.cache.db.VotingRequestConstants
import com.histler.cosplay2.data.model.WinTypeEntity

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
@Entity(
        tableName = VotingRequestConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedRequest::class,
                    parentColumns = arrayOf(RequestConstants.COLUMN_ID),
                    childColumns = arrayOf(VotingRequestConstants.COLUMN_ID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [VotingRequestConstants.COLUMN_ID])),
            (Index(value = [VotingRequestConstants.COLUMN_VOTING_NUMBER])),
            (Index(value = [VotingRequestConstants.COLUMN_WIN]))
        ]
)
data class CachedVotingRequest(
        @PrimaryKey
        @ColumnInfo(name = VotingRequestConstants.COLUMN_ID)
        var requestId: Long,
        @ColumnInfo(name = VotingRequestConstants.COLUMN_VOTING_NUMBER)
        var votingNumber: Int,
        @ColumnInfo(name = VotingRequestConstants.COLUMN_WIN)
        @TypeConverters(WineTypeConverter::class)
        var win: WinTypeEntity?
)