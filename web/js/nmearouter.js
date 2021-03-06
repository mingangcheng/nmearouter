/*function httpLoadMeteo(tp, all) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/meteo?type=" + tp, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return getDataset(tp, json.serie, all, 1, all);
}

function httpLoadMeteoByDate(tp, all, dt) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/meteo?date=" + dt + "&type=" + tp, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return getDataset(tp, json.serie, all, 1, all);
}*/

function httpBackup() {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/backup", false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return json;
}

function httpLoadSpeedDateRange(dt0, dt1) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/speed?date=" + dt0 + "&dateTo=" + dt1, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return getDataset("Speed", json.serie, 1, 1, 1);
}

function httpLoadMeteoDateRange(tp, all, dt0, dt1) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/meteo?date=" + dt0 + "&dateTo=" + dt1 + "&type=" + tp, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return getDataset(tp, json.serie, all, 1, all);
}

function getDataset(caption, sr, min, avg, max) {
	var data = new Object();
	data.datasets = [];
	if (min>0) data.datasets.push(fillDataset(caption + "Min", sr, "vMin", "#00FF00", "#22FF22"));
	if (avg>0) data.datasets.push(fillDataset(caption, 		sr, "v", 	"#555555", "#222222"));
	if (max>0) data.datasets.push(fillDataset(caption + "Max", sr, "vMax", "#FF0000", "#FF2222"));
	return data;
}

function fillDataset(caption, sr, attr, color, borderColor) {
	var dataset = new Object();
	dataset.label = caption;
	dataset.data = [];
	for (i = 0; i<sr.length; i++) {
		var item = sr[i];
		var datapoint = new Object();
		datapoint.x = Date.parse(item['time']);
		datapoint.y = parseFloat(item[attr]);
		dataset.data.push(datapoint);
	}
    dataset.pointBackgroundColor = color;"#FF0000",
    dataset.pointBorderColor = borderColor;
	return dataset;
}

function tripInfo(trip) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + 
			":1112/tripinfo?trip=" + trip.name, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	var duration = json.duration;
	var d = Math.floor(duration / 60 / 60 / 24);
	var h = Math.floor(duration / 60 / 60) % 24;
	var m = Math.round(duration / 60) % 60;
	bootbox.alert({
		message: 
		"<p>" + json.start + " - " + json.end + " UTC</p>" +			
		"<p>Distance <b>" + Math.round(json.dist * 100)/100 + "NM</b> in <b>" + d + "d " + h + "h " + m + "m</b></p>" +
		"<p>Max Speed <b>" + Math.round(json.maxspeed * 100) / 100 + "Kn</b></p>" +
		"<p>Max 30s Avg Speed <b>" + Math.round(json.maxspeed30 * 100) / 100 + "Kn</b></p>"
	});
	
}

function deleteDay(d) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/dropcruisingday?date=" + d.name, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var controllerElement = document.getElementById('TripsPanel');
	var controllerScope = angular.element(controllerElement).scope();
	var days = httpGetCruisingDays();
	controllerScope.days = days;
	controllerScope.$evalAsync();
	return xmlHttp.responseText;
}

function tripIt(d) {
	var xmlHttp = new XMLHttpRequest();
	var ss = d.name.split(".");
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/createtrip?trip=" + ss[0] + "&date=" + ss[1], false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var controllerElement = document.getElementById('TripsPanel');
	var controllerScope = angular.element(controllerElement).scope();
	var days = httpGetCruisingDays();
	controllerScope.days = days;
	controllerScope.$evalAsync();
	return xmlHttp.responseText;
}

function changeName(trip, name) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + 
			":1112/changetripdesc?trip=" + trip + "&desc=" + encodeURIComponent(name), false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var controllerElement = document.getElementById('TripsPanel');
	var controllerScope = angular.element(controllerElement).scope();
	var days = httpGetCruisingDays();
	controllerScope.days = days;
	controllerScope.$evalAsync();
	return xmlHttp.responseText;
}

function httpGetCruisingDays() {
	var caption;
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/cruisingdays", false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return json;
}

function httpGetTrack(dtF, dtT) {
	var caption;
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "http://" + window.location.hostname + ":1112/track?format=json&dateFrom=" + dtF +
			"&dateTo=" + dtT, false);
	xmlHttp.setRequestHeader('Content-Type', 'text/plain');
	xmlHttp.send(null);
	var json = JSON.parse(xmlHttp.responseText);
	return json.track.path;
}