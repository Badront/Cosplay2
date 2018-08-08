package com.histler.cosplay2.remote

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.histler.base.remote.serializer.DateLongSerializer
import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.repository.EventsRemote
import com.histler.cosplay2.remote.mapper.ScheduleModelMapper
import com.histler.cosplay2.remote.model.ListTopicCard
import com.histler.cosplay2.remote.model.Plan
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
    override fun getSchedule(): Observable<List<ScheduleEntity>> {
        return Observable
                .zip(
                        service
                                .getSchedule(),
                        service.getTopicsAndCards(),
                        BiFunction<Plan, TopicsAndCards, List<Pair<ScheduleNode, ListTopicCard?>>> { plan, topics ->
                            val gson = GsonBuilder()
                                    .registerTypeAdapter(Date::class.java, DateLongSerializer())
                                    .create()
                            val scheduleNodesType = object : TypeToken<List<ScheduleNode>>() {}.type
                            val scheduleNodes: List<ScheduleNode> = gson.fromJson(plan.plan, scheduleNodesType)
                            parseNodes(scheduleNodes, topics.cards)
                        }
                )
                .map {
                    it.map { mapper.mapFromModel(it) }
                }
    }

    private fun parseNodes(nodes: List<ScheduleNode>, cards: List<ListTopicCard>): List<Pair<ScheduleNode, ListTopicCard?>> {
        val resultList = mutableListOf<Pair<ScheduleNode, ListTopicCard?>>()
        for (node in nodes) {
            val innerNodes = node.nodes
            node.nodes = null
            var card: ListTopicCard? = null
            if (node.cardId != null) {
                for (topicCard in cards) {
                    if (topicCard.id == node.cardId) {
                        card = topicCard
                        break
                    }
                }
            }
            resultList.add(Pair(node, card))
            if (innerNodes != null && innerNodes.isNotEmpty()) {
                resultList.addAll(parseNodes(innerNodes, cards))
            }
        }
        return resultList
    }
}



