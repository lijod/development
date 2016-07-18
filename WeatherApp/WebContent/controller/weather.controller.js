(function () {
    angular
        .module("WeatherApp")
        .controller("WeatherController", WeatherController);

    function WeatherController($scope, WeatherService, SessionService) {
    	var vm = this;
    	
    	function init() {
    		vm.zipCode = "";
    		vm.main = "";
    		vm.locationName = "";
    		vm.coordinates = "";
    		vm.icon = "";
    		vm.wind = "";
    		vm.loadWeatherForZipcode = loadWeatherForZipcode;
    		vm.addToFav = addToFav;
    		vm.isFavorite = false;
    		vm.prefList = [];
    		loadWeather();
    	}
    	
    	init();
    	
    	function loadWeatherWithoutSession() {
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
    				vm.icon = "http://openweathermap.org/img/w/" + response.data.weather[0].icon + ".png";
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
    	
    	function loadWeather() {
    		if(SessionService.isLoggedIn()) {
    			console.log("Session found");
    			SessionService.currentUser()
    					.then(function(response) {
    						loadSavedPreferences(response.data.prefList);
    					},
    					function (error) {
    						console.log("Error fetching session user");
    					});
    		} else {
    			console.log("No session found");
    			loadWeatherWithoutSession();
    		}
    	}
    	
    	function loadSavedPreferences(prefList) {
    		vm.prefList = prefList;
    		
    		if(!prefList || prefList.length == 0) {
    			loadWeatherWithoutSession();
    			return;
    		}
    		
    		prefList.forEach(function(pref, index, arr) {
    			if(pref.local) {
    				loadWeatherForZipcode(pref.weatherPrefPK.zipcode);
    				vm.currLocation = pref;
    				console.log(pref);
    				return;
    			}
    		});	
    	}
    	
    	function loadWeatherForZipcode(zipcode) {
    		vm.zipCode = zipcode;
    		loadWeatherWithoutSession();
    	}
    }
    
})();