package com.route.test;

import com.route.dao.DistanceDao;
import com.route.dao.impl.DistanceDaoImpl;
import com.route.utils.JdbcUtils;
import org.junit.Test;

public class DistanceDaoTest {
    DistanceDao distanceDao = new DistanceDaoImpl();
    @Test
    public void testDelete(){
        try{
            int i = distanceDao.deleteDistanceByAddressId(15);
            System.out.println(i);
            JdbcUtils.commitAndClose();
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
}
