package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.RequestMapper
import com.histler.cosplay2.data.mapper.TopicMapper
import com.histler.cosplay2.data.repository.TopicsAndRequestsCache
import com.histler.cosplay2.data.store.ParticipantsDataStoreFactory
import com.histler.cosplay2.domain.model.Request
import com.histler.cosplay2.domain.model.Topic
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
        private val topicMapper: TopicMapper,
        private val requestMapper: RequestMapper,
        private val cache: TopicsAndRequestsCache,
        private val dataStoreFactory: ParticipantsDataStoreFactory
) : ParticipantsRepository {
    override fun getParticipants(sectionName: String?): Observable<Map<Topic, List<Request>>> {
        return Single.zip(
                cache.areTopicsAndRequestsCached(),
                cache.isCacheExpired(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMapObservable {
                    dataStoreFactory
                            .getDataStore(it.first, it.second)
                            .getTopicsAndRequests()
                }
                .distinct()
                .flatMap { topicsMap ->
                    dataStoreFactory
                            .getCacheDataStore()
                            .saveTopicsAndRequests(topicsMap)
                            .andThen(Observable.just(topicsMap))
                }
                .map { topicsMap ->
                    topicsMap
                            .mapKeys {
                                topicMapper.mapFromEntity(it.key)
                            }
                            .mapValues {
                                it.value.map { requestEntity ->
                                    requestMapper.mapFromEntity(requestEntity)
                                }

                            }
                }
    }
}