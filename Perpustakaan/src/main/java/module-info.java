module com.example.perpustakaan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.perpustakaan to javafx.fxml;
    exports com.example.perpustakaan;
}