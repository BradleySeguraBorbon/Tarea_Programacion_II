/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;
import java.util.Random;

/**
 *
 * @author Bradley
 */
public class Account {
    String type;
    String accountNumber;
    Double balance;

    public Account() {
        
    }
    
    public Account(String type) {
        Random randomFactory = new Random();
        
        this.type = type;
        int sectionA = randomFactory.nextInt(100, 999);      
        int sectionB = randomFactory.nextInt(10, 99);
        int sectionC = randomFactory.nextInt(1000, 9999);
        
        this.accountNumber = Integer.toString(sectionA) + "-" + Integer.toString(sectionB) + "-" + Integer.toString(sectionC);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }
    
    public void addBalance(Double amount) {
        balance += amount;
    }
    
    public Double substractBalance(Double amount) {
        balance -= amount;
        return amount;
    }
}
