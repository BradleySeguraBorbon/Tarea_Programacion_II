/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Bradley
 */
public class Transaction {

    protected String transactionID;
    protected String transactionTime;
    protected Double amount;
    protected String affiliatedFolio;
    protected String affiliatedFullName;
    protected String accountType;
    protected Action action;

    public static enum Action {
        RETIRO, DEPOSITO
    }

    public Transaction() {
        this.transactionID = null;
        this.transactionTime = null;
        this.amount = 0.0d;
        this.affiliatedFolio = null;
        this.action = null;
    }

    public Transaction(Double amount, String affiliatedFolio, String affiliatedFullName, String accountType, Action action) {
        Random randomGenerator = new Random();
        this.transactionID = Integer.toString((randomGenerator.nextInt(100000000)));
        this.transactionTime = LocalDateTime.now().toString();
        this.amount = amount;
        this.affiliatedFolio = affiliatedFolio;
        this.affiliatedFullName = affiliatedFullName;
        this.accountType = accountType;
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

    public void setAffiliated(String affiliatedFolio) {
        this.affiliatedFolio = affiliatedFolio;
    }
    
    public void setAccount(String accountType) {
        this.accountType = accountType;
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

    public String getAccountType() {
        return accountType;
    }

    public String getAffiliatedFolio() {
        return affiliatedFolio;
    }

    public String getAffiliatedName() {
        return affiliatedFullName;
    }

    public Action getAction() {
        return action;
    }

}
