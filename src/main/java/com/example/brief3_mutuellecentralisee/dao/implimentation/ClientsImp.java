package com.example.brief3_mutuellecentralisee.dao.implimentation;

import com.example.brief3_mutuellecentralisee.dao.dao;
import com.example.brief3_mutuellecentralisee.dao.interfaces.ClientsInt;
import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.models.ChartData;
import com.example.brief3_mutuellecentralisee.models.Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientsImp implements ClientsInt {

    //------------------------- Get all clients :  --------------------------------------
    @Override
    public List<Client> getAllClients() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Client> list = new ArrayList<Client>();

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setNameCompany(rs.getString("nameCompany"));
                client.setDateStartWork(rs.getString("dateStartWork"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setCinpass(rs.getString("cinpass"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAdress(rs.getString("adress"));
                client.setDateRegister(rs.getString("dateRegister"));
                client.setNumBadgework(rs.getString("NumbadgeWork"));


                list.add(client);
            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return list;
    }

    //----------------------------------------------------------------------------------------


    //----------------  Get all clients search keyword ---------------------------------------
    @Override
    public List<Client> getAllClients(String searchKeyword) {

        Connection conn = null;
        PreparedStatement stmt = null;
        List<Client> list = new ArrayList<Client>();

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_ALL_FILTRED);

            searchKeyword="%"+searchKeyword+"%";

            stmt.setString( 1,searchKeyword);//firstname
            stmt.setString(2,searchKeyword);//lastname
            stmt.setString(3,searchKeyword);//cinpass
            stmt.setString(4,searchKeyword);//email

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setNameCompany(rs.getString("nameCompany"));
                client.setDateStartWork(rs.getString("dateStartWork"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setCinpass(rs.getString("cinpass"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAdress(rs.getString("adress"));
                client.setDateRegister(rs.getString("dateRegister"));
                client.setNumBadgework(rs.getString("NumbadgeWork"));


                list.add(client);
            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return list;

    }
  //-----------------------------------------------------------------------------------------------


   //------------  Get Clients by cin pass -----------------------------------------------------
    @Override
    public Client getClientsByCinPass(String CinPass) {

        Connection conn = null;
        PreparedStatement stmt = null;
        Client client=null;

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_CLIENTS_CINPASS);
            stmt.setString(1,CinPass);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                client = new Client();
                client.setNameCompany(rs.getString("nameCompany"));
                client.setDateStartWork(rs.getString("dateStartWork"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setCinpass(rs.getString("cinpass"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAdress(rs.getString("adress"));
                client.setDateRegister(rs.getString("dateRegister"));
                client.setNumBadgework(rs.getString("NumbadgeWork"));


            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return client;
    }
    //----------------------------------------------------------------------------------------------


    //--------------------------- Get all companies ----------------------------------------
    @Override
    public List<String> getAllCompanies() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<String> list = new ArrayList<String>();

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_COMPANIES);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("nameCompany"));
            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return list;
    }

    //------------------------------------------------------------------------------------------


    //-------------------- Ajout client -------------------------------------------------------
    @Override
    public boolean insertClient(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result=-1;

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,client.getNameCompany());
            stmt.setString(2,client.getDateStartWork());
            stmt.setString(3,client.getFirstName());
            stmt.setString(4,client.getLastName());
            stmt.setString(5,client.getCinpass());
            stmt.setString(6,client.getPhone());
            stmt.setString(7,client.getEmail());
            stmt.setString(8,client.getAdress());
            stmt.setString(9,client.getDateRegister());
            stmt.setString(10,client.getNumBadgework());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return result>0;
    }

    //--------------------------------------------------------------------------------------------


    //------------- Select company / search keyword ------------------------------------------------------------
    @Override
    public List<Client> getByCompany(String company,String searchKeyword) {

        List<Client> list = new ArrayList<Client>();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_BY_COMPANY);

            stmt.setString(1,company);

            searchKeyword="%"+searchKeyword+"%";

            stmt.setString(2,searchKeyword);//firstname
            stmt.setString(3,searchKeyword);//lastname
            stmt.setString(4,searchKeyword);//cinpass
            stmt.setString(5,searchKeyword);//email

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setNameCompany(rs.getString("nameCompany"));
                client.setDateStartWork(rs.getString("dateStartWork"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setCinpass(rs.getString("cinpass"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAdress(rs.getString("adress"));
                client.setDateRegister(rs.getString("dateRegister"));
                client.setNumBadgework(rs.getString("NumbadgeWork"));


                list.add(client);
            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return list;

    }

    //--------------------------------------------------------------------------------------------

    //---------------- Get Clients by Date Register-------------------------------------------


    @Override
    public List<ChartData> getClientsByDateRegister() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<ChartData> list=new ArrayList<>();

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_CLIENTS_BY_DATE);
            ResultSet rs = stmt.executeQuery();

            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                ChartData data = new ChartData();
                data.setDate(sourceFormat.parse(rs.getString("dateRegister")));
                data.setCount(rs.getInt("Nombre"));
                list.add(data);

            }
        } catch (SQLException | ParseException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return list;

    }
}
