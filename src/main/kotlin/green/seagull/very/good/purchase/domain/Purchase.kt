package green.seagull.very.good.purchase.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate

@Document
class Purchase {
    @Id
    var id: String? = null

    var name: String
    var date: LocalDate
    var purchaseType: PurchaseType
    var amountDollars: BigDecimal

    constructor(name: String, date: LocalDate, purchaseType: PurchaseType, amountDollars: BigDecimal) {
        this.name = name
        this.date = date
        this.purchaseType = purchaseType
        this.amountDollars = amountDollars
    }

    override fun toString(): String {
        return "Purchase(id='$id', name='$name', date=$date, purchaseType=$purchaseType, amountDollars=$amountDollars)"
    }
}