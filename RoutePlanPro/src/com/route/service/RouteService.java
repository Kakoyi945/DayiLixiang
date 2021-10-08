package com.route.service;

import com.route.bean.Address;

import java.util.List;

public interface RouteService {
    List<Address> getRoute(int courierId);
}
