package com.histler.cosplay2.data.repository

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
interface UrlProvider {
    fun getBaseFestivalUrl(): String

    fun getFestivalId(): String
}