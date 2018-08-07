package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.ParticipantMapper
import com.histler.cosplay2.data.repository.ParticipantsCache
import com.histler.cosplay2.data.store.ParticipantsDataStoreFactory
import com.histler.cosplay2.domain.model.Participant
import com.histler.cosplay2.domain.repository.ParticipantsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class ParticipantsDataRepository @Inject constructor(
        private val participantMapper: ParticipantMapper,
        private val cache: ParticipantsCache,
        private val dataStoreFactory: ParticipantsDataStoreFactory
) : ParticipantsRepository {
    override fun getParticipants(sectionName: String?): Observable<List<Participant>> {
        return Single.zip(
                cache.areParticipantsCached(),
                cache.isCacheExpired(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMapObservable {
                    dataStoreFactory
                            .getDataStore(it.first, it.second)
                            .getParticipants()
                }
                .distinct()
                .flatMap { participants ->
                    dataStoreFactory
                            .getCacheDataStore()
                            .saveParticipants(participants)
                            .andThen(Observable.just(participants))
                }
                .map {
                    it.map {
                        participantMapper.mapFromEntity(it)
                    }
                }
    }
}