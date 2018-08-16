package com.histler.cosplay2.remote.mapper

import com.histler.base.remote.mapper.ModelMapper
import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.WinTypeEntity
import com.histler.cosplay2.data.repository.UrlProvider
import com.histler.cosplay2.remote.model.Request
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 15.08.2018
 */
class RequestModelMapper @Inject constructor(private val urlProvider: UrlProvider) : ModelMapper<Request, RequestEntity> {
    override fun mapFromModel(model: Request): RequestEntity {
        return RequestEntity(
                model.id,
                model.topicId,
                model.votingTitle,
                getImageUrl(model),
                model.votingNumber,
                when (model.win) {
                    "1" -> WinTypeEntity.GOLD
                    "2" -> WinTypeEntity.SILVER
                    "3" -> WinTypeEntity.BRONZE
                    else -> {
                        null
                    }
                }
        )
    }

    private fun getImageUrl(request: Request): String {
        return if (request.image != null) {
            urlProvider.getBaseFestivalUrl() + "uploads/" + urlProvider.getFestivalId() + "/" + request.id + "/" + request.image.fileName + "_large.jpg"
        } else {
            urlProvider.getBaseFestivalUrl() + "images/avatars/" + request.userId + "_full.png"
        }
    }

}