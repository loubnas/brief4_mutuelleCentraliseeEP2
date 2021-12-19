package com.example.brief3_mutuellecentralisee.dao;

import com.example.brief3_mutuellecentralisee.helpers.alertHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dao {
    // MySQl connection :---------------------------
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/brief4";
    public static final String ID = "root";
    public static final String PASS = "1234";
    //-------------------------------------------------------------------------

    //-----------------Requette sql ------------------------------------------------------------------

    // Select all clients :
    public static final String GET_ALL = "SELECT * FROM clients ORDER BY id";

    // Select filtre :
    public static final String GET_ALL_FILTRED = "SELECT * FROM clients where (firstname like ? or lastname like ? or cinpass like ? or email like ?  ) ORDER BY id";

    // Select all Companies :
    public static final String GET_COMPANIES = "SELECT DISTINCT(nameCompany) FROM clients ORDER BY nameCompany";

    // Select by company :
    public static final String GET_BY_COMPANY="SELECT * FROM clients WHERE nameCompany = ? and (firstname like ? or lastname like ? or cinpass like ? or email like ?  )";

    // Select email :
    public static final String GET_USER = "SELECT * FROM users where email=?";

    // Insert Clients :
    public static final String INSERT = "INSERT INTO clients(nameCompany, dateStartWork,firstname,lastname,cinpass,phone,email,adress,dateRegister,NumbadgeWork) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)";

    // Select user by CINPASS
    public static final String GET_CLIENTS_CINPASS= "SELECT * FROM clients where cinpass=?";

   // Select clients by date : (statistique)
    public static final String GET_CLIENTS_BY_DATE=" SELECT dateRegister,count(*) as 'Nombre' FROM clients group by dateRegister order by dateRegister asc";



    // Get connection  : ----------
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        }
        return null;
    }

    // close connection : ----------
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
            }
        }
    }

    // close statement : ----------
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
            }
        }
    }
}
