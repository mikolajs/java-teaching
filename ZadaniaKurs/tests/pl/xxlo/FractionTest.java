package pl.xxlo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FractionTest {

    Fraction f1;
    Fraction f2;
    @Test
    void test() {
        assertEquals(2, 1+1);
    }
    
    @Test
    void constructor1() {
        f1 = new Fraction(0); 
        assertEquals(0, f1.getFrational());
        assertEquals(0, f1.getNumerator());
        assertEquals(1, f1.getDenominator());
        assertEquals(1, f1.getSign());
        
        f1 = new Fraction(-9);
        assertEquals(9, f1.getFrational());
        assertEquals(0, f1.getNumerator());
        assertEquals(1, f1.getDenominator());
        assertEquals(-1, f1.getSign());
    }

    @Test
    void constructor2() {
        f1 = new Fraction(0, -4); 
        assertEquals(0, f1.getFrational());
        assertEquals(0, f1.getNumerator());
        assertEquals(1, f1.getDenominator());
        assertEquals(1, f1.getSign());
        
        f1 = new Fraction(12, -9);
        assertEquals(1, f1.getFrational());
        assertEquals(1, f1.getNumerator());
        assertEquals(3, f1.getDenominator());
        assertEquals(-1, f1.getSign());
    }
    
    @Test
    void constructor3() {
        f1 = new Fraction(1, 0, -4); 
        assertEquals(1, f1.getFrational());
        assertEquals(0, f1.getNumerator());
        assertEquals(1, f1.getDenominator());
        assertEquals(1, f1.getSign());
        
        f1 = new Fraction(3, -150, -45);
        assertEquals(6, f1.getFrational());
        assertEquals(1, f1.getNumerator());
        assertEquals(3, f1.getDenominator());
        assertEquals(1, f1.getSign());
        
        f1 = new Fraction(0, 9, -45);
        assertEquals(0, f1.getFrational());
        assertEquals(1, f1.getNumerator());
        assertEquals(5, f1.getDenominator());
        assertEquals(-1, f1.getSign());
        
        f1 = new Fraction(-2, 99, -45);
        assertEquals(4, f1.getFrational());
        assertEquals(1, f1.getNumerator());
        assertEquals(5, f1.getDenominator());
        assertEquals(1, f1.getSign());
        
        f1 = new Fraction(-2, -99, -45);
        assertEquals(4, f1.getFrational());
        assertEquals(1, f1.getNumerator());
        assertEquals(5, f1.getDenominator());
        assertEquals(-1, f1.getSign());
    }
    
    @Test
    void multiplyTest() {
        f1 = new Fraction(4);
        f2 = new Fraction(5);
        f1.multiply(f2);
        assertAll("positive non fraction",
                () -> assertEquals(20, f1.getFrational()),
                () -> assertEquals(0, f1.getNumerator()),
                () -> assertEquals(1, f1.getDenominator()), 
                () -> assertEquals(1, f1.getSign())
                );
        f1 = new Fraction(0, 5, 7);
        f2 = new Fraction(-1, 2, 5);
        f1.multiply(f2);
        assertAll("positive non fraction",
                () -> assertEquals(1, f1.getFrational()),
                () -> assertEquals(0, f1.getNumerator()),
                () -> assertEquals(1, f1.getDenominator()), 
                () -> assertEquals(-1, f1.getSign())
                );
        
        f1 = new Fraction(4);
        f2 = new Fraction(5);
        f1.multiply(f2);
        assertAll("positive non fraction",
                () -> assertEquals(20, f1.getFrational()),
                () -> assertEquals(0, f1.getNumerator()),
                () -> assertEquals(1, f1.getDenominator()), 
                () -> assertEquals(1, f1.getSign())
                );
    }
    
    @Test
    void divideTest() {
        
    }
    
    @Test
    void sumTest() {
        
    }
    
    @Test
    void subtractTest() {
        
    }
    
}
