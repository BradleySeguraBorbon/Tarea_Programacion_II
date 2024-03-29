/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.Random;


/**
 *
 * @author Bradley
 */
public class Afiliated {
     String name;
     String firstLastName;
     String secondLastName;
     String folio;
     int age;
     ArrayList<Account> accounts;
     String profileImage;
     String cooperative;
     Sexo sexo;
     
     public enum Sexo{
         FEMENINO,
         MASCULINO,
     }

     
     public Afiliated() {
         accounts = new ArrayList();
         name = firstLastName = secondLastName = folio = profileImage = cooperative =  null;
         age = -1;
     }

    public Afiliated(String name, String firstLastName, String secondLastName, int age, Sexo sexp, String cooperative) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.sexo = sexo;
        this.cooperative = cooperative;
        accounts = new ArrayList();
        
        //Creación de FOLIO ÚNICO
        Random random = new Random(); 
        StringBuilder folioBuilder = new StringBuilder();
        folioBuilder.append(this.name.charAt(0)).append(this.firstLastName.charAt(0)).append(Integer.toString(this.age));
        
        String randomNum = Integer.toString(random.nextInt(99));
        String auxFolio = folioBuilder.toString() + randomNum;     
        for(Afiliated afiliated : (ArrayList<Afiliated>)AppContext.getInstance().get("afiliated")) {
            while(afiliated.getFolio().equals(auxFolio)) {
                String newRandomNum = Integer.toString(random.nextInt(99));
                auxFolio.replace(randomNum, newRandomNum);
                randomNum = newRandomNum;
            }
        }
        this.folio = auxFolio;  
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
        this.age = age;
    }

    public void setSexo(Sexo sex) {
        this.sexo = sex;
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
        return this.name;
    }

    public String getFirstLastName() {
        return this.firstLastName;
    }
    
    public String getSecondLastName() {
        return this.secondLastName;
    }
    
    public String getFullName() {
        return this.name + " " + this.firstLastName + " " + this.secondLastName + " ";
    }

    public int getAge() {
        return this.age;
    }
    
    public Sexo getSexo() {
        return this.sexo;
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
    
    public String toString() {
        return "Name: " + name + 
                "\nPrimer Apellido:  " +  firstLastName + 
                "\nSegundo Apellido:  " + secondLastName + 
                "\nEdad: " + Integer.toString(age) + 
                " \nFolio: " + folio + 
                "\nCooperativa: " + this.cooperative;
    }
}
