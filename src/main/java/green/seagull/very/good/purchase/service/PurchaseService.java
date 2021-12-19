package green.seagull.very.good.purchase.service;

import green.seagull.very.good.purchase.controllers.PurchaseController;
import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurchaseService {
    private Logger log = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    public Mono<Purchase> save(Purchase purchase) {
        log.info("Saving purchase: " + purchase);
        return purchaseCrudRepository.save(purchase);
    }

    public Flux<Purchase> findAll() {
        return purchaseCrudRepository.findAll();
    }
}
