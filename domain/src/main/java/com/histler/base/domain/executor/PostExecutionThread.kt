package com.histler.base.domain.executor

import io.reactivex.Scheduler

/**
 * Created by abadretdinov
 * on 04.07.2018
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}