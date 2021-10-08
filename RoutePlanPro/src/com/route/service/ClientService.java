package com.route.service;

import com.route.bean.Client;
import com.route.bean.Page;

import java.util.List;

public interface ClientService {
    public int addClient(Client client);

    public int deleteClient(int id);

    public int updateClient(Client client,int id);

    public Client queryClientById(int id);

    public List<Client> queryClients();

    Page<Client> page(int pageNo,int pageSize);

    List<Client> getClientsByCourierId(int courierId);

    Client getLastClient();
}
