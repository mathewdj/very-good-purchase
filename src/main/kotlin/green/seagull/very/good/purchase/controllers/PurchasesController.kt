package green.seagull.very.good.purchase.controllers

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.service.PurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@Controller
class PurchasesController {

    @Autowired
    private lateinit var purchaseService: PurchaseService

    @GetMapping("/api/purchases")
    @ResponseBody
    fun findAll(): Flux<Purchase> = purchaseService.findAll()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/purchases")
    fun save(@RequestBody purchase: Purchase) = purchaseService.save(purchase)

    @DeleteMapping("/api/purchases/{purchaseId}")
    @ResponseBody
    fun delete(@PathVariable purchaseId: String) = purchaseService.delete(purchaseId)
}