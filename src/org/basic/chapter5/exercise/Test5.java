package org.basic.chapter5.exercise;

import org.basic.Trader;
import org.basic.Transaction;
import org.basic.chapter6.CreateTras;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Test5 {

    public static void main(String[] args) {
        List<Transaction> transactions = CreateTras.getTransactions();

        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue).reversed())
                .collect(toList());

        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .collect(toList());


        boolean present = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));

        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        transactions.stream()
                .max(comparing(Transaction::getValue));

        transactions.stream()
                .min(comparing(Transaction::getValue));


    }

}
