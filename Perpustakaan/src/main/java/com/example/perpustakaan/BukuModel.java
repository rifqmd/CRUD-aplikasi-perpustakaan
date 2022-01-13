package com.example.perpustakaan;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BukuModel {
    private IntegerProperty id;
    private StringProperty judul;
    private StringProperty author;
    private StringProperty tahun;
    private StringProperty halaman;

    public IntegerProperty idProperty() {return id;}
    public String getJudul(){return judul.get();}
    public String getAuthor(){return author.get();}
    public String getTahun(){return tahun.get();}
    public String getHalaman(){return halaman.get();}

    public BukuModel(int id, String judul, String author, String tahun, String halaman){
        this.id         = new SimpleIntegerProperty(id);
        this.judul      = new SimpleStringProperty(judul);
        this.author     = new SimpleStringProperty(author);
        this.tahun      = new SimpleStringProperty(tahun);
        this.halaman    = new SimpleStringProperty(halaman);
    }
}
