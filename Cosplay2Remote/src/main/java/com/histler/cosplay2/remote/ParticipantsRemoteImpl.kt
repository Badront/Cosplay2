package com.histler.cosplay2.remote

import com.histler.cosplay2.data.model.ParticipantEntity
import com.histler.cosplay2.data.repository.ParticipantsRemote
import com.histler.cosplay2.remote.mapper.ParticipantModelMapper
import com.histler.cosplay2.remote.model.ListTopicCard
import com.histler.cosplay2.remote.model.Topic
import com.histler.cosplay2.remote.service.Cosplay2ScheduleService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class ParticipantsRemoteImpl @Inject constructor(
        private val service: Cosplay2ScheduleService,
        private val mapper: ParticipantModelMapper
) : ParticipantsRemote {
    override fun getParticipants(): Observable<List<ParticipantEntity>> {
        return service
                .getTopicsAndCards()
                .map { tac ->
                    val topics = tac.topics
                    val cards = mutableListOf<ListTopicCard>()
                    cards.addAll(tac.cards)
                    val result = mutableListOf<Pair<ListTopicCard?, Topic>>()

                    for (topic in topics) {
                        val iterator = cards.iterator()
                        while (iterator.hasNext()) {
                            val card = iterator.next()
                            if (card.topicId == topic.id) {
                                card.topicName = topic.title
                                result.add(Pair(card, topic))
                                iterator.remove()
                            }
                        }
                    }
                    result.map {
                        mapper.mapFromModel(it)
                    }
                }
    }
}