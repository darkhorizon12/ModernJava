package org.basic.chapter8;

import org.basic.CreateDish;
import org.basic.Dish;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.toList;

public class Chapter8 {
    public static void main(String[] args) {

        List<Dish> menus = CreateDish.createDishes();
//        menus.stream()
//                .filter(d -> d.getCalories() > 400)
//                .map(Dish::getName)
//                .collect(toList());

        // ** 전략패턴: 추상화된 패턴을 구축해두고, 각 전략에 따라 패턴의 구현을 달리
        Validator strategy1 = new Validator(str -> str.matches("[a-z]+"));
        boolean rst1 = strategy1.validate("aaaa");
        Validator strategy2 = new Validator(str -> str.matches("\\d+"));
        boolean rst2 = strategy2.validate("aaaa");

        // ** 템플릿 메서드: 이 알고리즘을 사용하고 싶은데 그대로는 안되니 조금은 고쳐 사용?
        // ** 옵저ㅂ: 어떤 이벤트가 발생했을 때 한 객체가 다른 객체리스트에게 자동으로 알림을 하는?
        // ** 의무체인: 한 객체가 작업을 처리한 후 다른 객체로 결과를 전달하는?
        UnaryOperator<String> headerProcessing = text -> "From Raul, mari and Alan: " + text;
        UnaryOperator<String> spellCheckProcessing = text -> text.replaceAll("lambdas", "lamdas");
        Function<String, String> pipeLine = headerProcessing.andThen(spellCheckProcessing);
        String result = pipeLine.apply("Aren't lamdas really sexy?!");

        // ** 팩토리: 인스턴스 로직을 클라이언트에 노출시키지 않음

        menus.stream()
                .filter(d -> d.getCalories() >400)
                .peek(System.out::println)
                .map(Dish::getName)
                .peek(System.out::println)
                .collect(toList());
    }
}
