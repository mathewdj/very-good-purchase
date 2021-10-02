package green.seagull.very.good.purchase.controllers;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @GetMapping()
    public @ResponseBody Flux<Purchase> getAllPurchases() {
        return purchaseCrudRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody Mono<Purchase> save(@RequestBody Purchase purchase) {
        return purchaseCrudRepository.save(purchase);
    }
}
