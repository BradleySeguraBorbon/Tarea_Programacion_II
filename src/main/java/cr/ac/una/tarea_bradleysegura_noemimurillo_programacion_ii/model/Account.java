/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

/**
 *
 * @author Bradley
 */
public class Account {
    String type;
    String accountNumber;
    Double balance;

    public Account(String type) {
        this.type = type;
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
