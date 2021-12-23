package green.seagull.very.good.purchase.repository

import green.seagull.very.good.purchase.domain.Purchase
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseCrudRepository : ReactiveCrudRepository<Purchase, String>