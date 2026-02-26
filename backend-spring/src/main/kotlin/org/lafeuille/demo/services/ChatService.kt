package org.lafeuille.demo.services

import org.lafeuille.demo.tools.JollydayTools
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatModel: ChatModel
) {

    fun getJoke(): ChatResponse =
        chatModel.call(Prompt("Tell me a joke"))

    fun getHolidays(): ChatResponse? =
        ChatClient.create(chatModel)
            .prompt(Prompt("What are the public holidays in 2026 for France?"))
            .tools(JollydayTools())
            .call()
            .chatResponse()
}