package com.route.service;

import com.route.bean.Client;
import com.route.bean.Courier;
import com.route.bean.Page;

import java.util.List;

public interface CourierService {
    public List<Courier> getCouriers();

    public Integer saveCourier(Courier courier);

    public Integer deleteCourierById(int id);

    Page<Courier> page(int pageNo, int pageSize);

    Courier queryCourierById(int id);

    Integer updateCourier(Courier courier,int id);

}
