package com.example.perpustakaan;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PustakawanModel {
    private IntegerProperty id_pustakawan;
    private StringProperty nama_pustakawan;
    private StringProperty username;
    private StringProperty password;

    public IntegerProperty idProperty() {return id_pustakawan;}
    public String getNama_pustakawan(){return nama_pustakawan.get();}
    public String getUsername(){return username.get();}
    public String getPassword(){return password.get();}

    public PustakawanModel(int id_pustakawan, String nama_pustakawan, String username, String password){
        this.id_pustakawan      = new SimpleIntegerProperty(id_pustakawan);
        this.nama_pustakawan    = new SimpleStringProperty(nama_pustakawan);
        this.username           = new SimpleStringProperty(username);
        this.password           = new SimpleStringProperty(password);
    }
}
