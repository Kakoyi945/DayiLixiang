package com.route.controller;

import com.route.bean.Address;
import com.route.bean.Client;
import com.route.bean.Page;
import com.route.service.AddressService;
import com.route.service.ClientService;
import com.route.service.impl.AddressServiceImpl;
import com.route.service.impl.ClientServiceImpl;
import com.route.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClientServlet extends BaseServlet{

    private ClientService clientService = new ClientServiceImpl();
    private AddressService addressService = new AddressServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);

        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);

        Page<Client> page = clientService.page(pageNo,pageSize);

        page.setUrl("clientServlet?action=page");

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/ClientManage/clientsInformation.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageTotalCount = WebUtils.parseInt(req.getParameter("pageTotalCount"),0)+1;

        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),4);

        int pageNo = pageTotalCount / pageSize;

        if(pageTotalCount % pageSize > 0){
            pageNo += 1;
        }
        String parseNO = String.valueOf(pageNo);

        Client client = WebUtils.copyParamToBean(req.getParameterMap(), new Client());
        int i = clientService.addClient(client);
        Client newClient = clientService.getLastClient();
        Address address = WebUtils.copyParamToBean(req.getParameterMap(),new Address());
        address.setClientId(newClient.getId());
/*        Map<String,String[]> parameterMap = req.getParameterMap();
        for(Map.Entry<String,String[]>entry:parameterMap.entrySet()){
            System.out.println(entry.getKey()+"="+ Arrays.asList(entry.getValue()));
        }*/

        int j = addressService.saveAddress(address);

        addressService.getDistances();

        System.out.println("共添加"+i+"个邮件");
        resp.sendRedirect(req.getContextPath()+"/clientServlet?action=page&pageNo="+parseNO);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int i = clientService.deleteClient(id);
        System.out.println("删除了"+i+"个邮件");
        resp.sendRedirect(req.getContextPath()+"/clientServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Client client = WebUtils.copyParamToBean(req.getParameterMap(),new Client());
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        clientService.updateClient(client,id);
        if(req.getParameter("longitude") != null || req.getParameter("latitude") != null){
            Address address = WebUtils.copyParamToBean(req.getParameterMap(), new Address());
            address.setClientId(id);
            int i = addressService.updateAddress(address,id);
        }

        resp.sendRedirect(req.getContextPath()+"/clientServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getClient(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //获取请求的客户编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用service的queryClientById方法
        Client client = clientService.queryClientById(id);
        //将结果保存在request域中
        req.setAttribute("client",client);
        //请求转发到client_edit页面
        req.getRequestDispatcher("/pages/ClientManage/client_edit.jsp").forward(req,resp);
    }
    protected void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //通过ClientService查询全部图书
        List<Client> clients = clientService.queryClients();
        //把全部图书保存到request域中
        req.setAttribute("clients",clients);
        //请求转发到clientInformation.jsp页面
        req.getRequestDispatcher("/pages/ClientManage/clientInformation.jsp").forward(req,resp);


    }
}
