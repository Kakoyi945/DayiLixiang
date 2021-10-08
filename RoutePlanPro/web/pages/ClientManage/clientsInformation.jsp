<%--
  Created by IntelliJ IDEA.
  User: happiness
  Date: 2021/8/25
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>clientsInfomation</title>
    <link type="text/css" rel="stylesheet" href="static/css/nav.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

<%-- 静态包含 base标签、css样式、jQuery文件 --%>
<%@ include file="/pages/common/head.jsp"%>
<script type="text/javascript">
    $(function () {
        // 给删除的a标签绑定单击事件，用于删除的确认提示操作
        $("a.deleteClass").click(function () {
            return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
            // return false// 阻止元素的默认行为===不提交请求
        });
    });
</script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/picture/fontpage.jpg" >
    <span class="wel_word">客户信息管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp"%>


</div>

<div id="main">
    <table class="table table-bordered table-hover">
        <tr>
            <td>客户名称</td>
            <td>邮件数量</td>
            <td>邮件地址</td>
            <td>注意事项</td>
            <td>邮递员id</td>
            <td colspan="2">操作</td>
        </tr>
        <c:if test="${empty requestScope.page.items}">
            <tr>
                <td colspan="6">当前订单为空，单击“添加客户订单”添加新订单</td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.page.items}">
        <c:forEach items="${requestScope.page.items}" var="client">
            <tr>
                <td>${client.clientName}</td>
                <td>${client.number}</td>
                <td>${client.address}</td>
                <td>${client.attention}</td>
                <td>${client.courierId}</td>
                <td><a href="clientServlet?action=getClient&id=${client.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="clientServlet?action=delete&id=${client.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>
        </c:if>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/ClientManage/client_edit.jsp?pageTotalCount=${requestScope.page.pageTotalCount}&pageSize=${requestScope.page.pageSize}">添加客户订单</a></td>
        </tr>
    </table>


    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>




</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
