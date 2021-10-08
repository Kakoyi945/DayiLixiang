package com.route.dao;

import com.route.bean.Client;

import java.util.List;

public interface ClientDao {
    public int addClient(Client client);

    public int deleteClient(int id);

    public int updateClient(Client client,int id);

    public Client queryClientById(int id);

    public List<Client> queryClients();

    public Integer queryForPageTotalCount();

    public List<Client> queryForPageItems(int begin,int pageSize);

    public Integer queryCountByCourierId(int courierId);

    List<Client> getClientsByCourierId(int courierId);

    Client getLastClient();
}
