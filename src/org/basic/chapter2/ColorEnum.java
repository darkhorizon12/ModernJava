package org.basic.chapter2;

public enum ColorEnum {
    RED("red"),
    WHITE("white"),
    YELLOW("yellow"),
    GREEN("green"),
    BLACK("black");

    private final String name;

    ColorEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
