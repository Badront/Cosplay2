package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class TopicCardImage(
        @SerializedName("fileName") val fileName: Long,
        val original: ImageSize,
        val large: ImageSize,
        val medium: ImageSize
)