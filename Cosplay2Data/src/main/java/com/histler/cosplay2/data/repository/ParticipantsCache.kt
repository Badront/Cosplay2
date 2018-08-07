package com.histler.cosplay2.data.repository

import com.histler.cosplay2.data.model.ParticipantEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
interface ParticipantsCache {

    fun clearParticipants(): Completable

    fun saveParticipants(participants: List<ParticipantEntity>): Completable

    fun getParticipants(): Observable<List<ParticipantEntity>>

    fun areParticipantsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}