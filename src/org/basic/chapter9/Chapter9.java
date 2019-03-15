package org.basic.chapter9;

public class Chapter9 implements B, A{
    public void hello() {
        A.super.hello();
    }
    public static void main(String[] args) {
        new Chapter9().hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
interface B {
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C implements A {}
//interface A {
//    default void hello() {
//        System.out.println("Hello from A");
//    }
//}
