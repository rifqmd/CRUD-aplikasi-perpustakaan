package com.example.perpustakaan;

import java.sql.*;

public class Koneksi {

    private Connection conn;
    private Statement st;

    public Koneksi(){
        try{
            String user = "root";
            String password = "";
            String urldb = "jdbc:mysql://localhost:3306/perpustakaan";
            conn = DriverManager.getConnection(urldb, user, password);
            System.out.println("Koneksi Berhasil");
        }
        catch (SQLException e){
            System.out.println("Terjadi Error yang tidak diketahui: " +e);
        }catch (Exception e){
            System.out.println("Koneksi Gagal: " +e);
        }
    }

    //METHOD UNTUK AMBIL DATA
    public ResultSet getData(String query){
        try{
            st = conn.createStatement();
            return st.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //METHOD INSERT UPDATE DELETE
    public int executeQuery(String query){
        try {
            st = conn.createStatement();
            return st.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.println("Terjadi Error Yang Tidak Diketahui");
        }
        return 0;
    }
}
