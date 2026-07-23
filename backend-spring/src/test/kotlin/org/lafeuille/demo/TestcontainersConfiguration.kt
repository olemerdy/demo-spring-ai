package org.lafeuille.demo

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.ollama.OllamaContainer

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    @Bean
    @ServiceConnection
    fun ollamaContainer(): OllamaContainer = OllamaContainer("ollama/ollama")
}
