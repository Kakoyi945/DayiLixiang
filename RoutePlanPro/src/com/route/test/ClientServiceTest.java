package com.route.test;

import com.route.bean.Client;
import com.route.bean.Page;
import com.route.service.ClientService;
import com.route.service.impl.ClientServiceImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

public class ClientServiceTest {
    private ClientService clientService = new ClientServiceImpl();

    @Test
    public void addClient(){
        try {
            clientService.addClient(new Client(null, "yellow", 1, "汕头市金平区", null, 1));
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Test
    public void deleteClient(){
        try{
            clientService.deleteClient(8);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void updateClient(){
        try{
            clientService.updateClient(new Client(null, "yellow", 1, "汕头市金平区", null, 1),2);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void queryClientById(){
        try{
            Client client = clientService.queryClientById(2);
            System.out.println(client);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testPage(){
        try{
            Page<Client> page = clientService.page(1, 5);
            System.out.println(page);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
}
