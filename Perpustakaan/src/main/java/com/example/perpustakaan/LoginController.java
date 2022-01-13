package com.example.perpustakaan;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController{
    public TextField tfUsername;
    public PasswordField tfPassword;

    private Koneksi koneksi = new Koneksi();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void btnLoginOnClick(ActionEvent actionEvent) throws IOException {
        String userpustakawan = "admin";
        String passpustakawan = "1234";

        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if(username.equals("") && password.equals("")){
            infoBox("Mohon masukan Username dan Password", "Informasi", null);
        }else if(username.equals(userpustakawan) && password.equals(passpustakawan)){
            infoBox("Login berhasil!", "Informasi", null);
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            infoBox("Login gagal!", "Informasi", null);
        }

    }

    private void infoBox(String infoPesan, String judulPesan, String kepalaPesan) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judulPesan);
        alert.setHeaderText(kepalaPesan);
        alert.setContentText(infoPesan);
        alert.showAndWait();
    }

    public void btnTamuOnClick(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("listbuku.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
