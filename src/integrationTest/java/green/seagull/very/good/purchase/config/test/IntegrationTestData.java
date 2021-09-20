package green.seagull.very.good.purchase.config.test;

import green.seagull.very.good.purchase.domain.Purchase;
import green.seagull.very.good.purchase.domain.PurchaseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IntegrationTestData {
    public static Purchase bookPurchase() {
        return new Purchase("Tales of Beedle the Bard",
                LocalDate.of(2017, 1, 1),
                PurchaseType.Book, BigDecimal.TEN);
    }
}
