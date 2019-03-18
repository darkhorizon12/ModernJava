package org.basic.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Chapter11 {

    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(createShops().size(), 100),
                    (runnable) -> {
                        Thread t = new Thread(runnable);
                        // Deamon Thread는 자바 프로그램이 종료될 때 일반 스레드도 함께 종료시킨다.
                        t.setDaemon(true);
                        return t;
                    });

    public static void main(String[] args) throws Exception {
//        Shop shop = new Shop("sss");
//        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invacation returned after " + invocationTime + " mecs");
//
//        doSomethingElse();
//
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n", price);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " mecs");

//        long start = System.nanoTime();
//        System.out.println(new Chapter11().findPrices("myPhone"));
//        long duration = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Done in " + duration + " msecs");

//        long start = System.nanoTime();
//        System.out.println(new Chapter11().findDiscountPrices("myPhone"));
//        long duration = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Done findDiscountPrices in " + duration + " msecs");
//        start = System.nanoTime();
//        System.out.println(new Chapter11().findDiscountPricesAsync("myPhone"));
//        duration = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Done findDiscountPricesAsync in " + duration + " msecs");

        long start = System.nanoTime();
        CompletableFuture[] futures = new Chapter11().findPricesStream("myPhone33")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " +
                        ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " +
                ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = createShops().stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findDiscountPrices(String product) {
        return createShops().stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    public List<String> findDiscountPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = getStream(product)
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    // 모든 가격 정보를 받을 때까지 리스트 생성을 기다리지 않는다
    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return getStream(product);
    }

    private Stream<CompletableFuture<String>> getStream(String product) {
        return createShops().stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    private static List<Shop> createShops() {
        return Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BestDelicious"),
                new Shop("BuyItAll")
        );
    }


    // Future로 오래 걸리는 작업을 비동기적으로 실행
    public void executeAsync() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() { // Callable을 executor에 제ㅜㄹ
            @Override
            public Double call() throws Exception {
                // 시간이 오래 걸리는 작업을 다른 스레드에 비동기적으로 실행
                return doSomething();
            }
        });
        // 비동기 작업을 수행하는 동안 다른 작업을 한다
        doSomethingElse();

        try {
            // 비동기 작업 결과. 결과가 준비되어 있지 않으면 호출 스레드가 블록. 여기선 1초 동안만 기다리는 것으로 설정
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 현재 스레드에서 대기 중 인터럽트 발생
        } catch (ExecutionException e) {
            /// 계산 중 예외 발생
        } catch (TimeoutException e) {
            // Future가 완료되기 전에 타임아웃 발생
        }


    }

    private static void doSomethingElse() {
        System.out.println("doSomethingElse()");
    }

    private static Double doSomething() {
        return null;
    }
}
