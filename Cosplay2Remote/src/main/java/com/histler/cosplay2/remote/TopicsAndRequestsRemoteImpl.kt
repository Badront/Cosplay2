package com.histler.cosplay2.remote

import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.TopicEntity
import com.histler.cosplay2.data.repository.TopicsAndRequestsRemote
import com.histler.cosplay2.remote.mapper.RequestModelMapper
import com.histler.cosplay2.remote.mapper.TopicModelMapper
import com.histler.cosplay2.remote.model.Request
import com.histler.cosplay2.remote.model.Topic
import com.histler.cosplay2.remote.service.Cosplay2ScheduleService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class TopicsAndRequestsRemoteImpl @Inject constructor(
        private val service: Cosplay2ScheduleService,
        private val topicMapper: TopicModelMapper,
        private val requestMapper: RequestModelMapper
) : TopicsAndRequestsRemote {
    override fun getTopicsAndRequests(): Observable<Map<TopicEntity, List<RequestEntity>>> {
        return service
                .getTopicsAndCards()
                .map { tac ->
                    val topics = tac.topics
                    val requests = mutableListOf<Request>()
                    requests.addAll(tac.cards)
                    val result = mutableMapOf<Topic, MutableList<Request>>()

                    for (topic in topics) {
                        val iterator = requests.iterator()
                        result[topic] = mutableListOf()
                        while (iterator.hasNext()) {
                            val request = iterator.next()
                            if (request.topicId == topic.id) {
                                result[topic]?.add(request)
                                iterator.remove()
                            }
                        }
                    }
                    result
                            .mapKeys {
                                topicMapper.mapFromModel(it.key)
                            }
                            .mapValues {
                                it.value.map { request ->
                                    requestMapper.mapFromModel(request)
                                }
                            }
                }
    }
}