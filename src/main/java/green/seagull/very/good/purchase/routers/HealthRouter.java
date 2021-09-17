package green.seagull.very.good.purchase.routers;

import green.seagull.very.good.purchase.handlers.HealthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class HealthRouter {
    @Bean
    public RouterFunction<ServerResponse> route(HealthHandler healthHandler) {
        return RouterFunctions.
                route(GET("/health").and(accept(MediaType.APPLICATION_JSON)), healthHandler::health);
    }
}
