package org.basic.chapter11;

public class Quote {

    private final String shopProduct;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopProduct, double price, Discount.Code code) {
        this.shopProduct = shopProduct;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");

        String shopProduct = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);

        return new Quote(shopProduct, price, code);
    }

    public String getShopProduct() {
        return shopProduct;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
