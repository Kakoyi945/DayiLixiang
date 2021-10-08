package com.route.service.impl;

import com.route.bean.Courier;
import com.route.bean.Page;
import com.route.dao.CourierDao;
import com.route.dao.impl.CourierDaoImpl;
import com.route.service.CourierService;

import java.util.List;

public class CourierServiceImpl implements CourierService {
    CourierDao courierDao = new CourierDaoImpl();

    @Override
    public List<Courier> getCouriers() {
        return courierDao.getCouriers();
    }

    @Override
    public Integer saveCourier(Courier courier) {
        return courierDao.saveCourier(courier);
    }

    @Override
    public Integer deleteCourierById(int id) {
        return courierDao.deleteCourierById(id);
    }

    @Override
    public Page<Courier> page(int pageNo, int pageSize) {
        Page<Courier> page = new Page<Courier>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = courierDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        if(pageTotalCount == 0){
            List<Courier> items = courierDao.queryForPageItems(1,pageSize);
            //设置当前页数据
            page.setItems(items);
            return page;
        }else {
            //求总页码
            Integer pageTotal = pageTotalCount / pageSize;
            if (pageTotalCount % pageSize > 0) {
                pageTotal += 1;
            }
            //设置总页码
            page.setPageTotal(pageTotal);
            //设置当前页码
            page.setPageNo(pageNo);

            //求当前页数据的开始索引
            int begin = (page.getPageNo() - 1) * pageSize;
            //求当前页数据
            List<Courier> items = courierDao.queryForPageItems(begin, pageSize);
            //设置当前页数据
            page.setItems(items);
            return page;
        }
    }

    @Override
    public Courier queryCourierById(int id) {
        Courier courier = courierDao.queryCourierById(id);
        return courier;
    }

    @Override
    public Integer updateCourier(Courier courier, int id) {
        return courierDao.updateCourier(courier,id);
    }
}
