package green.seagull.very.good.purchase.controllers;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(path = "/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @GetMapping()
    public @ResponseBody Flux<Purchase> getAllPurchases() {
        return purchaseCrudRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Mono<Purchase> save(Purchase purchase) {
        return purchaseCrudRepository.save(purchase);
    }
}
