package org.basic.chapter11;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopProduct() + " price is " +
            Discount.apply(quote.getPrice(), quote.getCode());
    }

    private static String apply(double price, Code code) {
        Shop.delay();
        return String.valueOf(price * (100 - code.percentage) / 100);
    }
}
