package green.seagull.very.good.purchase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document
public class Purchase {
    @Id
    private String id;

    private String name;
    private LocalDate date;
    private PurchaseType purchaseType;
    private BigDecimal amountDollars;

    public Purchase(String name, LocalDate date, PurchaseType purchaseType, BigDecimal amountDollars) {
        this.name = name;
        this.date = date;
        this.purchaseType = purchaseType;
        this.amountDollars = amountDollars;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public BigDecimal getAmountDollars() {
        return amountDollars;
    }
}
