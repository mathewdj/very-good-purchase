package green.seagull.very.good.purchase.controllers;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(path = "/api/purchases")
    public @ResponseBody Flux<Purchase> getAllPurchases() {
        return purchaseService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/api/purchases")
    public @ResponseBody Mono<Purchase> save(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @DeleteMapping(path = "/api/purchases/{purchaseId}")
    public @ResponseBody Mono<Void> delete(@PathVariable String purchaseId) {
        return purchaseService.delete(purchaseId);
    }
}
