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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PustakawanController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label lblId;
    public Label lblNotif;
    public TextField tfJudul;
    public TextField tfAuthor;
    public TextField tfTahun;
    public TextField tfHalaman;
    public TableView<BukuModel>DataViewBuku;
    public TableColumn<BukuModel, SimpleIntegerProperty>colId;
    public TableColumn<BukuModel, SimpleStringProperty>colJudul;
    public TableColumn<BukuModel, SimpleStringProperty>colAuthor;
    public TableColumn<BukuModel, SimpleStringProperty>colTahun;
    public TableColumn<BukuModel, SimpleStringProperty>colHalaman;


    private Koneksi koneksi = new Koneksi();

    public void initialize(URL url, ResourceBundle resourceBundle){
        this.tampilDataViewBuku();
        this.tampilkan_data();
        lblNotif.setText("");
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

    private void tampilDataViewBuku(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colHalaman.setCellValueFactory(new PropertyValueFactory<>("halaman"));

        try{
            String query = "SELECT * FROM buku";
            ResultSet hasil = koneksi.getData(query);
            ObservableList<BukuModel>bukuModels = FXCollections.observableArrayList();
            DataViewBuku.setItems(bukuModels);
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
            DataViewBuku.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> tampilDetailBuku(newValue));
    }

    private void tampilDetailBuku(BukuModel buku){
        if(buku != null){
            lblId.setText((buku.idProperty().getValue().toString()));
            tfJudul.setText(buku.getJudul());
            tfAuthor.setText(buku.getAuthor());
            tfTahun.setText(buku.getTahun());
            tfHalaman.setText(buku.getHalaman());
        }else{
            lblId.setText("null");
            tfJudul.setText("");
            tfAuthor.setText("");
            tfTahun.setText("");
            tfHalaman.setText("");

        }
    }

    public void btnSimpanOnClick(ActionEvent actionEvent) {
        String id       = lblId.getText();
        String judul    = tfJudul.getText();
        String author   = tfAuthor.getText();
        String tahun    = tfTahun.getText();
        String halaman  = tfHalaman.getText();
        String query    = "INSERT INTO buku VALUES("+ id +", '"+judul+"', '"+author+"', '"+tahun+"', "+halaman+")";
        koneksi.executeQuery(query);
        this.tampilkan_data();
        this.tampilDataViewBuku();
        lblNotif.setText("Data berhasil ditambahkan!");
    }

    public void btnUpdateOnClick(ActionEvent actionEvent) {
        String id       = lblId.getText();
        String judul    = tfJudul.getText();
        String author   = tfAuthor.getText();
        String tahun    = tfTahun.getText();
        String halaman  = tfHalaman.getText();
        String query    = "UPDATE buku SET judul= '"+judul+"', author='"+author+"', tahun='"+tahun+"', halaman='"+halaman+"' WHERE id="+Integer.parseInt(id);
        koneksi.executeQuery(query);
        this.tampilkan_data();
        this.tampilDataViewBuku();
        lblNotif.setText("Data berhasil diperbarui!");
    }

    public void btnHapusOnClick(ActionEvent actionEvent) {
        String id       = lblId.getText();
        String query    = "DELETE FROM buku WHERE id="+Integer.parseInt(id);
        koneksi.executeQuery(query);
        this.tampilkan_data();
        this.tampilDataViewBuku();
        lblNotif.setText("Data berhasil dihapus!");
    }

    public void btnResetOnClick(ActionEvent actionEvent) {
        lblId.setText("null");
        tfJudul.setText("");
        tfAuthor.setText("");
        tfTahun.setText("");
        tfHalaman.setText("");
        lblNotif.setText("");
    }

    public void btnKembaliOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}