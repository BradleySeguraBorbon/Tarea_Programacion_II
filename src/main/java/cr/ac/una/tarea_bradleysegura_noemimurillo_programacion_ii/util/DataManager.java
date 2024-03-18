/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Bradley
 */
public class DataManager {

    private ArrayList<String> availableAccounts;
    private ArrayList<Afiliated> afiliated;
    private String cooperativeName;
    private Image cooperativeIcon;

    public DataManager() {
        availableAccounts = new ArrayList();
        afiliated = new ArrayList();
    }

    public void setAvailableAccounts(ArrayList<String> availableAccounts) {
        this.availableAccounts = availableAccounts;
    }

    public void setAfiliated(ArrayList<Afiliated> afiliated) {
        this.afiliated = afiliated;
    }

    public void setCooperativeName(String cooperativeName) {
        this.cooperativeName = cooperativeName;
    }

    public void setCooperativeIcon(Image cooperativeIcon) {
        this.cooperativeIcon = cooperativeIcon;
    }
    
     public ArrayList<String> getAvailableAccounts() {
        return availableAccounts;
    }

    public ArrayList<Afiliated> getAfiliated() {
        return afiliated;
    }

    public String getCooperativeName() {
        return cooperativeName;
    }

    public Image getCooperativeIcon() {
        return cooperativeIcon;
    }

    public void addAvailableAccount(String newAccountType) {
        availableAccounts.add(newAccountType);
    }

    public void removeAvailableAccount(String accountType) {
        availableAccounts.remove(accountType);
    }

    public ArrayList<Account> getAfiliatedAccounts(String folio) {
        for (Afiliated afiliated : afiliated) {
            if (afiliated.getFolio().equals(folio)) {
                return afiliated.getAccounts();
            }
        }
        return null;
    }

    public void addAccountToAfiliated(Account newAccount, String folio) {
        for (Afiliated afiliated : afiliated) {
            if (afiliated.getFolio().equals(folio)) {
                afiliated.addAccount(newAccount);
            }
        }
    }

    public void save(String path) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream(path);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(this);
        objectOutput.close();
        fileOutput.close();
    }

    public static DataManager load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(path);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        DataManager loadedDataManager = (DataManager) objectInput.readObject();
        objectInput.close();
        fileInput.close();
        return loadedDataManager;
    }
}