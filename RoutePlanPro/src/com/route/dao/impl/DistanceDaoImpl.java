package com.route.dao.impl;

import com.route.bean.Distance;
import com.route.dao.DistanceDao;

import java.util.List;

public class DistanceDaoImpl extends BaseDao implements DistanceDao {
    @Override
    public void saveDistance(Distance distance) {
        String sql = "insert into distance_tbl(`courier_id`,`add1_id`,`add2_id`,`distance`)values(?,?,?,?)";
        update(sql,distance.getCourierId(),distance.getAdd1Id(),distance.getAdd2Id(),distance.getDistance());
    }

    @Override
    public List<Distance> getDistancesByCourierId(int courierId) {
        String sql = "select courier_id courierId,add1_id add1Id,add2_id add2Id,distance from distance_tbl where courier_id = ?";
        List<Distance> distances = queryForList(Distance.class, sql, courierId);
        return distances;
    }

    @Override
    public int deleteDistanceByAddressId(int addressId) {
        String sql = "delete from distance_tbl where add1_id = ? or add2_id = ?";
        int i = update(sql, addressId, addressId);
        return i;
    }
}
