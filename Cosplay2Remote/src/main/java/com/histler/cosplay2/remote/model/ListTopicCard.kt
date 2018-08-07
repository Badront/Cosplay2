package com.histler.cosplay2.remote.model

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class ListTopicCard(
        val image: TopicCardImage,
        id: Long,
        topicName: String,
        topicId: Long,
        votingNumber: String,
        votingTitle: String,
        win: String,
        userId: Long
) : TopicCard(id, topicName, topicId, votingNumber, votingTitle, win, userId)