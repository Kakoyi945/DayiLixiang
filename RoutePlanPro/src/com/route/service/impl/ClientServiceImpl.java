package com.route.service.impl;

import com.route.bean.Address;
import com.route.bean.Client;
import com.route.bean.Page;
import com.route.dao.AddressDao;
import com.route.dao.ClientDao;
import com.route.dao.DistanceDao;
import com.route.dao.impl.AddressDaoImpl;
import com.route.dao.impl.ClientDaoImpl;
import com.route.dao.impl.DistanceDaoImpl;
import com.route.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    AddressDao addressDao = new AddressDaoImpl();
    DistanceDao distanceDao = new DistanceDaoImpl();

    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public int addClient(Client client) {
        return clientDao.addClient(client);
    }

    @Override
    public int deleteClient(int id) {
        Address address = addressDao.getAddressByClientId(id);
        Integer addId = address.getId();
        distanceDao.deleteDistanceByAddressId(addId);
        addressDao.deleteAddressByClientId(id);
        return clientDao.deleteClient(id);
    }

    @Override
    public int updateClient(Client client, int id) {
        return clientDao.updateClient(client,id);
    }

    @Override
    public Client queryClientById(int id) {
        return clientDao.queryClientById(id);
    }

    @Override
    public List<Client> queryClients() {
        return clientDao.queryClients();
    }

    @Override
    public Page<Client> page(int pageNo, int pageSize) {

        Page<Client> page = new Page<Client>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = clientDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        if(pageTotalCount == 0){
            List<Client> items = clientDao.queryForPageItems(1,pageSize);
            page.setItems(items);
            return page;
        }else {
            //求总页码
            Integer pageTotal = pageTotalCount / pageSize;
            if (pageTotalCount % pageSize > 0) {
                pageTotal += 1;
            }
            //设置总页码
            page.setPageTotal(pageTotal);
            //设置当前页码
            page.setPageNo(pageNo);

            //求当前页数据的开始索引
            int begin = (page.getPageNo() - 1) * pageSize;
            //求当前页数据
            List<Client> items = clientDao.queryForPageItems(begin, pageSize);
            //设置当前页数据
            page.setItems(items);
            return page;
        }
    }

    @Override
    public List<Client> getClientsByCourierId(int courierId) {
        List<Client> clients = clientDao.getClientsByCourierId(courierId);
        return clients;
    }

    @Override
    public Client getLastClient() {
        Client client = clientDao.getLastClient();
        return client;
    }
}
