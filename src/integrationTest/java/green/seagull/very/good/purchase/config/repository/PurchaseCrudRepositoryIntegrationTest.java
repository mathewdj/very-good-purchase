package green.seagull.very.good.purchase.config.repository;

import green.seagull.very.good.purchase.VeryGoodPurchaseApplication;
import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static green.seagull.very.good.purchase.config.test.IntegrationTestData.bookPurchase;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = VeryGoodPurchaseApplication.class)
@ActiveProfiles("integrationTest")
class PurchaseCrudRepositoryIntegrationTest {

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @BeforeEach
    public void cleanup() {
        purchaseCrudRepository.deleteAll().block();
    }

    @Test
    public void testSavePurchase() {
        purchaseCrudRepository.save(bookPurchase()).block();
        Flux<Purchase> purchases = purchaseCrudRepository.findAll();

        StepVerifier
                .create(purchases)
                .assertNext(purchase -> {
                    assertThat(purchase.getName()).isEqualTo("Tales of Beedle the Bard");
                    assertThat(purchase.getAmountDollars()).isEqualTo(bookPurchase().getAmountDollars());
                    assertThat(purchase.getDate()).isEqualTo(bookPurchase().getDate());
                    assertThat(purchase.getPurchaseType()).isEqualTo(bookPurchase().getPurchaseType());
                    assertThat(purchase.getId()).isNotNull();
                })
                .expectComplete()
                .verify();
    }
}