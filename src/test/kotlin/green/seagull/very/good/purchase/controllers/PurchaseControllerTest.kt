package green.seagull.very.good.purchase.controllers

import green.seagull.very.good.purchase.TestDataModel.samplePurchase
import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.service.PurchaseService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.kotlin.given
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig::class)
class PurchaseControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var mockPurchaseService: PurchaseService

    @Test
    fun `GET purchases list endpoint returns 200`() {
        given(mockPurchaseService.findAll()).willReturn(Flux.just(samplePurchase()))

        webTestClient.get().uri(URL)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Purchase::class.java)
            .value<WebTestClient.ListBodySpec<Purchase>> {
                assertThat(it.first())
                    .usingRecursiveComparison()
                    .ignoringFields("amountDollars") // Truncating 19.00 to 19.0
                    .isEqualTo(samplePurchase())
            }
    }

    @Test
    fun `should GET single purchase by id`() {
        given(mockPurchaseService.findById("1")).willReturn((Mono.just(samplePurchase())))

        webTestClient.get().uri("$URL/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<Purchase>()
            .isEqualTo(samplePurchase())
    }

    @Test
    fun `should POST purchase to create a new purchase`() {
        webTestClient.post().uri(URL)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(samplePurchase()), Purchase::class.java)
            .exchange()
            .expectStatus().isCreated

        verify(mockPurchaseService).save(samplePurchase())
    }

    @Test
    fun `should DELETE purchase to delete a purchase`() {
        webTestClient.delete().uri("$URL/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk

        verify(mockPurchaseService).delete("1")
    }

    companion object {
        const val URL = "/api/purchases"
    }
}

@Configuration
open class TestConfig {
    @Primary
    @Bean
    open fun mockPurchaseService(): PurchaseService = Mockito.mock(PurchaseService::class.java)
}