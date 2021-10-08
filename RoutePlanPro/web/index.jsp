<%--
  Created by IntelliJ IDEA.
  User: happiness
  Date: 2021/8/23
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>indexPage</title>
    <link rel="stylesheet" type="text/css" href="static/css/frontpage.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <%@ include file="/pages/common/head.jsp"%>
</head>

<body>
<header class="top">
    <img id = "headerSlogan"
         src="static/picture/fontpage.jpg" alt="Providing all the caffeine you need to power your life.">
    <img id="headerlogo"
         src="static/picture/headerlogo.jpg" alt="HITSZ header logo image">
</header>

<nav>
    <ul>
        <li><a href="courierServlet?action=page">员工信息</a></li>
        <li><a href="clientServlet?action=page">客户信息</a></li>
        <li class="selected"><a href="index.jsp">欢迎界面</a></li>
    </ul>
</nav>

<div id="tableContainer">
    <div id="tableRow">

        <section id="Introduction">
            <h1>Introduction</h1>
            <p>Help you construct your expressages</p>
            <p>Help you program the most optimal routine</p>
            <p>More function will be launched</p>

        </section>

        <section id="instructions">
            <article>
                <header>
                    <h1>Instructions</h1>
                    <h2>Here will tell you how to use the project</h2>
                </header>
                <p>
                    Firstly,you should prepare all the information about your clients,such as the client's name,the amount of the expressages,the address,and so on.
                </p>
                <p>
                    Secondly,click the <a href = "pages/RoutePlanning/main.jsp">mainpage</a> in the navigation or here.
                </p>
                <p>
                    Thirdly,you can enter the information required in the table.Each client maps one table.
                </p>
                <p>
                    Finally,click the <em>submit</em>,then you can wait for the solution with a cup of coffee.
                </p>
            </article>
        </section>

        <aside>
            <p class="beanheading">
            </p>
        </aside>

    </div>
</div>

<footer>

</footer>

</body>
</html>
