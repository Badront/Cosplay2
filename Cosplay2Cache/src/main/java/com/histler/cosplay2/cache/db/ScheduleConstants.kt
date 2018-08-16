package com.histler.cosplay2.cache.db

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
object ScheduleConstants {
    const val TABLE_NAME = "schedule"
    const val COLUMN_NODE_ID = "node_id"
    const val COLUMN_TIME_START = "time_start"
    const val COLUMN_TIME_END = "time_end"
    const val COLUMN_TYPE = "type"
    const val COLUMN_PARENT_ID = "parent_id"

    const val QUERY_SCHEDULE_WITH_NODES =
            "SELECT " +
                    "$TABLE_NAME.*, " +
                    "${NodeConstants.TABLE_NAME}.*, " +
                    "${RequestNodeConstants.TABLE_NAME}.*, " +
                    "${RequestConstants.TABLE_NAME}.*, " +
                    "${TopicNodeConstants.TABLE_NAME}.*, " +
                    "${TopicConstants.TABLE_NAME}.* " +
                    "FROM " +
                    "$TABLE_NAME " +
                    "INNER JOIN ${NodeConstants.TABLE_NAME} ON $TABLE_NAME.$COLUMN_NODE_ID=${NodeConstants.TABLE_NAME}.${NodeConstants.COLUMN_ID} " +
                    "LEFT JOIN ${RequestNodeConstants.TABLE_NAME} ON ${NodeConstants.TABLE_NAME}.${NodeConstants.COLUMN_ID}=${RequestNodeConstants.TABLE_NAME}.${RequestNodeConstants.COLUMN_NODE_UID} " +
                    "LEFT JOIN ${RequestConstants.TABLE_NAME} ON ${RequestNodeConstants.TABLE_NAME}.${RequestNodeConstants.COLUMN_REQUEST_ID}=${RequestConstants.TABLE_NAME}.${RequestConstants.COLUMN_ID} " +
                    "LEFT JOIN ${TopicNodeConstants.TABLE_NAME} ON ${NodeConstants.TABLE_NAME}.${NodeConstants.COLUMN_ID}=${TopicNodeConstants.TABLE_NAME}.${TopicNodeConstants.COLUMN_NODE_UID} " +
                    "LEFT JOIN ${TopicConstants.TABLE_NAME} ON ${TopicNodeConstants.TABLE_NAME}.${TopicNodeConstants.COLUMN_TOPIC_ID}=${TopicConstants.TABLE_NAME}.${TopicConstants.COLUMN_ID} " +
                    "ORDER BY $TABLE_NAME.$COLUMN_TIME_START ASC"

    const val DELETE_SCHEDULE_WITH_NODES =
            "DELETE FROM $TABLE_NAME, ${NodeConstants.TABLE_NAME}, ${RequestNodeConstants.TABLE_NAME}, ${TopicNodeConstants.TABLE_NAME}"
}