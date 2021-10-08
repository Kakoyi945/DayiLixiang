package com.route.controller;

import com.route.bean.Client;
import com.route.service.ClientService;
import com.route.service.impl.ClientServiceImpl;
import com.route.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddressServlet extends BaseServlet{

    private ClientService clientService = new ClientServiceImpl();

    protected void getAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courierId = WebUtils.parseInt(req.getParameter("courierId"),1);

        //通过ClientService查询全部图书
        List<Client> clients = clientService.getClientsByCourierId(courierId);
        //把全部图书保存到request域中
        req.setAttribute("clients",clients);
        req.setAttribute("courierId",courierId);
        //请求转发到clientInformation.jsp页面
        req.getRequestDispatcher("/pages/RoutePlanning/main.jsp").forward(req,resp);
    }
}
