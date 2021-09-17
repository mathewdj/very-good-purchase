package green.seagull.very.good.purchase.routers;

import green.seagull.very.good.purchase.domain.Health;
import green.seagull.very.good.purchase.domain.HealthColours;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHealthEndpoint() {
        webTestClient.get().uri("/health")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Health.class).value(health -> {
                    assertThat(health.healthColour()).isEqualTo(HealthColours.Green);
                });
    }

}