package org.basic.chapter8;

@FunctionalInterface
public interface ValidateStrategy {
    boolean execute(String str);
}
