package green.seagull.very.good.purchase.controllers

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository
import green.seagull.very.good.purchase.test.IntegrationTestData.bookPurchase
import green.seagull.very.good.purchase.test.IntegrationTestData.ps4Purchase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
class PurchaseControllerIntegrationTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var purchaseCrudRepository: PurchaseCrudRepository

    @BeforeEach
    fun cleanup() {
        purchaseCrudRepository.deleteAll().block()
    }

    @Test
    fun `should find all purchases from database`() {
        purchaseCrudRepository.save(bookPurchase()).block()

        webTestClient.get().uri("/api/purchases")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Purchase::class.java).value<WebTestClient.ListBodySpec<Purchase>> {
                val purchase = it.first()
                assertThat(purchase.name).isEqualTo(bookPurchase().name)
            }
    }

    @Test
    fun `should save a new purchase from endpoint in database`() {
        webTestClient.post().uri("/api/purchases")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(ps4Purchase())
            .exchange()
            .expectStatus().isCreated
            .expectBody(Purchase::class.java)
            .value {
                assertThat(it.id).isNotNull()
            }

        val purchase = purchaseCrudRepository.findAll().blockFirst()
        AssertionsForClassTypes.assertThat(purchase)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(ps4Purchase())
    }
}