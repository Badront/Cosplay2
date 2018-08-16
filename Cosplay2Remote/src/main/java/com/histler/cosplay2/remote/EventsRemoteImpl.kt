package com.histler.cosplay2.remote

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.histler.base.remote.serializer.DateLongSerializer
import com.histler.cosplay2.data.model.ScheduleNodeEntity
import com.histler.cosplay2.data.repository.EventsRemote
import com.histler.cosplay2.remote.mapper.ScheduleModelMapper
import com.histler.cosplay2.remote.model.Plan
import com.histler.cosplay2.remote.model.Request
import com.histler.cosplay2.remote.model.ScheduleNode
import com.histler.cosplay2.remote.model.TopicsAndCards
import com.histler.cosplay2.remote.service.Cosplay2ScheduleService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.*
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class EventsRemoteImpl @Inject constructor(
        private val service: Cosplay2ScheduleService,
        private val mapper: ScheduleModelMapper
) : EventsRemote {
    override fun getSchedule(): Observable<List<ScheduleNodeEntity>> {
        return Observable
                .zip(
                        service
                                .getSchedule(),
                        service.getTopicsAndCards(),
                        BiFunction<Plan, TopicsAndCards, List<Pair<ScheduleNode, Request?>>> { plan, topics ->
                            val gson = GsonBuilder()
                                    .registerTypeAdapter(Date::class.java, DateLongSerializer())
                                    .create()
                            val scheduleNodesType = object : TypeToken<List<ScheduleNode>>() {}.type
                            val scheduleNodes: List<ScheduleNode> = gson.fromJson(plan.plan, scheduleNodesType)
                            parseNodes(scheduleNodes, topics.cards)
                        }
                )
                .map { pairs ->
                    pairs.map { mapper.mapFromModel(it) }
                }
    }

    private fun parseNodes(nodes: List<ScheduleNode>, cards: List<Request>, parentNodeId: String? = null): List<Pair<ScheduleNode, Request?>> {
        val resultList = mutableListOf<Pair<ScheduleNode, Request?>>()
        for (node in nodes) {
            val innerNodes = node.nodes
            node.nodes = null
            node.parentId = parentNodeId
            var card: Request? = null
            if (node.requestId != null) {
                for (topicCard in cards) {
                    if (topicCard.id == node.requestId) {
                        card = topicCard
                        break
                    }
                }
            }
            resultList.add(Pair(node, card))
            if (innerNodes != null && innerNodes.isNotEmpty()) {
                resultList.addAll(parseNodes(innerNodes, cards, node.uid))
            }
        }
        return resultList
    }
}



