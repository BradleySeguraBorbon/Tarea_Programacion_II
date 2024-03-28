/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import java.util.ArrayList;
import javafx.scene.image.Image;


/**
 *
 * @author Bradley
 */
public class Afiliated {
     String name;
     String firstLastName;
     String secondLastName;
     String folio;
     int Age;
     ArrayList<Account> accounts;
     String profileImage;
     String cooperative;
     Sexo sex;
     public enum Sexo{
         FEMENINO,
         MASCULINO,
     }

     
     public Afiliated() {
         accounts = new ArrayList();
     }

    public Afiliated(String name, String firstLastName, String secondLastName, int age, Sexo sex, String cooperative) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.Age = age;
        this.sex = sex;
        this.cooperative = cooperative;
        accounts = new ArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }
    
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public void setAge(int age) {
        this.Age = age;
    }
    
    public void setSexo(Sexo sex){
        this.sex = sex;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setProfileImage(String profileImage) {
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

    public String getFirstLastName() {
        return this.firstLastName;
    }
    
    public String getSecondLastName() {
        return secondLastName;
    }
    
    public String getFullName() {
        return this.name + " " + this.firstLastName + " " + this.secondLastName + " ";
    }

    public int getAge() {
        return Age;
    }
    
    public Sexo getSex(){
        return sex;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    
    public ArrayList<String> getAccountTypes() {
        ArrayList<String> accountTypes = new ArrayList();
        for(Account account : this.accounts) {
            accountTypes.add(account.getType());
        }
        return accountTypes;
    }

    public String getProfileImage() {
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
    
    public boolean isAccountRemovable(String accountType) {
        for(Account userAccount : accounts) {
            if(userAccount.getType().equals(accountType)) {
                return userAccount.getBalance() == 0;
            }
        }
        return false;
    }
}
