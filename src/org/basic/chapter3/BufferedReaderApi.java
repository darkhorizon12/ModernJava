package org.basic.chapter3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BufferedReaderApi {

    public static void main(String[] args) throws IOException {
        String str = process(br -> {
            return br.readLine() + br.readLine() + br.readLine() + br.readLine();
        });
//        System.out.println(str);

        List<Integer> intList = testPredicate(Arrays.asList(1, 2, 3,4 ,5 ,6), (i) -> i > 3);
        System.out.println(intList);
    }

    private static String process(BufferedReaderProcessor brp) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/juonkim/Documents/workspace-inflearn/ModernJava/src/백기선의 스프링 데이터 JPA.txt"))) {
           return brp.process(bufferedReader);
        }
    }

    private static List<Integer> testPredicate(List<Integer> list, IntPredicate predicate) {
        return list.stream().filter(predicate::test).collect(Collectors.toList());
    }
}
