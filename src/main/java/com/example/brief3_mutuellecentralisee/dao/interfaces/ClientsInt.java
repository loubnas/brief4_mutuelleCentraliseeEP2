package com.example.brief3_mutuellecentralisee.dao.interfaces;

import com.example.brief3_mutuellecentralisee.models.ChartData;
import com.example.brief3_mutuellecentralisee.models.Client;

import java.util.List;

public interface ClientsInt {

    public List<Client> getAllClients();
    public List<String> getAllCompanies();
    public boolean insertClient(Client client);
    public List<Client> getByCompany(String company,String searchKeyword);
    public List<Client> getAllClients(String searchKeyword);
    public Client getClientsByCinPass(String CinPass);
    public List<ChartData> getClientsByDateRegister();
}
