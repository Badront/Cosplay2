package com.histler.base.presentation.mapper

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
interface Mapper<out V, in D> {
    fun mapToView(domain: D): V
}