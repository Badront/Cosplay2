package com.histler.base.cache.db

/**
 * Created by abadretdinov
 * on 09.07.2018
 */
object ConfigConstants {
    const val TABLE_NAME = "config"

    const val COLUMN_TABLE_REQUEST = "tableRequest"

    const val QUERY_CONFIG = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_TABLE_REQUEST=:request"
}