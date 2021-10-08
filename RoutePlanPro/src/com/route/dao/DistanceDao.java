package com.route.dao;

import com.route.bean.Distance;

import java.util.List;

public interface DistanceDao {
    void saveDistance(Distance distance);

    List<Distance> getDistancesByCourierId(int courierId);

    int deleteDistanceByAddressId(int addressId);
}
