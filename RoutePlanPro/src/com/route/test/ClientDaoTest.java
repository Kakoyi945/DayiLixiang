package com.route.test;

import com.route.bean.Client;
import com.route.dao.ClientDao;
import com.route.dao.impl.ClientDaoImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

import java.util.List;

public class ClientDaoTest {
    private ClientDao clientDao = new ClientDaoImpl();
    @Test
    public void testInsert(){
        try {
            clientDao.addClient(new Client(null,"yellow",1,"汕头市金平区",null,1));
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();

        }
    }

    @Test
    public void testDelete() {
        try {
            clientDao.deleteClient(7);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        try{
            clientDao.updateClient(new Client(null,"yellow",1,"汕头市金平区",null,1),1);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryForOne(){
        try{
            Client client = clientDao.queryClientById(1);
            System.out.println(client);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryFoeList(){
        try{
            List<Client> clients = clientDao.queryClients();
            for(Client client:clients){
                System.out.println(client);
            }
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }

    }

    @Test
    public void testqueryCountByCourierId(){
        try{
            Integer num = clientDao.queryCountByCourierId(5);
            System.out.println(num);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

}
