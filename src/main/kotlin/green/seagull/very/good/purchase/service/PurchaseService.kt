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

    open fun tsv(): Flux<String> {
        return findAllSortDateDesc()
            .map {
                val date = with (it.date) {
                    val monthZeroPadded = monthValue.toString().padStart(2, '0')
                    val dayOfMonthZeroPadded = dayOfMonth.toString().padStart(2, '0')
                    "$year-$monthZeroPadded-$dayOfMonthZeroPadded"
                }
                with (it) {
                    "$date\t${amountDollars}\t${name}\t${purchaseType.toString().lowercase()}\n"
                }
            }
    }

    open fun findAllSortDateDesc(): Flux<Purchase> =
        findAll()
        .sort { p1, p2 -> p2.date.compareTo(p1.date) }

    open fun findById(id: String) = purchaseCrudRepository.findById(id)

    open fun delete(purchaseId: String): Mono<Void> {
        log.info("Deleting purchaseId: $purchaseId")
        return purchaseCrudRepository.deleteById(purchaseId)
    }

    companion object {
        private val log = LoggerFactory.getLogger(PurchaseService::class.java)
    }
}