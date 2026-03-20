package org.lafeuille.demo.web

import org.lafeuille.demo.services.ChatService
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/chat"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ChatController(
    private val chatService: ChatService,
) {
    @GetMapping("joke")
    fun joke(): ChatResponse = chatService.getJoke()

    @GetMapping("holidays")
    fun holidays(): ChatResponse? = chatService.getHolidays()
}
