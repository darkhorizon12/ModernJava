package org.basic.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    private final String product;

    private static final Random random = new Random();

    public Shop(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                new Random().nextInt(Discount.Code.values().length)
                ];
        return String.format("%s:%.2f:%s", this.product, price, code);
    }

    // calculatePrice 호출하면 비동기 동작이 완료될 때까지 1초동안 블록
    public double calculatePrice(String product) {
        randomDelay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        /*

        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                // 다른 스레드에서 비동기적으로 수행
                double price = calculatePrice(product);
                // 계산이 완료되면 Future에 값을 설정
                futurePrice.complete(price);
            } catch (Exception ex) {
                // 도중에 에러가 발생하면 발생한 에러를 포함해서 Future를 종료한다.
                futurePrice.isCompletedExceptionally();
            }
        }).start();

        // 계산이 끝나길 기다리지 않고 Future를 리턴
        return futurePrice;

        */
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
