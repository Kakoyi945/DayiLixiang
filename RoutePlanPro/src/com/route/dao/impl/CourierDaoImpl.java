package com.route.dao.impl;

import com.route.bean.Client;
import com.route.bean.Courier;
import com.route.dao.CourierDao;

import java.util.List;

public class CourierDaoImpl extends BaseDao implements CourierDao {


    @Override
    public List<Courier> getCouriers() {
        String sql = "select `id`,`status`,`courier_id` courierId,`courier_name` courierName,`age`,`gender` from deliverymen";
        return queryForList(Courier.class,sql);
    }

    @Override
    public Integer saveCourier(Courier courier) {
        String sql = "insert into deliverymen(`status`,`courier_id`,`courier_name`,`age`,`gender`)values(?,?,?,?,?)";
        return update(sql,courier.getStatus(),courier.getCourierId(),courier.getCourierName(),courier.getAge(),courier.getGender());
    }

    @Override
    public Integer deleteCourierById(int id) {
        String sql = "delete from deliverymen where id = ?";
        return update(sql,id);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from deliverymen";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Courier> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`status`,`courier_id` courierId,`courier_name` courierName,`age`,`gender` from deliverymen limit ?,?";
        return queryForList(Courier.class,sql,begin,pageSize);
    }

    @Override
    public Courier queryCourierById(int id) {
        String sql = "select `id`,`status`,`courier_id` courierId,`courier_name` courierName,`age`,`gender` from deliverymen where id = ?";
        return queryForOne(Courier.class,sql,id);
    }


    @Override
    public Integer updateCourier(Courier courier, int id) {
        String sql = "update deliverymen set `status`=?,`courier_id`=?,`courier_name`=?,`age`=?,`gender`=? where id = ?";
        return update(sql,courier.getStatus(),courier.getCourierId(),courier.getCourierName(),courier.getAge(),courier.getGender(),id);
    }

}
