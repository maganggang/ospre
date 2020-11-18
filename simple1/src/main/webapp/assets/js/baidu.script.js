//初始化默认的项目中心地址
var map = new BMapGL.Map("map_container"); // 创建Map实例
console.log(map);
map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
map.centerAndZoom(new BMapGL.Point(116.374455, 39.775457), 19); // 初始化地图,设置中心点坐标和地图级别
map.setHeading(20);
map.setTilt(70);//3d视角
/**
     * 定义一个控件类
 */
function ZoomControl() {
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMapGL.Size(20, 20)
}
//通过JavaScript的prototype属性继承于BMap.Control
ZoomControl.prototype = new BMapGL.Control();
//自定义控件必须实现自己的initialize方法，并且将控件的DOM元素返回
//在本方法中创建个div元素作为控件的容器，并将其添加到地图容器中
ZoomControl.prototype.initialize = function(map) {
	//创建一个dom元素
	var input = document.createElement('input');
	//添加文字说明
	input.setAttribute("type", "number");
	input.setAttribute("name", "mapNumber");
		input.setAttribute("value", 70);
	// 设置样式
	input.style.width = "50px";
	input.style.boxShadow = "0 2px 6px 0 rgba(27, 142, 236, 0.5)";
	input.style.borderRadius = "5px";
	input.style.backgroundColor = "white";
	// 绑定事件,点击一次放大两级
	input.onchange = function(e) {
		map.setTilt(input.value);
	}
	// 添加DOM元素到地图中
	map.getContainer().appendChild(input);
	// 将DOM元素返回
	return input;
}
//创建控件元素
var myZoomCtrl = new ZoomControl();
//添加到地图中
map.addControl(myZoomCtrl);

var view = new mapvgl.View({
	map: map
});
var threeLayer = new mapvgl.ThreeLayer(); //创建一个threelayer
view.addLayer(threeLayer); //添加到view

var light = new THREE.DirectionalLight('#ffffff', 2); //添加灯光
light.position.set(100, 100, 100);
light.lookAt(0, 0, 0);
threeLayer.scene.add(light);
/**
  初始化地图中心
 */
function initMap(lng, lat) {
	map.centerAndZoom(new BMapGL.Point(lng, lat), 19); // 初始化地图,设置中心点坐标和地图级别
}
/**
	初始化模型
 */
function initModel(lng,lat,url) {
	var pointLayer = new mapvgl.IconLayer({
		width: 100 / 4,
		height: 153 / 4,
		offset: [0, -153 / 8],
		icon: '../images/circle.jpg',
		enablePicked: true, // 是否可以拾取
		selectedColor: '#ff0000', // 选中项颜色
		autoSelect: true, // 根据鼠标位置来自动设置选中项
		onClick: function(e){ // 点击事件
			alert('click', e);
		},
		data: [{
			geometry: {
				type: 'POINT',
				coordinates: [lng, lat]
			}
		}]
	});
	view.addLayer(pointLayer);
	var loader3 = new THREE.GLTFLoader();
	loader3.load(url, function(gltf) {
		var object = gltf.scene;
		object.rotation.set(3.1415926 / 2, 0, 0);
		gltf.scene.position.set(0,  0 ,0);
		gltf.scene.scale.set(160, 160, 160);
		//new BMapGL.Point(12954894, 4805812)
		threeLayer.add(object, new BMapGL.Point(map.centerPoint.lng, map.centerPoint.lat)); //设置模型位置坐标
	}, undefined, function(error) {
		console.error(error);
	})
}

//获取地理位置的函数
function getLocation(lng, lat) {
	var geoc = new BMapGL.Geocoder();
	var point = new BMapGL.Point(lng, lat);
	geoc.getLocation(point, function(rs) {
		var addComp = rs.addressComponents;
		var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
		//$("#start").val(result);
		//$("#start_location").val(result);
		alert("当前的位置为:" + address);
	});
}

