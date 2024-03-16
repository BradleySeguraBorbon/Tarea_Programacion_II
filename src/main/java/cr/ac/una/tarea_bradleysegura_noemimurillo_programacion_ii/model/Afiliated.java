/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Bradley
 */
public class Afiliated {
     String name;
     String surname;
     String folio;
     Date birthDate;
     ArrayList<Account> accounts;
     Image profileImage;
     String cooperative;
     
     public Afiliated() {
         accounts = new ArrayList();
     }

    public Afiliated(String name, String surname, Date birthDate, String cooperative) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.cooperative = cooperative;
        accounts = new ArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public void setCooperative(String cooperative) {
        this.cooperative = cooperative;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public String getCooperative() {
        return cooperative;
    }

    public String getFolio() {
        return folio;
    }
     
    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }
    
    public void removeAccount(String accountNumber) {
        for(Account userAccount : accounts)
            if(userAccount.getAccountNumber().equals(accountNumber))
                accounts.remove(userAccount);
    }
}
