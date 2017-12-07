package pl.xxlo;

class Fraction {
    //plus
    private int sign = 1;
    private int numerator;
    private int fractional;
    private int denominator;
    
    public Fraction(int fractional, int numerator, int denominator) {
        int f = 0, n = 0, d = 0;
        if(denominator == 0 || numerator == 0) {
            this.denominator = 1;
            this.numerator = 0;
            if(fractional != 0) this.sign = fractional/Math.abs(fractional);
            this.fractional = Math.abs(fractional);
        } else {
            
            if(fractional != 0) f = fractional/Math.abs(fractional);
            n = numerator/Math.abs(numerator);
            d = denominator/Math.abs(denominator); 
            if(f == 0)  sign = n*d;
            else sign = f*d*n;
            
            this.numerator = Math.abs(numerator);
            this.fractional = Math.abs(fractional);
            this.denominator = Math.abs(denominator);
            pretty();
        } 
    }
    
    public Fraction(int fractional) {
        numerator = 0;
        denominator = 1;
        if(fractional < 0) {
            sign = -1;
            this.fractional = Math.abs(fractional);
        } else this.fractional = fractional;
    }
    
    public Fraction(int numerator, int denominator) {
        if(denominator == 0) denominator = 1;
        if(numerator == 0) {
            this.denominator = 1;
            this.sign = 1;
            this.fractional = 0;
        } else {
            if((denominator/Math.abs(denominator))*(numerator/Math.abs(numerator)) < 0)
                sign = -1;
            this.denominator = Math.abs(denominator);
            this.numerator = Math.abs(numerator);
            pretty();
        }
    }
    
    public void multiply(Fraction f) {
       numerator =  (denominator * fractional + numerator) * 
               ((f.denominator) *  (f.fractional) + f.numerator);
       denominator *= f.denominator;
       fractional = 0;
       sign = sign*f.sign;
       pretty();
    }
    
    public void divide(Fraction f) {
        numerator =  (denominator * fractional + numerator) * f.denominator; 
        denominator *= (f.denominator * f.fractional + f.numerator);
        fractional = 0;
        sign = sign*f.sign;
        pretty();
    }
    
    public void add(Fraction f) {
        int gdc = findGDC(denominator, f.denominator);
        int common = denominator*f.denominator/gdc;
        numerator = sign*(fractional*common + numerator*common/denominator) +  
                sign*(f.fractional*common + f.numerator*common/f.denominator);
        denominator = common;
        fractional = 0;
        if(numerator < 0 ) {
            numerator = Math.abs(numerator);
            sign = -1;
        }
        pretty();
    }
    
    public void subtract(Fraction f) {
        int gdc = findGDC(denominator, f.denominator);
        int common = denominator*f.denominator/gdc;
        numerator = sign*(fractional*common + numerator*common/denominator) -  
                sign*(f.fractional*common + f.numerator*common/f.denominator);
        denominator = common;
        fractional = 0;
        if(numerator < 0 ) {
            numerator = Math.abs(numerator);
            sign = -1;
        }
        pretty();
    }
    
    public int getDenominator() { return denominator;}
    public int getNumerator() { return numerator; }
    public int getFrational() { return fractional; }
    public int getSign() { return sign; }
    
    
    public String toString() {
        String s = " ";
        if(sign < 0) s += "- ";
        return s + fractional + " & " + numerator + " / " + denominator;
    }
    
    private void pretty() {
        denomine();
        if(numerator != 0) gcd();
    }
    
    private void gcd() {
        int g = findGDC(numerator, denominator);   
        numerator /= g;
        denominator /= g;
    }
    
    private int findGDC(int a, int b) {
        int c = 0;
        if(a < b) {
           c = b;
           b = a;
           a = c;
        }
        c = a % b;
        while(c != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return b;
    }
    
    private void denomine() {
        fractional += numerator / denominator;
        numerator %= denominator;
    }
}

public class MainFractions {
	
	public static void main(String[] args) {
       Fraction f1 = new Fraction(35);
       Fraction f2 = new Fraction(1200, 625);
       Fraction f3 = new Fraction(4, 48, 9);
       System.out.println("f1 " + f1.toString());
       System.out.println("f2 "  + f2.toString());
       System.out.println("f3 " + f3.toString());
       f1 = new Fraction(1, 1, 2);
       f2 = new Fraction(2, 1, 3);
       System.out.print(f1.toString() + " * " + f2.toString() + " = ");
       f1.multiply(f2);
       System.out.println(f1.toString());
       
       f1 = new Fraction(1, 1, 2);
       System.out.print(f1.toString() + " / " + f2.toString() + " = ");
       f1.divide(f2);
       System.out.println(f1.toString());
       
       f1 = new Fraction(1, 1, 2);
       System.out.print(f1.toString() + " + " + f2.toString() + " = ");
       f1.add(f2);
       //3 i 5/6
       System.out.println(f1.toString());
       
       f1 = new Fraction(1, 1, 2);
       System.out.print(f1.toString() + " - " + f2.toString() + " = ");
       f1.subtract(f2);
       //-5/6
       System.out.println(f1.toString());
       
	}
}
