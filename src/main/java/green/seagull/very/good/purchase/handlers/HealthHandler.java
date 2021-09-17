package green.seagull.very.good.purchase.handlers;

import green.seagull.very.good.purchase.domain.HealthColours;
import green.seagull.very.good.purchase.domain.Health;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HealthHandler {
    public Mono<ServerResponse> health(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Health(HealthColours.Green)));
    }
}
