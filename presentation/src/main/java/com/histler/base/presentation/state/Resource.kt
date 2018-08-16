package com.histler.base.presentation.state

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class Resource<out T> constructor(
        val state: ResourceState,
        val data: T?,
        val message: String?
)