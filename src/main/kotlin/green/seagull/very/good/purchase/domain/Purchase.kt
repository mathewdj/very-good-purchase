package green.seagull.very.good.purchase.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate

@Document
data class Purchase(
    @Id var id: String? = null,
    var name: String,
    var date: LocalDate,
    var purchaseType: PurchaseType,
    var amountDollars: BigDecimal
)
