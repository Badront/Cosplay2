package com.histler.base.data.mapper

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
interface EntityMapper<ENTITY, DOMAIN> {
    fun mapFromEntity(entity: ENTITY): DOMAIN
    fun mapToEntity(domain: DOMAIN): ENTITY
}