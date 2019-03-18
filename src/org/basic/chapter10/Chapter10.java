package org.basic.chapter10;

import org.basic.CreateDish;
import org.basic.Dish;

import java.util.*;

public class Chapter10 {
    public static void main(String[] args) {
        Dish dish = null;
//        Optional<Dish> opt = Optional.of(dish);
        // opt. 으로 접근하면 NullPointerException 발생

        Map<String, Dish> map = new HashMap<>();
        map.put("aaa", new Dish("aaa", false, 300, Dish.Type.MEAT));
        map.put("bbb", new Dish("bbb", false, 400, Dish.Type.OTHER));
        map.put("ccc", new Dish("ccc", true, 400, Dish.Type.MEAT));
        map.put("ddd", new Dish("ddd", false, 400, Dish.Type.OTHER));

        Optional<Dish> obj = Optional.ofNullable(map.get("ddd"));
        obj.orElse(new Dish("ddd", false, 400, Dish.Type.OTHER));

        String str = "sss";
        Optional<Integer> rst = stringToInt(str);
        System.out.println(rst);

    }

    // Optional에서는 기본 특화형이 오히려 성능이 떨어질 수 있어 Optional을 사용하는 게 낫다.
    public static Optional<Integer> stringToInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(Chapter10::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }


}
