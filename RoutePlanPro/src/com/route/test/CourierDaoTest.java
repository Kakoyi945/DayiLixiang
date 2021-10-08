package com.route.test;

import com.route.bean.Courier;
import com.route.dao.CourierDao;
import com.route.dao.impl.CourierDaoImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

import java.util.List;

public class CourierDaoTest {
    CourierDao courierDao = new CourierDaoImpl();
    @Test
    public void testSaveCourier(){
        try{
            Integer i = courierDao.saveCourier(new Courier(null, "工作", 5, "didi", 50, "男"));
            System.out.println("保存了"+i+"条数据");
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCouriers(){
        try{
            List<Courier> couriers = courierDao.getCouriers();
            for(Courier courier:couriers){
                System.out.println(courier);
            }
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteCourier(){
        try{
            courierDao.deleteCourierById(5);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

}
