package com.histler.cosplay2.cache.db

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
object TopicConstants {
    const val TABLE_NAME = "topic"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_URL_CODE = "url_code"
    const val COLUMN_CARD_CODE = "card_code"

    const val QUERY_TOPICS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_TOPICS = "DELETE FROM $TABLE_NAME"
}