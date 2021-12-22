package green.seagull.very.good.purchase.controllers

import green.seagull.very.good.purchase.domain.Health
import green.seagull.very.good.purchase.domain.HealthColours
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.function.Consumer

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthRouterTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `health endpoint should return green`() {
        webTestClient.get().uri("/health")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.healthColour")
                .isEqualTo("Green")
    }

}