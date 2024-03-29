/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Random;
import java.text.SimpleDateFormat;
/**
 *
 * @author Bradley
 */
public class Transaction {
    protected String transactionID;
    protected String transactionTime;
    protected Double amount;
    protected Affiliated afiliated;
    protected Action action;        
            
    public static enum Action { RETIRO, DEPOSITO }
    
    public Transaction() {
        this.transactionID = null;
        this.transactionTime = null;
        this.amount = 0.0d;
        this.afiliated = null;
        this.action = null;
    }
    
    public Transaction(Double amount, Affiliated afiliated, Action action) {
        Random randomGenerator = new Random();
        this.transactionID = Integer.toString((randomGenerator.nextInt(100000000)));
        this.transactionTime = LocalDateTime.now().toString();
        this.amount = amount;
        this.afiliated = afiliated;
        this.action = action;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime.toString();
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAffiliated(Affiliated afiliated) {
        this.afiliated = afiliated;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public Double getAmount() {
        return amount;
    }

    public Affiliated getAffiliated() {
        return afiliated;
    }
    
    public String getAffiliatedName() {
        return afiliated.getFullName();
    }
    
    public String getAffiliatedFolio() {
        return afiliated.getFolio();
    }

    public Action getAction() {
        return action;
    }
    
    
}
