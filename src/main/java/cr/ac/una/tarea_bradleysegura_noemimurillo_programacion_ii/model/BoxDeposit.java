/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import java.util.HashMap;

/**
 *
 * @author Bradley
 */
public class BoxDeposit extends Transaction {
    
    HashMap<Denomination, Integer> depositDenomination;
    
     public enum Denomination {
        CINCO, DIEZ, VEINTICINCO, CINCUENTA, CIEN, QUINIENTOS, MIL, DOSMIL, CINCOMIL, DIEZMIL, VEINTEMIL
    }
    
    public BoxDeposit() {
        depositDenomination = new HashMap<Denomination, Integer>();
    }
    
    public void setDepositDenominacion(HashMap<Denomination, Integer> newDenomination) {
        this.depositDenomination = newDenomination;
    }

    public HashMap<Denomination, Integer> getDepositDenomination() {
        return depositDenomination;
    }
    
    public void addDenomination(Denomination moneda, Integer quantity) {
        depositDenomination.put(moneda, quantity);
    }
    
    public Integer getSpecificDenomination(Denomination moneda) {
        for(Denomination currency : depositDenomination.keySet()) {
            if(currency == moneda) {
                return depositDenomination.get(currency);
            }
        }
        return 0;
    }
}