var labels=[];
function createLabel(lng, lat, labelTitle, labelStyle){
	var point = new BMapGL.Point(lng, lat);
	if (labelTitle) {
		var opts = {
			position: point, // 指定文本标注所在的地理位置
		};
		// 创建文本标注对象
		var label = new BMap.Label(labelTitle, opts);
		// 自定义文本标注样式
		label.setStyle({
			color: 'blue',
			borderRadius: '5px',
			borderColor: '#ccc',
			padding: '10px',
			fontSize: '16px',
			height: '30px',
			lineHeight: '30px',
			fontFamily: '微软雅黑'
		});
		if (labelStyle){
			label.setStyle(labelStyle)
		}
		map.addOverlay(label);
		labels.push(label);
	}
	return label;
}
var makers = [];
//创建点覆盖物
function createMarker(lng, lat,imagePath, callback) {
	// 创建Marker标注，使用小车图标
	var pt = new BMapGL.Point(lng, lat);
	var markerTemp;
	// 图标
	if (imagePath) {
		var iconTemp = new BMapGL.Icon(imagePath, new BMapGL.Size(52, 26));
		// 创建Marker标注，使用小车图标
		markerTemp = new BMapGL.Marker(pt, {
		    icon: iconTemp
		});
	} else {
		markerTemp  = new BMapGL.Marker(new BMapGL.Point(116.404, 39.925));
	}
	makers.push(markerTemp);
	map.addOverlay(markerTemp);
	if (callback) {
		markerTemp.addEventListener("click", callback);
	}
	return markerTemp;
}
var polyLines = [];
//创建折线
function createPolyLine(array, callback) {
	var arrayPoint = [];
	for (var i = 0; i < array.length; i++) {
		arrayPoint.push(new BMapGL.Point(array[i].lng, array[i].lat))
	}
	var polyline = new BMapGL.Polyline(arrayPoint, {
		strokeColor: 'red',
		strokeWeight: 2,
		strokeOpacity: 0.5
	});
	map.addOverlay(polyline); // 增加折线
	if  (callback) {
		polyline.addEventListener("click", callback());
	}
	var startIcon = new BMapGL.Icon("../images/icon_st.png", new BMapGL.Size(23, 25), {
		offset: new BMapGL.Size(10, 25), // 指定定位位置  
	});
	var endIcon = new BMapGL.Icon("../images/icon_en.png", new BMapGL.Size(23, 25), {
		offset: new BMapGL.Size(10, 25), // 指定定位位置  
	});
	var startMarker = new BMapGL.Marker(arrayPoint[0], { icon: startIcon });
	var endMarker = new BMapGL.Marker(arrayPoint[arrayPoint.length-1], { icon: endIcon });
	//var marker = new BMap.Marker(point);
	map.addOverlay(startMarker);
	map.addOverlay(endMarker);
	polyLines.push(polyline);
	polyLines.push(startMarker);
	polyLines.push(endMarker);
	return polyline;
}
var circles = [];
//创建圆形
function createCircle(lng, lat, radius, callback) {
	var cirClePoint = new BMapGL.Point(lng, lat);
	// 创建圆
	var circle = new BMapGL.Circle(cirClePoint, radius, {
		strokeColor: 'red',
		strokeWeight: 2,
		strokeOpacity: 0.5
	});
	map.addOverlay(circle);
	if (callback) {
		circle.addEventListener("click", callback());
	}
	circles.push(circle);
	return circle;
}
var polyGons = [];
//创建多边形
function createPolygon(array, callback) {
	var arrayPoint = [];
	for (var i = 0; i < array.length; i++) {
		arrayPoint.push(new BMapGL.Point(array[i].lng, array[i].lat))
	}
	// 创建多边形
	var polygon = new BMapGL.Polygon(arrayPoint, {
		strokeColor: 'red',
		strokeWeight: 2,
		strokeOpacity: 0.5
	});
	map.addOverlay(polygon);
	if (callback) {
		polygon.addEventListener("click", callback());
	}
	polyGons.push(polygon);
	return polygon;
}
//创建信息窗口
function createInfoWindow(marker,lng,lat, title, message, dom) {
	var point=new BMapGL.Point(lng,lat)
	var opts = {
		width: 200,     // 信息窗口宽度
		height: 100,     // 信息窗口高度
		title: title, // 信息窗口标题
		message: message
	}
	var infoWindow = new BMapGL.InfoWindow(dom, opts);  // 创建信息窗口对象 
	marker.addEventListener("click", function() {
		map.openInfoWindow(infoWindow, point); //开启信息窗口
	});
}
