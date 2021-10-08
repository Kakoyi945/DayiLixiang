<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="https://api.map.baidu.com/api?type=webgl&v=1.0&ak=ztphPg3sbEBka3UivnVytGrhsV0bpU0E"></script>
    <%@ include file="/pages/common/head.jsp"%>
    <title>批量地址解析</title>
    <link type="text/css" rel="stylesheet" href="static/css/main.css">
</head>
<body>
<div id='container'></div>
    <div id='result'>
        <div>批量地址解析</div>
        <ul>
            <c:forEach items="${requestScope.clients}" var="client">
                <li>${client.address}</li>
            </c:forEach>
        </ul>
        <div>
            <button onclick="bdGEO()" class='btn'>批量解析</button>
            <button onclick="getPath()" class="btn">路径规划</button>
            <button onclick="showHidden()" class="btn">显示路径</button>
        </div>
    </div>
    <div id="route">
        <div>邮递路径</div>
        <ul id="route_res">
        </ul>
        <div>
            <button onclick="hideBlock()" class="btn">返回</button>
        </div>
    </div>
<script>

    var map = new BMapGL.Map('container');
    map.centerAndZoom(new BMapGL.Point(113.980435,22.595056), 12);
    map.enableScrollWheelZoom(true);
    var myGeo = new BMapGL.Geocoder();
    var info = document.getElementsByTagName("li");
    var adds = [];
    for(var i = 0;i<info.length;i++){
        adds.push(info[i].innerHTML);
    }

    var index = 0;
    function bdGEO(){
        if (index < adds.length) {
            var add = adds[index];
            geocodeSearch(add);
            index++;
        }
    }
    function geocodeSearch(add){
        if(index < adds.length){
            setTimeout(window.bdGEO,400);
        }
        myGeo.getPoint(add, function(point){
            if (point) {
                var address = new BMapGL.Point(point.lng, point.lat);
                addMarker(address,new BMapGL.Label(index+":"+add,{offset:new BMapGL.Size(10,-10)}));
            }
        }, "深圳市");
    }
    // 编写自定义函数,创建标注
    function addMarker(point,label){
        var marker = new BMapGL.Marker(point);
        map.addOverlay(marker);
        marker.setLabel(label);
    }


    function getPath(){
        var xmlHttprequest = new XMLHttpRequest();
/*        function MyAddress(lnt,lat){
            this.lnt = lnt;
            this.lat = lat;
        }
        MyAddress.prototype = {
            getLnt:function (){return this.lnt;},
            getLat:function (){return this.lat;},
            toString:function (){return "("+this.lnt+"..."+this.lat+")"}
        }*/

        var point = [];
        xmlHttprequest.open("GET","routeServlet?action=getRoute&courierId=${requestScope.courierId}",true);
        xmlHttprequest.onreadystatechange = function (){
            if(xmlHttprequest.readyState == 4&&xmlHttprequest.status == 200){
                var route = JSON.parse(xmlHttprequest.responseText);
                for(var i = 0;i<route.length;i++){
                    point.push(new BMapGL.Point(route[i].longitude,route[i].latitude));
                    document.getElementById("route_res").innerHTML += "<li>"+route[i].address+"</li>";
                }
                var driving = new BMapGL.DrivingRoute(map);
                for(var i = 0;i<point.length-1;i++){
                    driving.search(point[i],point[i+1]);
                }
                map.setViewport(point);
                driving.setSearchCompleteCallback(function(){
                    var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组

                    var polyline = new BMapGL.Polyline(pts,{strokeColor:"black",strokeWeight:5});
                    map.addOverlay(polyline);
                });
            }
        }
        xmlHttprequest.send();


    }
    function showHidden(){
        document.getElementById("route").style.display="block";
    }
    function hideBlock(){
        document.getElementById("route").style.display="none";
    }
</script>
</body>
</html>