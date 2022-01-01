package green.seagull.very.good.purchase.config.test;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.domain.PurchaseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IntegrationTestData {
    public static Purchase bookPurchase() {
        return new Purchase(null,
                "Tales of Beedle the Bard",
                LocalDate.of(2017, 1, 1),
                PurchaseType.Book, BigDecimal.TEN);
    }

    public static Purchase ps4Purchase() {
        return new Purchase(null,
                "Normand Salazar",
                LocalDate.of(2021, 9, 20),
                PurchaseType.PS4, BigDecimal.ONE);
    }
}
