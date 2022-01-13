package com.example.perpustakaan;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TamuController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Koneksi koneksi = new Koneksi();

    public TableView<BukuModel> DataViewBuku2;
    public TableColumn<BukuModel, SimpleIntegerProperty> colId;
    public TableColumn<BukuModel, SimpleStringProperty>colJudul;
    public TableColumn<BukuModel, SimpleStringProperty>colAuthor;
    public TableColumn<BukuModel, SimpleStringProperty>colTahun;
    public TableColumn<BukuModel, SimpleStringProperty>colHalaman;

    public void initialize(URL url, ResourceBundle resourceBundle){
        this.tampilDataViewBuku2();
        this.tampilkan_data();
    }

    public void tampilkan_data(){
        try{
            String query = "SELECT * FROM buku";
            ResultSet hasil = koneksi.getData(query);
            ObservableList<String> items = FXCollections.observableArrayList();
            while (hasil.next()){
                items.add(hasil.getString(2));
                items.add(hasil.getString(3));
                items.add(hasil.getString(4));
                items.add(hasil.getString(5));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void tampilDataViewBuku2(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));

        try{
            String query = "SELECT * FROM buku";
            ResultSet hasil = koneksi.getData(query);
            ObservableList<BukuModel>bukuModels = FXCollections.observableArrayList();
            DataViewBuku2.setItems(bukuModels);
            while(hasil.next()){
                int id          = hasil.getInt(1);
                String judul    = hasil.getString(2);
                String author   = hasil.getString(3);
                String tahun    = hasil.getString(4);
                String halaman  = hasil.getString(5);
                bukuModels.add(new BukuModel(id, judul, author, tahun, halaman));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void btnKembaliOnClick(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
