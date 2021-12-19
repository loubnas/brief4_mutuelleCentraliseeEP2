package com.example.brief3_mutuellecentralisee.models;

import com.example.brief3_mutuellecentralisee.dao.implimentation.UsersImp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserImpTest {

  @Test
    public void testLogin(){
        UsersImp user = new UsersImp();
        assertNotNull(user.Connect("loubna@gmail.com","112"));
    }


    @Test
    public void testLoginNull(){
        UsersImp user = new UsersImp();
        assertNull(user.Connect("DHDDH@gmail.com","1267"));
    }




}
