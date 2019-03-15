package org.basic.chapter8;

public class Validator {
    private final ValidateStrategy validateStrategy;

    public Validator(ValidateStrategy validateStrategy) {
        this.validateStrategy = validateStrategy;
    }

    public boolean validate(String str) {
        return validateStrategy.execute(str);
    }
}
