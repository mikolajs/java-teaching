package pl.xxlo;

abstract class A {
    abstract void callme();

    void callmetoo() {
        System.out.println("To jest konkretna metoda.");
    }
}

class B extends A {
    void callme() {
        System.out.println("Implementacja callme() z klasy B.");
    }
    public Object clone() {
        return new B();
    }
}

class Main {
    public static void main(String args[]) {
        B b = new B();
        b.callme();
        b.callmetoo();
    }
}
