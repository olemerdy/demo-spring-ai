package org.lafeuille.demo.services

import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatModel: ChatModel
) {

    fun joke(): ChatResponse =
        chatModel.call(Prompt("Tell me a joke"))
}