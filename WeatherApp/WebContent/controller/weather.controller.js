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
    		vm.isFavorite = false;
    		vm.hasPref = false;
    		vm.prefList = [];
    		loadWeather();

    		//Function declarations
    		vm.addToFav = addToFav;
    		vm.removeFav = removeFav;
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
    		console.log(name, isLocal);
    		
    		WeatherService
    			.addToFav(vm.zipCode, name, isLocal)
    			.then(function(response) {
    				console.log(response);
    				vm.pref = response.data;
    				vm.hasPref = true;
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    	function removeFav(pref) {
    		console.log(pref);
    		
    		WeatherService
    			.removeFav(pref)
    			.then(function(response) {
    				if(response.data) {
    					vm.pref= {};
    					vm.hasPref = false;
    				}
    			},
    			function(error) {
    				console.log("Error deleting preference");
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
    		
    		if(!prefList) {
    			loadWeatherWithoutSession();
    			return;
    		}
    		
    		prefList.forEach(function(pref, index, arr) {
    			if(pref.local) {
    				loadWeatherForZipcode(pref.weatherPrefPK.zipcode);
    				vm.pref = pref;
    				vm.hasPref = true;
    				console.log(pref);
    				return;
    			}
    		});	
    		
    		loadWeatherWithoutSession();
    	}
    	
    	function loadWeatherForZipcode(zipcode) {
    		vm.zipCode = zipcode;
    		loadWeatherWithoutSession();
    	}
    }
    
})();