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
    <script type="text/javascript" src="https://api.map.baidu.com/api?type=webgl&v=1.0&ak=ztphPg3sbEBka3UivnVytGrhsV0bpU0E"></script>
    <title>客户信息</title>

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
    <img class="logo_img" alt="" src="../../static/picture/fontpage.jpg" >
    <h2>编辑客户订单</h2>


</div>
<div id="l-map"></div>
<div id="main">
    <form action="clientServlet" method="post">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="pageTotalCount" value="${param.pageTotalCount}">
        <input type="hidden" name="pageSize" value="${param.pageSize}">
        <input type="hidden" name="action" value="${empty param.id ? "add" : "update" }" />
        <input type="hidden" name="id" value="${ requestScope.client.id }" />
        <input type="hidden" name="longitude" id="longitude">
        <input type="hidden" name="latitude" id="latitude">
        <table>
            <tr>
                <td>客户名称</td>
                <td>邮件数量</td>
                <td>邮件地址</td>
                <td>注意事项</td>
                <td>邮递员id</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="clientName" type="text" value="${requestScope.client.clientName}"/></td>
                <td><input name="number" type="text" value="${requestScope.client.number}"/></td>
                <td><input name="address" type="text" value="${requestScope.client.address}" id="suggestId"/></td>
                <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                <td><input name="attention" type="text" value="${requestScope.client.attention}"/></td>
                <td><input name="courierId" type="text" value="${requestScope.client.courierId}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMapGL.Map("l-map");
    map.centerAndZoom(new BMapGL.Point(113.980435,22.595056),12);                   // 初始化地图,设置城市和地图级别。

    var ac = new BMapGL.Autocomplete(    //建立一个自动完成的对象
        {"input" : "suggestId"
            ,"location" : map
        });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
        var myGeo = new BMapGL.Geocoder();
        myGeo.getPoint(G("searchResultPanel").innerHTML, function(point){
            if(point){
                G("longitude").value = point.lng;
                G("latitude").value = point.lat;
            }else{
                alert('您选择的地址没有解析到结果！');
            }
        }, '北京市')

        setPlace();
    });

    function setPlace(){
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMapGL.Marker(pp));    //添加标注
        }
        var local = new BMapGL.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }
</script>

