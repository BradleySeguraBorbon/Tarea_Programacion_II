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
public class Affiliated {

    private String name;
    private String firstLastName;
    private String secondLastName;
    private String folio;
    private Integer age;
    private ArrayList<Account> accounts;
    private String profileImage;
    private String cooperative;
    private Sexo sexo;
    private Integer specialTickets;

    public enum Sexo {
        FEMENINO,
        MASCULINO,
    }

    public Affiliated() {
        accounts = new ArrayList();
        name = firstLastName = secondLastName = folio = profileImage = cooperative = null;
        age = -1;
    }

    public Affiliated(String name, String firstLastName, String secondLastName, int age, Sexo sexo, String cooperative) {
        this(name, firstLastName, secondLastName, age, sexo, null, cooperative);
    }

    public Affiliated(String name, String firstLastName, String secondLastName, Integer age, Sexo sexo, String profileImage, String cooperative) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.sexo = sexo;
        this.profileImage = profileImage;
        this.cooperative = cooperative;
        this.specialTickets = 0;
        accounts = new ArrayList();

        //Creación de FOLIO ÚNICO
        Random random = new Random();
        StringBuilder folioBuilder = new StringBuilder();
        folioBuilder.append(this.name.charAt(0)).append(this.firstLastName.charAt(0)).append(Integer.toString(this.age));

        String randomNum = Integer.toString(random.nextInt(99));
        String auxFolio = folioBuilder.toString() + randomNum;
        for (Affiliated afiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
            while (afiliated.getFolio().equals(auxFolio)) {
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

    public void setAge(Integer age) {
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

    public void setSpecialTickets(Integer specialTickets) {
        this.specialTickets = specialTickets;
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

    public Integer getAge() {
        return this.age;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public Integer getSpecialTickets() {
        return specialTickets;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getAccountTypes() {
        ArrayList<String> accountTypes = new ArrayList();
        for (Account account : this.accounts) {
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

    public void removeAccount(String accountType) {
        for (Account userAccount : accounts) {
            if (userAccount.getType().equals(accountType)) {
                accounts.remove(userAccount);
                return;
            }
        }
    }

    public boolean isAccountRemovable(String accountType) {
        for (Account userAccount : accounts) {
            if (userAccount.getType().equals(accountType)) {
                return userAccount.getBalance() == 0;
            }
        }
        return true;
    }

    public void updateTicketsCount() {
        Integer totalTransactions = 0;
        for (Account account : this.accounts) {
            totalTransactions += account.getHistory().size();
        }
        this.specialTickets = totalTransactions / 3;
    }

    public String toString() {
        return "Name: " + name
                + "\nPrimer Apellido:  " + firstLastName
                + "\nSegundo Apellido:  " + secondLastName
                + "\nEdad: " + Integer.toString(age)
                + " \nFolio: " + folio
                + "\nCooperativa: " + this.cooperative;
    }
}
