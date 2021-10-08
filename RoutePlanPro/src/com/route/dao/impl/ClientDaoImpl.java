package com.route.dao.impl;

import com.route.bean.Client;
import com.route.dao.ClientDao;

import java.util.List;

public class ClientDaoImpl extends BaseDao implements ClientDao{
    @Override
    public int addClient(Client client) {
        String sql = "insert into client_info(`clientname`,`number`,`address`,`courier_id`,`attention`) values(?,?,?,?,?)";
        return update(sql, client.getClientName(), client.getNumber(), client.getAddress(), client.getCourierId(), client.getAttention());
    }

    @Override
    public int deleteClient(int id) {
        String sql = "delete from client_info where id = ?";

        return update(sql,id);
    }

    @Override
    public int updateClient(Client client,int id) {
        String sql = "update client_info set `clientname`=?,`number`=?,`address`=?,`courier_id`=?,`attention`=? where id = ?";

        return update(sql,client.getClientName(),client.getNumber(),client.getAddress(),client.getCourierId(),client.getAttention(),id);
    }

    @Override
    public Client queryClientById(int id) {
        String sql = "select `id`,`clientname` clientName,`number`,`address`,`courier_id` courierId,`attention` from client_info where id = ?";

        return queryForOne(Client.class,sql,id);
    }

    @Override
    public List<Client> queryClients() {
        String sql = "select `id`,`clientname` clientName,`number`,`address`,`courier_id` courierId,`attention` from client_info";

        return queryForList(Client.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from client_info";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Client> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`clientname` clientName,`number`,`address`,`courier_id` courierId,`attention` from client_info limit ?,?";
        return queryForList(Client.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryCountByCourierId(int courierId) {
        String sql = "select count(*) from client_info where courier_id = ?";
        Number count =  (Number) queryForSingleValue(sql,courierId);
        return count.intValue();
    }

    @Override
    public List<Client> getClientsByCourierId(int courierId) {
        String sql = "select `id`,`clientname` clientName,`number`,`address`,`courier_id` courierId,`attention` from client_info where courier_id = ?";

        return queryForList(Client.class,sql,courierId);
    }

    @Override
    public Client getLastClient() {
        String sql = "select `id`,`clientname` clientName,`number`,`address`,`courier_id` courierId,`attention` from client_info order by id desc limit 1";
        return queryForOne(Client.class,sql);
    }
}
