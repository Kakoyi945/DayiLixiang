package com.route.controller;

import com.google.gson.Gson;
import com.route.bean.Address;
import com.route.service.RouteService;
import com.route.service.impl.RouteServiceImpl;
import com.route.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RouteServlet extends BaseServlet{
    private RouteService routeService = new RouteServiceImpl();
    protected void getRoute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courierId = WebUtils.parseInt(req.getParameter("courierId"), 1);
        List<Address> result = routeService.getRoute(courierId);
        Gson gson = new Gson();
        String json = gson.toJson(result);
        resp.getWriter().write(json);
        System.out.println(json);
    }
}
