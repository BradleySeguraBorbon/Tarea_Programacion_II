/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Bradley
 */
public class BoxDeposit extends Transaction {

    private HashMap<Denomination, Integer> depositDenomination;

    public enum Denomination {
        CINCO, DIEZ, VEINTICINCO, CINCUENTA, CIEN, QUINIENTOS, MIL, DOSMIL, CINCOMIL, DIEZMIL, VEINTEMIL
    }

    public BoxDeposit() {
        depositDenomination = new HashMap<>();
        depositDenomination.put(Denomination.CINCO, 0);
        depositDenomination.put(Denomination.DIEZ, 0);
        depositDenomination.put(Denomination.VEINTICINCO, 0);
        depositDenomination.put(Denomination.CINCUENTA, 0);
        depositDenomination.put(Denomination.CIEN, 0);
        depositDenomination.put(Denomination.QUINIENTOS, 0);
        depositDenomination.put(Denomination.MIL, 0);
        depositDenomination.put(Denomination.DOSMIL, 0);
        depositDenomination.put(Denomination.CINCOMIL, 0);
        depositDenomination.put(Denomination.DIEZMIL, 0);
        depositDenomination.put(Denomination.VEINTEMIL, 0);
    }

    public BoxDeposit(Double amount, String affiliatedFolio, String affiliatedFullName, String accountType, Action action) {
        super(amount, affiliatedFolio, affiliatedFullName, accountType, action);
        depositDenomination = new HashMap<>();

        depositDenomination.put(Denomination.CINCO, 0);
        depositDenomination.put(Denomination.DIEZ, 0);
        depositDenomination.put(Denomination.VEINTICINCO, 0);
        depositDenomination.put(Denomination.CINCUENTA, 0);
        depositDenomination.put(Denomination.CIEN, 0);
        depositDenomination.put(Denomination.QUINIENTOS, 0);
        depositDenomination.put(Denomination.MIL, 0);
        depositDenomination.put(Denomination.DOSMIL, 0);
        depositDenomination.put(Denomination.CINCOMIL, 0);
        depositDenomination.put(Denomination.DIEZMIL, 0);
        depositDenomination.put(Denomination.VEINTEMIL, 0);
    }

    public void setDepositDenomination(HashMap<Denomination, Integer> newDenomination) {
        this.depositDenomination = newDenomination;
    }

    public HashMap<Denomination, Integer> getDepositDenomination() {
        return depositDenomination;
    }
    
    public ArrayList<Denomination> getCoins() {
        return (ArrayList<Denomination>)Arrays.asList(Denomination.class.getEnumConstants());
    }

    public void setDenomination(Denomination moneda, Integer quantity) {
        depositDenomination.put(moneda, quantity);
    }

    public void calculateTotal() {
        this.amount = 0.d;
        for (Denomination moneda : this.depositDenomination.keySet()) {
            switch (moneda) {
                case CINCO -> this.amount += (depositDenomination.get(moneda) * 5);
                case DIEZ -> this.amount += (depositDenomination.get(moneda) * 10);
                case VEINTICINCO -> this.amount += (depositDenomination.get(moneda) * 25);
                case CINCUENTA -> this.amount += (depositDenomination.get(moneda) * 50);
                case CIEN -> this.amount += (depositDenomination.get(moneda) * 100);
                case QUINIENTOS -> this.amount += (depositDenomination.get(moneda) * 500);
                case MIL -> this.amount += (depositDenomination.get(moneda) * 1000);
                case DOSMIL -> this.amount += (depositDenomination.get(moneda) * 2000);
                case CINCOMIL -> this.amount += (depositDenomination.get(moneda) * 5000);
                case DIEZMIL -> this.amount += (depositDenomination.get(moneda) * 10000);
                case VEINTEMIL -> this.amount += (depositDenomination.get(moneda) * 20000);
            }
        }
    }

    public int getSpecificDenomination(Denomination moneda) {
        for (Denomination currency : depositDenomination.keySet()) {
            if (currency == moneda) {
                return depositDenomination.get(currency);
            }
        }
        return 0;
    }

   /* public int getCincoColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.CINCO);
    }

    public int getDiezColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.DIEZ);
    }

    public int getVeinticincoColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.VEINTICINCO);
    }

    public int getCincuentaColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.CINCUENTA);
    }

    public int getCienColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.CIEN);
    }

    public int getQuinientosColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.QUINIENTOS);
    }

    public int getMilColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.MIL);
    }

    public int getDosMilColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.DOSMIL);
    }

    public int getCincoMilColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.CINCOMIL);
    }

    public int getDiezMilColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.DIEZMIL);
    }

    public int getVeinteMilColonesDenomination() {
        return (int) depositDenomination.get(BoxDeposit.Denomination.VEINTEMIL);
    } */

    @Override
    public String toString() {
        String objectString;
        objectString = "transactionID: " + this.transactionID + 
                "\ntransactionTime: " + this.transactionTime +
                "\namount: " + this.amount + 
                "\nafiliated: " + getAffiliatedName() + 
                "\naccount: " + this.accountType +
                "\naction: " + this.action.toString() +
                "\nDenominations: ";
        for(Denomination coin : this.depositDenomination.keySet()) {
            objectString += "\n" + coin.toString() + ": " + this.depositDenomination.get(coin).toString();
        }
        return objectString;
    }
}
