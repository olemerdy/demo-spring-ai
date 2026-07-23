package org.lafeuille.demo

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.SelinuxContext
import org.testcontainers.ollama.OllamaContainer
import java.io.File

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    @Bean
    @ServiceConnection
    fun ollamaContainer(): OllamaContainer {
        val hostPath = System.getProperty("user.home") + "/.ollama"
        File(hostPath).mkdirs()

        val container = OllamaContainer("ollama/ollama")
        container.addFileSystemBind(
            hostPath,
            "/root/.ollama",
            BindMode.READ_WRITE,
            SelinuxContext.SHARED,
        )
        return container
    }
}
