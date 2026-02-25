package org.lafeuille.demo.web

import org.lafeuille.demo.services.ChatService
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/chat")
class ChatController(
    private val chatService: ChatService
) {

    @GetMapping("joke")
    fun joke(): ChatResponse =
        chatService.joke()
}