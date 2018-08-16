package com.histler.cosplay2.cache.db

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
object RequestConstants {
    const val TABLE_NAME = "request"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_TOPIC_ID = "topic_id"
    const val COLUMN_IMAGE = "image"

    const val QUERY_REQUESTS =
            "SELECT $TABLE_NAME.*,${VotingRequestConstants.TABLE_NAME}.* " +
                    "FROM $TABLE_NAME, ${VotingRequestConstants.TABLE_NAME} " +
                    "WHERE $TABLE_NAME.$COLUMN_ID=${VotingRequestConstants.TABLE_NAME}.${VotingRequestConstants.COLUMN_ID}"

    const val QUERY_REQUESTS_BY_TOPIC =
            "SELECT $TABLE_NAME.*,${VotingRequestConstants.TABLE_NAME}.* " +
                    "FROM $TABLE_NAME, ${VotingRequestConstants.TABLE_NAME} " +
                    "WHERE $TABLE_NAME.$COLUMN_ID=${VotingRequestConstants.TABLE_NAME}.${VotingRequestConstants.COLUMN_ID}" +
                    " AND $TABLE_NAME.$COLUMN_TOPIC_ID=:topicId"

    const val DELETE_REQUESTS = "DELETE FROM $TABLE_NAME, ${VotingRequestConstants.TABLE_NAME}"
}