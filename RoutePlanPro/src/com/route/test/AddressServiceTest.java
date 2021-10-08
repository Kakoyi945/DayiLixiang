package com.route.test;

import com.route.bean.Address;
import com.route.dao.AddressDao;
import com.route.dao.impl.AddressDaoImpl;
import com.route.service.AddressService;
import com.route.service.impl.AddressServiceImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

public class AddressServiceTest {
    private AddressService addressService = new AddressServiceImpl();
   /*
    @Test
    public void testSave(){
        try{
            Address address = new Address(null, "深圳北站", 2.0, 2.0, 2);
            addressService.saveAddress(address);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdate(){
        try{
            Address address = new Address(null, "深圳湾公园", 3.0, 4.0, 2);
            addressService.updateAddress(address,11);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testGetDistance(){
        try{
            Address address = new Address(19, "深圳北站", 2.0, 2.0, 2);
            addressService.getDistances();
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    */
}
