package com.histler.base.cache.mapper

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
interface CachedMapper<CACHED, ENTITY> {
    fun mapFromCached(cached: CACHED): ENTITY
    fun mapToCached(entity: ENTITY): CACHED

}