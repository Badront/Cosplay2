package com.histler.base.remote.mapper

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
interface ModelMapper<in Model, out Entity> {
    fun mapFromModel(model: Model): Entity
}