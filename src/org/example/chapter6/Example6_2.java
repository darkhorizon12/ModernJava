package org.example.chapter6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class Example6_2 {
	public static void main(String[] args) {
		List<Menu> menus = returnMenuList();

		Optional<Menu> minMenu = menus.stream().collect(minBy(comparingInt(Menu::getCalories)));

		double averageCalory = menus.stream().collect(averagingInt(Menu::getCalories));

		IntSummaryStatistics menuStatics = menus.stream().collect(summarizingInt(Menu::getCalories));
		System.out.println("menuStatics.getAverage() = [" + menuStatics.getAverage() + "]");

		String shortName = menus.stream().map(Menu::getName).collect(joining(", "));
		System.out.println("shortName = [" + shortName + "]");

		int totalCalories1 = menus.stream().collect(reducing(0, Menu::getCalories, Integer::sum));
		int totalCalories2 = menus.stream().mapToInt(Menu::getCalories).sum();
		System.out.println("totalCalories1 = [" + totalCalories1 + "]");
		System.out.println("totalCalories2 = [" + totalCalories2 + "]");

	}

	private static List<Menu> returnMenuList() {
		return 	Arrays.asList(
				new Menu("pork", false, 800, Menu.Type.MEAT),
				new Menu("beef", false, 700, Menu.Type.MEAT),
				new Menu("chicken", false, 400, Menu.Type.MEAT),
				new Menu("french fries", true, 530, Menu.Type.OTHER),
				new Menu("rice", true, 350, Menu.Type.OTHER),
				new Menu("season fruit", true, 120, Menu.Type.OTHER),
				new Menu("pizza", true, 550, Menu.Type.OTHER),
				new Menu("prawns", false, 890, Menu.Type.FISH),
				new Menu("salmon", false, 450, Menu.Type.FISH),
				new Menu("soup", true, 650, Menu.Type.OTHER)
		);
	}
}

