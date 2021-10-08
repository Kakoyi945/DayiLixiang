package com.route.test;

import com.route.bean.Address;
import com.route.service.RouteService;
import com.route.service.impl.RouteServiceImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

import java.util.List;

public class RouteServiceTest {
    private RouteService routeService = new RouteServiceImpl();
    @Test
    public void testGetRoute(){
        try{
            List<Address> route = routeService.getRoute(2);
            System.out.println("路径为：");
            for(Address address:route){
                System.out.println(address);
            }
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
}
