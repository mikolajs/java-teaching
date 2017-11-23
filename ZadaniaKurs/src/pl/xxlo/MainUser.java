package pl.xxlo;

abstract class User {
    String firstName;
    String surName;
    int age;
    String phone;
    String address;
    String login;
    String password;
    
    public boolean login(String passphrase) {
        return (passphrase.equals(password));
    }
    
    public void setPersonalData(String first, String last, int age, 
            String address, String login) {
        this.firstName = first;
        this.surName = last;
        this.age = age;
        this.address = address;
        this.login = login;
    }
    
    public boolean setPassword(String pass) {
        pass = pass.trim();
        if(pass.length() > 5) {
            this.password = pass; 
            return true;
        } else return false;
    }
}

class Client extends User {
    String shipment;
    double currency;
    
    public void  buy(double amount) {
        currency -= amount;
    }
    
    public void payIn(double amount) {
        currency += amount;
    }
}

class Employee extends User {
    String department = "";
    double earned = 0.0;
    double account = 0.0;
    
    public void sale(Client client, double amount) {
        client.buy(amount);
        earned += amount;
    }
    
    public double extra() {
        account += earned*0.05;
        double e = earned*0.95;
        earned = 0;
        return e;
    }
    
    public double payOutAll() {
        double a = account;
        account = 0.0;
        return a;
    }
}

class Director extends User {
    double money = 0;
    double spend = 0;
    
    public void mkExtra(Employee e) {
        money +=  e.extra();
    }
    public void pay(Employee e) {
        spend = e.payOutAll();
    }
}

public class MainUser {
    public static void main(String[] args) {
        System.out.println("User demo");
        Client client1;
        client1 = new Client();
        client1.setPersonalData("Jan", "Kowalski", 99, "bezdomny", "mały");
        
        client1.setPassword("tajne");

        
    }
}
