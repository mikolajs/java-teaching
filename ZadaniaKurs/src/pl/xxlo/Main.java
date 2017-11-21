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
    public A clone() {
        return new B();
    }
}


class Main {
    public static void main(String args[]) {
        /*B b = new B();
        b.callme();
        b.callmetoo();
        A a = new B();
        a.callmetoo();
        a.callme()*/;
        String a = "Hej";
        String b = "Hejka";
        b = b.substring(0,3);
        System.out.println(a + " ?= " + b);
        System.out.println(b.equals(a));
    }
}
