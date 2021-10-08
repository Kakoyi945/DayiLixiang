package com.route.controller;

import com.route.bean.Client;
import com.route.bean.Courier;
import com.route.bean.Page;
import com.route.service.AddressService;
import com.route.service.CourierService;
import com.route.service.impl.AddressServiceImpl;
import com.route.service.impl.CourierServiceImpl;
import com.route.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourierServlet extends BaseServlet{
    private CourierService courierService = new CourierServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);

        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Courier> page = courierService.page(pageNo,pageSize);

        page.setUrl("courierServlet?action=page");

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/CourierManage/couriersInformation.jsp").forward(req,resp);
    }
    protected void getCourier(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //获取请求的客户编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用service的queryClientById方法
        Courier courier = courierService.queryCourierById(id);
        //将结果保存在request域中
        req.setAttribute("courier",courier);
        //请求转发到client_edit页面
        req.getRequestDispatcher("/pages/CourierManage/courier_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Courier courier = WebUtils.copyParamToBean(req.getParameterMap(),new Courier());
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        courierService.updateCourier(courier,id);
        resp.sendRedirect(req.getContextPath()+"/courierServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int i = courierService.deleteCourierById(id);
        System.out.println("删除了"+i+"个员工的信息");
        resp.sendRedirect(req.getContextPath()+"/courierServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageTotalCount = WebUtils.parseInt(req.getParameter("pageTotalCount"),0)+1;

        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),4);

        int pageNo = pageTotalCount / pageSize;

        if(pageTotalCount % pageSize > 0){
            pageNo += 1;
        }
        String parseNO = String.valueOf(pageNo);

        Courier courier = WebUtils.copyParamToBean(req.getParameterMap(), new Courier());

        int i = courierService.saveCourier(courier);
        System.out.println("共添加"+i+"个邮件");
        resp.sendRedirect(req.getContextPath()+"/courierServlet?action=page&pageNo="+parseNO);
    }
}
