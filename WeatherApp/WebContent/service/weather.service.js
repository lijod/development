"use strict";
(function () {

    angular
        .module("WeatherApp")
        .factory("WeatherService", WeatherService);

    function WeatherService($http) {
    	
    	var api = {
			getWeatherDetailsForZipcode : getWeatherDetailsForZipcode,
			get5DayForecastForZipcode: get5DayForecastForZipcode,
			addToFav: addToFav,
			removeFav: removeFav
    	};
    	
    	return api;
    	
    	function getWeatherDetailsForZipcode(zipCode) {
    		return $http.get("http://api.openweathermap.org/data/2.5/weather?units=imperial&apikey=d35593705091ed00355d127ba301ea54&q=" + zipCode);
    	}
    	
    	function get5DayForecastForZipcode(zipCode) {
    		return $http.get("http://api.openweathermap.org/data/2.5/forecast?units=imperial&apiKey=d35593705091ed00355d127ba301ea54&q=" + zipCode);
    	}
    	
    	function addToFav(zipcode, name, isLocal) {
    		return $http.get("weather/addfavlocation.htm?zipcode=" + zipcode + "&name="  + name + "&islocal=" + isLocal);
    	}
    	
    	function removeFav(pref) {
    		return $http.post("weather/removefavlocation.htm", pref);
    	}
    	
    }
    
})();