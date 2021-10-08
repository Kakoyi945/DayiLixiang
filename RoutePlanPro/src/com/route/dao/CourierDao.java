package com.route.dao;

import com.route.bean.Courier;

import java.util.List;

public interface CourierDao {
    public List<Courier> getCouriers();

    public Integer saveCourier(Courier courier);

    public Integer deleteCourierById(int id);

    public Integer queryForPageTotalCount();

    public List<Courier> queryForPageItems(int begin, int pageSize);

    public Courier queryCourierById(int id);

    public Integer updateCourier(Courier courier, int id);
}
