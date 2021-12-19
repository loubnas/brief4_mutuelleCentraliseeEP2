package com.example.brief3_mutuellecentralisee.models;

import com.example.brief3_mutuellecentralisee.dao.implimentation.ClientsImp;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientsImpTest {

    ClientsImp clientsImp= new ClientsImp();
    List<Client> test = new ArrayList<>();
    List<String> testCompany = new ArrayList<>();
    List<ChartData> testChart= new ArrayList<>();
    Client clt = new Client();


    //-------------------------  Test : get All Clients Test-------------------------------------------------
    @Test
    public void getAllClientsTest() {
       assertNotEquals(test,clientsImp.getAllClients());
    }


    @Test
    public void getAllClientsTestEquals(){
        assertEquals(test,clientsImp.getAllClients());
    }


    //-------------------------------------------------------------------------------------------------




    //----------------  Test : get All Companies -------------------------------------------------------------

    @Test
    public void getAllCompaniesTest(){
        assertNotEquals(testCompany,clientsImp.getAllCompanies());
    }

    @Test
    public void getAllCompaniesTestEquals(){
        assertEquals(testCompany,clientsImp.getAllCompanies());
    }


    //---------------------------------------------------------------------------------------------------



    //------------------ Test :  Get client by date register  --------------------------------------------------

    @Test
    public void getClientsByDateRegisterTest(){
        assertNotEquals(testChart,clientsImp.getClientsByDateRegister());
    }

    @Test
    public void getClientsByDateRegisterTestEquals(){
        assertEquals(testChart,clientsImp.getClientsByDateRegister());
    }


    //------------------------- Test : Get by company   ----------------------------------------------------

     // cas ou search = first

       @Test
       public void getByCompanyTest(){
       assertNotEquals(test,clientsImp.getByCompany("Chatterbridge","E"));
       }

     // cas search = email
     @Test
     public void getByCompanyTestEquals(){
         assertNotEquals(test,clientsImp.getByCompany("Gigashots","aheald1@hao123.com"));
     }


    //---------------------------------------------------------------------------------------------


    //-------- Get All Clients( search) --------------------------------------------------------------

    @Test
    public void GetAllClientsTest(){
        assertNotEquals(test,clientsImp.getAllClients("AB"));
    }


    @Test
    public void GetAllClientsTestEquals(){
        assertEquals(test,clientsImp.getAllClients("AB"));
    }



    //--------------------------------------------------------------------------------------------------



    //---------------- Test : Get Clients By CinPass--------------------------------------------------

    @Test
    public void getClientsByCinPassTest(){
        assertNotEquals(clt,clientsImp.getClientsByCinPass("BC561150"));
    }

     @Test
    public void getClientsByCinPassEquals(){
        assertEquals(clt,clientsImp.getClientsByCinPass("BC561150"));
    }


    //-------------------------------------------------------------------------------------------------


    @Test
    public void insertClientTest(){
        Client c= new Client();
        c.setNameCompany("yct");
        c.setNumBadgework("11");
        c.setDateRegister("11/10/2020");
        c.setAdress("qu 123 PLATEAU");
        c.setDateStartWork("13/10/2020");
        c.setEmail("test1@gmail.com");
        c.setFirstName("test1");
        c.setLastName("test2");
        c.setCinpass("HH1111111");
        c.setPhone("0666666666");
        assertEquals(true,clientsImp.insertClient(c));
    }
}