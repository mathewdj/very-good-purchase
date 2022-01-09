package green.seagull.very.good.purchase.test

import green.seagull.very.good.purchase.domain.Purchase
import green.seagull.very.good.purchase.model.PurchaseType
import java.math.BigDecimal
import java.time.LocalDate

object IntegrationTestData {
    fun bookPurchase(): Purchase = Purchase(
        null,
        "Tales of Beedle the Bard",
        LocalDate.of(2017, 1, 1),
        PurchaseType.book, BigDecimal.TEN
    )

    fun ps4Purchase(): Purchase = Purchase(
        null,
        "Normand Salazar",
        LocalDate.of(2021, 9, 20),
        PurchaseType.ps4, BigDecimal.ONE
    )
}