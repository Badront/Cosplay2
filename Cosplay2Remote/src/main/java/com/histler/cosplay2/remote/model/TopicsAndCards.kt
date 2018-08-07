package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class TopicsAndCards(
        val topics: List<Topic>,
        @SerializedName("requests") val cards: List<ListTopicCard>
)