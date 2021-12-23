package green.seagull.very.good.purchase

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.domain.PurchaseType
import java.math.BigDecimal
import java.time.LocalDate

object TestDataModel {
    fun samplePurchase() = Purchase(
        null,
        "Oathbringer",
        LocalDate.parse("2021-10-10"),
        PurchaseType.Book,
        BigDecimal("19.00")
    )
}