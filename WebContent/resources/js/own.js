var latitude;
var longitude;

function init() {
	navigator.geolocation.getCurrentPosition(function(data) {
		var coordinates = data.coords;
		console.log(coordinates);

		latitude = coordinates.latitude;
		longitude = coordinates.longitude;
		setPosition(latitude, longitude);
		fetchWeather(latitude, longitude);

	}, function(error) {
		console.log('Error: Der Proxy muss weg!');
		setPosition(49.015666, 8.389605999999958);
		fetchWeather(49.015666, 8.389605999999958);
	});
};

function getLocation() {
	navigator.geolocation.getCurrentPosition(function(data) {
		var coordinates = data.coords;
		console.log(coordinates);

		latitude = coordinates.latitude;
		longitude = coordinates.longitude;
	}, function(error) {
		console.log('Error: Der Proxy muss weg!');
		latitude = 37.4202254;
		longitude = -122.0798886;
	});
}

function setPosition(latitude, longitude) {
	console.log("Set marker: " + latitude +"," + longitude);
	initMap([{name:'latitude', value:latitude}, {name:'longitude', value:longitude}]);

};

function fetchWeather(latitude, longitude) {
	initWeather([ {
		name : 'latitude',
		value : latitude
	}, {
		name : 'longitude',
		value : longitude
	} ]);
};

function refreshMainMap() {
	refreshMap();
}

function loadForecast() {
	fetchForecast();
}

function deleteCurrentImage(){
	removeImage();
}

function getUsername() {
	var username = document.getElementById("home:username").innerHTML;
	console.log(username);
	return username;
}

function displayDetails() {
	var filenameLong = document.getElementById('map:main:overlay:image').getAttribute('src');
	var filename = filenameLong.split('/')[4].split('?')[0];
	displayImage([{name:'filename',value:filename}]);
}

function tmp() {

}