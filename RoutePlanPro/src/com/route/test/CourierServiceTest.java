package com.route.test;

import com.route.bean.Courier;
import com.route.service.CourierService;
import com.route.service.impl.CourierServiceImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

import java.util.List;

public class CourierServiceTest {
    CourierService courierService = new CourierServiceImpl();

    @Test
    public void testGetCouriers(){
        try{
            List<Courier> couriers = courierService.getCouriers();
            for(Courier courier: couriers){
                System.out.println(courier);
            }
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testSaveCourier(){
        try{
            Integer i = courierService.saveCourier(new Courier(null, "工作", 1, "苏罗洛", 15, "女"));
            System.out.println(i);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete(){
        try{
            Integer i = courierService.deleteCourierById(3);
            System.out.println(i);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryById(){
        try{
            Courier courier = courierService.queryCourierById(2);
            System.out.println(courier);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateCourier(){
        try{
            courierService.updateCourier(new Courier(null,"工作",2,"苏苏罗",15,"女"),2);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
}
