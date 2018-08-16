package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class Image(
        @SerializedName("filename") val fileName: Long,
        val original: ImageSize,
        val large: ImageSize,
        val medium: ImageSize
)