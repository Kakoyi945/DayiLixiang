var map = new BMapGL.Map('container');
map.centerAndZoom(new BMapGL.Point(113.980435,22.595056), 13);
map.enableScrollWheelZoom(true);

//批量地址解析
var myGeo = new BMapGL.Geocoder();
var adds = document.getElementsByClassName('addrs');
var index = 0;
function bdGEO(){
            if (index < adds.length) {
                var add = adds[index].innerText;
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
		var marker = new BMapGL.Marker(point,{enableMassClear:false});
		map.addOverlay(marker);
		marker.setLabel(label);
	}

//轨迹动画
/*var path = [{
        'lng': 116.297611,
        'lat': 40.047363
    }, {
    	'lng': 116.302839,
    	'lat': 40.048219
    }, {
    	'lng': 116.308301,
    	'lat': 40.050566
    }, {
    	'lng': 116.305732,
    	'lat': 40.054957
    }, {
    	'lng': 116.304754,
    	'lat': 40.057953
    }, {
    	'lng': 116.306487,
    	'lat': 40.058312
    }, {
    	'lng': 116.307223,
    	'lat': 40.056379
	}];*/
$(function (){
    $("#routePlanBtn").click(function (){
        alert("点击了routePlanBtn事件");
    });
});
/*
{

    var point = [];
    for (var i = 0; i < path.length; i++) {
        point.push(new BMapGL.Point(path[i].lng, path[i].lat));
    }
    var pl = new BMapGL.Polyline(point);
    setTimeout('start()', 3000);
    start = function() {
        trackAni = new BMapGLLib.TrackAnimation(map, pl, {
            overallView: true,
            tilt: 30,
            duration: 20000,
            delay: 300
        });
        trackAni.start();
    }
}*/
