package com.route.test;

import com.route.bean.Address;
import com.route.dao.AddressDao;
import com.route.dao.impl.AddressDaoImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.JDBCType;
import java.util.List;

public class AddressDaoTest {
    private AddressDao addressDao = new AddressDaoImpl();
    /*
    @Test
    public void testSave(){
        try{
            Address address = new Address(1, "深圳北站", 1.0, 1.0, 1);
            int i = addressDao.saveAddress(address);
            System.out.println("保存了"+i+"条数据");
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        try{
            Address address = new Address(null,"深圳湾公园",2.0,2.0,5);
            int i = addressDao.updateAddress(address,10);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    */

    @Test
    public void testQueryForList(){
        try{
            List<Address> addresses = addressDao.getAddresses();
            for(Address address:addresses){
                System.out.println(address);
                JdbcUtils.commitAndClose();
            }
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryLastOne(){
        try{
            Address address = addressDao.getAddressByClientId(8);
            System.out.println(address);
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete(){
        try{
            int i = addressDao.deleteAddressByClientId(8);
            System.out.println(i);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

}
