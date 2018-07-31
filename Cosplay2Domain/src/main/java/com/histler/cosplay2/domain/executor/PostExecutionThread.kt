package com.histler.cosplay2.domain.executor

import io.reactivex.Scheduler

/**
 * Created by abadretdinov
 * on 31.07.2018
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}