/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Bradley
 */
public class Account {
    String type;
    String accountNumber;
    Double balance;
    ArrayList<Transaction> history;

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
    
    public void setHistory(ArrayList<Transaction> history) {
        this.history = history;
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
    
    public ArrayList<Transaction> getHistory() {
        return history;
    }
    
    public void deposit(Double amount) {
        balance += amount;
    }
    
    public Double withdraw(Double amount) {
        balance -= amount;
        return amount;
    }
    
    public Double makeTransaction(Transaction movement) {
        this.history.add(movement);
        if(movement.getAction() == Transaction.Action.DEPOSITO) {
            deposit(movement.getAmount());
        }
        else {
            return withdraw(movement.getAmount());
        }
        return null;    
    }
}
