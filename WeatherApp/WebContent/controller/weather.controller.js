(function () {

    angular
        .module("WeatherApp")
        .controller("WeatherController", WeatherController);

    function WeatherController($scope, WeatherService) {
    	var vm = this;
    	
    	function init() {
    		vm.zipCode = "";
    		vm.main = "";
    		vm.locationName = "";
    		vm.coordinates = "";
    		vm.icon = "";
    		vm.wind = "";
    		vm.loadWeather = loadWeather;
    		vm.addToFav = addToFav;
    		loadWeather();
    	}
    	
    	init();
    	
    	function loadWeather() {
    		console.log(vm.zipCode);
    		if(vm.zipCode === "") {
    			vm.zipCode = "02120";
    		}
    		
    		WeatherService
    			.getWeatherDetailsForZipcode(vm.zipCode)
    			.then(function(response) {
    				console.log(response);
    				vm.main = response.data.main;
    				vm.locationName = response.data.name;
    				vm.coordinates = response.data.coord;
    				vm.icon = response.data.weather.icon;
    				vm.wind = response.data.wind;
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    	function addToFav(name, isLocal) {
    		WeatherService
    			.addToFav(vm.zipCode, name, isLocal)
    			.then(function(response) {
    				console.log(response);
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    }
    
})();