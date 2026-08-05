package org.lafeuille.demo

import com.github.dockerjava.api.model.Device
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
        val hostPath = System.getProperty("user.home") + "/.ollama/models"
        File(hostPath).mkdirs()

        val container =
            OllamaContainer("ollama/ollama")
                .withEnv("OLLAMA_IGPU_ENABLE", "" + true)
                .withEnv("HSA_OVERRIDE_GFX_VERSION", "10.3.0")
                .withCreateContainerCmdModifier {
                    it.hostConfig?.withDevices(
                        Device("rwm", "/dev/kfd", "/dev/kfd"),
                        Device("rwm", "/dev/dri", "/dev/dri"),
                    )
                }
        container.addFileSystemBind(
            hostPath,
            "/root/.ollama/models",
            BindMode.READ_WRITE,
            SelinuxContext.SHARED,
        )
        return container
    }
}
