package org.example.chapter6;

public class Menu {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;

	public Menu(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Dish{");
		sb.append("name='").append(name).append('\'');
		sb.append(", vegetarian=").append(vegetarian);
		sb.append(", calories=").append(calories);
		sb.append(", type=").append(type);
		sb.append('}');
		return sb.toString();
	}

	public enum Type {
		MEAT, FISH, OTHER
	}
}

