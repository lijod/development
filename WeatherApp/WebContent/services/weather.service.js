"use strict";
(function () {

    angular
        .module("WeatherApp")
        .factory("WeatherService", WeatherService);

    function WeatherService($http) {
    	
    	
    	var api = {
			getWeatherDetailsForZipcode : getWeatherDetailsForZipcode
    	};
    	
    	return api;
    	
    	function getWeatherDetailsForZipcode(zipCode) {
    		return $http.get("http://api.openweathermap.org/data/2.5/weather?apikey=d35593705091ed00355d127ba301ea54&zip=" + zipCode);
    	}
    	
    }
    
});