package com.histler.cosplay2.remote.mapper

import com.histler.base.remote.mapper.ModelMapper
import com.histler.cosplay2.data.model.NominationEntity
import com.histler.cosplay2.data.model.ParticipantEntity
import com.histler.cosplay2.data.model.WinTypeEntity
import com.histler.cosplay2.data.repository.UrlProvider
import com.histler.cosplay2.remote.model.ListTopicCard
import com.histler.cosplay2.remote.model.Topic
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class ParticipantModelMapper @Inject constructor(private val urlProvider: UrlProvider) : ModelMapper<Pair<ListTopicCard?, Topic>, ParticipantEntity> {
    override fun mapFromModel(model: Pair<ListTopicCard?, Topic>): ParticipantEntity {
        val card = model.first
        val topic = model.second
        return ParticipantEntity(
                card?.id,
                card?.votingTitle ?: topic.title,
                if (card != null) getImageUrl(card) else null,
                NominationEntity(topic.id, topic.title),
                when (card?.win) {
                    "1" -> WinTypeEntity.GOLD
                    "2" -> WinTypeEntity.SILVER
                    "3" -> WinTypeEntity.BRONZE
                    else -> {
                        null
                    }
                }
        )
    }

    private fun getImageUrl(card: ListTopicCard): String {
        return if (card.image != null) {
            urlProvider.getBaseFestivalUrl() + "uploads/" + urlProvider.getBaseFestivalUrl() + "/" + card.id + "/" + card.image.fileName + "_large.jpg"
        } else {
            urlProvider.getBaseFestivalUrl() + "images/avatars/" + card.userId + "_full.png"
        }
    }
}