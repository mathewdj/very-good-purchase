package green.seagull.very.good.purchase.service

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.repository.PurchaseCrudRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
open class PurchaseService {

    @Autowired
    private lateinit var purchaseCrudRepository: PurchaseCrudRepository

    open fun save(purchase: Purchase): Mono<Purchase> {
        log.info("Saving purchase: $purchase")
        return purchaseCrudRepository.save(purchase)
    }

    open fun update(purchase: Purchase): Mono<Purchase> {
        log.info("Updating purchase: $purchase")
        return purchaseCrudRepository.save(purchase)
    }

    open fun findAll(): Flux<Purchase> = purchaseCrudRepository.findAll()

    open fun findById(id: String) = purchaseCrudRepository.findById(id)

    open fun delete(purchaseId: String): Mono<Void> {
        log.info("Deleting purchaseId: $purchaseId")
        return purchaseCrudRepository.deleteById(purchaseId)
    }

    companion object {
        private val log = LoggerFactory.getLogger(PurchaseService::class.java)
    }
}