
public class Main {

    public static void main(String[] args) {
        long deltaTime = 0;
        final int size = 100_000_000;
        Primes p;

        deltaTime = System.currentTimeMillis();
        p = new PrimitivePrimes(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(50000, 50050);

        deltaTime = System.currentTimeMillis();
        p = new Sieve(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(50000, 50050);

        deltaTime = System.currentTimeMillis();
        p = new SuperSieve(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(50000, 50050);

    }

}
