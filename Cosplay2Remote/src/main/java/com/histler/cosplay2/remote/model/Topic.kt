package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class Topic(
        val id: Long,
        @SerializedName("url_code") val urlCode: String,
        @SerializedName("card_code") val cardCode: String,
        val title: String
)