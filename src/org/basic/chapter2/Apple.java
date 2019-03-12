package org.basic.chapter2;

public class Apple {

    private ColorEnum color;

    private double weight;

    private String origin;

    public Apple(ColorEnum color, double weight, String origin) {
        this.color = color;
        this.weight = weight;
        this.origin = origin;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Apple{");
        sb.append("color='").append(color).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", origin='").append(origin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
