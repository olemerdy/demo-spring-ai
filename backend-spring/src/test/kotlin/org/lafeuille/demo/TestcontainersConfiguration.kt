package org.lafeuille.demo

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.BindMode
import org.testcontainers.ollama.OllamaContainer
import org.testcontainers.utility.DockerImageName
import java.nio.file.Paths

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    fun ollamaContainer(): OllamaContainer {
        val hostPath = Paths.get(System.getProperty("user.home")).resolve(".ollama")
        return OllamaContainer(DockerImageName.parse("ollama/ollama:latest"))
            .withFileSystemBind(hostPath.toString(), "/root/.ollama", BindMode.READ_WRITE)
    }

}
