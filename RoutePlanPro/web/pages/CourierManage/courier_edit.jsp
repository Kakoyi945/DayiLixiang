<%--
  Created by IntelliJ IDEA.
  User: happiness
  Date: 2021/8/25
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>邮递员信息</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>
    <link type="text/css" rel="stylesheet" href="static/css/nav.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }

        input {
            text-align: center;
        }
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
        #l-map{height:300px;width:100%;}
    </style>
</head>
<body>

<div id="header">
    <img src="static/picture/fontpage.jpg" >
    <h2>编辑邮递员信息</h2>


</div>
<div id="main">
    <form action="courierServlet" method="post">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="pageTotalCount" value="${param.pageTotalCount}">
        <input type="hidden" name="pageSize" value="${param.pageSize}">
        <input type="hidden" name="action" value="${empty param.id ? "add" : "update" }" />
        <input type="hidden" name="id" value="${ requestScope.courier.id }" />
        <table>
            <tr>
                <td>员工状态</td>
                <td>员工id</td>
                <td>员工名字</td>
                <td>员工年龄</td>
                <td>性别</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="status" type="text" value="${requestScope.courier.status}"/></td>
                <td><input name="courierId" type="text" value="${requestScope.courier.courierId}"/></td>
                <td><input name="courierName" type="text" value="${requestScope.courier.courierName}" id="suggestId"/></td>
                <td><input name="age" type="text" value="${requestScope.courier.age}"/></td>
                <td><input name="gender" type="text" value="${requestScope.courier.gender}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>


