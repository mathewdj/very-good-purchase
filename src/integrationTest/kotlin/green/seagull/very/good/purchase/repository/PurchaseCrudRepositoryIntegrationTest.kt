package green.seagull.very.good.purchase.repository

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.test.IntegrationTestData.bookPurchase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
class PurchaseCrudRepositoryIntegrationTest {

    @Autowired
    private lateinit var purchaseCrudRepository: PurchaseCrudRepository

    @BeforeEach
    fun cleanup() {
        purchaseCrudRepository.deleteAll().block()
    }

    @Test
    fun `should save purchase`() {
        purchaseCrudRepository.save(bookPurchase()).block()
        val purchases = purchaseCrudRepository.findAll()
        StepVerifier
            .create(purchases)
            .assertNext { (id, name, date, purchaseType, amountDollars): Purchase ->
                assertThat(name).isEqualTo("Tales of Beedle the Bard")
                assertThat(amountDollars).isEqualTo(bookPurchase().amountDollars)
                assertThat(date).isEqualTo(bookPurchase().date)
                assertThat(purchaseType).isEqualTo(bookPurchase().purchaseType)
                assertThat(id).isNotNull()
            }
            .expectComplete()
            .verify()
    }
}