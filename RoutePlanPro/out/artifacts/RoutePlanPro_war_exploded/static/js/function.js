/*
function submitAndAdd(){
	document.getElementById("information").style.display = "block";
}
function close1(){
	document.getElementById("information").style.display = "none";
	return false;
}
function lookSubmited(){
	document.getElementById("result").style.display = "block";
}
function close2(){
	document.getElementById("result").style.display = "none";
}
function close3(){
	this.parentElement.remove();
}
*/

/*

//检索查询
function G(id) {
		return document.getElementById(id);
	}
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
		setPlace();
});
//添加覆盖物
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
}*/
