package green.seagull.very.good.purchase.config.routers;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static green.seagull.very.good.purchase.config.test.IntegrationTestData.bookPurchase;
import static green.seagull.very.good.purchase.config.test.IntegrationTestData.ps4Purchase;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
class PurchaseRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @BeforeEach
    public void cleanup() {
        purchaseCrudRepository.deleteAll().block();
    }

    @Test
    public void testGetAllPurchasesEndpoint() {
        purchaseCrudRepository.save(bookPurchase()).block();

        webTestClient.get().uri("/api/purchases")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Purchase.class).value(purchases -> {
                    var purchase = purchases.stream().findFirst().orElseThrow();
                    assertThat(purchase.getName()).isEqualTo(bookPurchase().getName());
                });
    }

    @Test
    public void testSaveNewPurchaseEndpoint() {
        webTestClient.post().uri("/api/purchases")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(ps4Purchase())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Purchase.class).value(purchase -> assertThat(purchase.getId()).isNotNull());

        var purchase = purchaseCrudRepository.findAll().toStream().findFirst().orElseThrow();
        assertThat(purchase)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ps4Purchase());
    }
}
