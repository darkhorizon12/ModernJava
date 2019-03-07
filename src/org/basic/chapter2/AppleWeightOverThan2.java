package org.basic.chapter2;

public class AppleWeightOverThan2 implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 2d;
    }
}
