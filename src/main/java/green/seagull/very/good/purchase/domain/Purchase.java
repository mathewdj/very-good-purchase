package green.seagull.very.good.purchase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", purchaseType=" + purchaseType +
                ", amountDollars=" + amountDollars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(name, purchase.name)
                && Objects.equals(date, purchase.date)
                && purchaseType == purchase.purchaseType
                && Objects.equals(amountDollars, purchase.amountDollars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, purchaseType, amountDollars);
    }

}
