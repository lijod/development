(function () {
    angular
        .module("WeatherApp")
        .controller("WeatherController", WeatherController);

    function WeatherController($scope, $rootScope, WeatherService, SessionService) {
    	var vm = this;
    	
    	function init() {
    		vm.zipCode = "";
    		vm.main = "";
    		vm.locationName = "";
    		vm.coordinates = "";
    		vm.icon = "";
    		vm.wind = "";
    		vm.loadWeatherForZipcode = loadWeatherForZipcode;
    		vm.loadWeatherFromPref = loadWeatherFromPref;
    		vm.getIconImgSrc = getIconImgSrc;
    		vm.isFavorite = false;
    		vm.hasPref = false;
    		vm.prefList = [];
    		vm.forecast = {};
    		vm.sortedForecastKey = [];
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
    				load5DayForcast();
    				vm.main = response.data.main;
    				vm.locationName = response.data.name;
    				vm.coordinates = response.data.coord;
    				vm.icon = getIconImgSrc(response.data.weather[0].icon);
    				vm.wind = response.data.wind;
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    	function addToFav(name, isLocal) {
    		console.log(name, isLocal);
    		
    		if(!name || name.trim() ==="") {
    			swal('Please!',
   	    			 'Enter a name for this location.',
   	    			 'warning');
    			return;
    		}
    		
    		if(!isLocal) {
    			isLocal = false;
    		}
    		
    		WeatherService
    			.addToFav(vm.zipCode, name, isLocal)
    			.then(function(response) {
    				console.log(response);
    				vm.pref = response.data;
    				vm.prefList.push(response.data);
    				vm.hasPref = true;
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    	function removeFav(pref) {
    		swal({
    			  title: 'Are you sure?',
    			  text: "You won't be able to revert this!",
    			  type: 'warning',
    			  showCancelButton: true,
    			  confirmButtonColor: '#3085d6',
    			  cancelButtonColor: '#d33',
    			  confirmButtonText: 'Yes, delete it!'
    			}).then(function() {
    				WeatherService
        			.removeFav(pref)
        			.then(function(response) {
        				if(response.data) {
        					removeFavFromPrefList(response.data);
        					vm.pref= {};
        					vm.hasPref = false;
        				}
        				swal('Deleted!',
        	    			 'Your file has been deleted.',
        	    			 'success');
        			},
        			function(error) {
        				console.log("Error deleting preference");
        			});
    			});
    	}
    	
    	function loadWeather() {
    		if(SessionService.isLoggedIn()) {
    			console.log("Session found");
    			SessionService.currentUser()
    					.then(function(response) {
    						 $rootScope.username = response.data.username;
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
    		
    		console.log(prefList)
    		
    		if(!prefList) {
    			loadWeatherWithoutSession();
    			return;
    		}
    		
    		prefList.forEach(function(pref, index, arr) {
    			if(pref.local) {
    				loadWeatherFromPref(pref);
    			}
    		});
    		
    		loadWeatherWithoutSession();
    	}
    	
    	function loadWeatherForZipcode(zipcode) {
    		vm.zipCode = zipcode;
    		vm.pref = {};
			vm.hasPref = false;
    		loadWeatherWithoutSession();
    	}
    	
    	function loadWeatherFromPref(pref) {
    		loadWeatherForZipcode(pref.weatherPrefPK.zipcode);
			vm.pref = pref;
			vm.hasPref = true;
			console.log(pref);
			return;
    	}
    	
    	function removeFavFromPrefList(pref) {
    		var toDelete = -1;
    		for(i in vm.prefList) {
    			if(vm.prefList[i].weatherPrefPK.zipcode === pref.weatherPrefPK.zipcode && vm.prefList[i].weatherPrefPK.userId === pref.weatherPrefPK.userId) {
    				toDelete = i;
    				break;
    			}
    		}
    		console.log(toDelete);
    		
    		vm.prefList.splice(toDelete, 1);
    	}
    	
    	function load5DayForcast() {
    		WeatherService
				.get5DayForecastForZipcode(vm.zipCode)
				.then(function(response) {
					processForecastForView(response.data["list"]);
				},
				function(error) {
					
				});
    	}
    	
    	function processForecastForView(data) {
    		forecast = {};
    		sortedForecastKey = [];
    		console.log(data.length);
    		data.forEach(function(element, index, arr) {
    			var date = element["dt_txt"].substring(0, 10);
    			if(! (date in forecast)) {
    				forecast[date] = [];
    				sortedForecastKey.push(date);
    			}
    			forecast[date].push(element);
    		});
    		sortedForecastKey.sort();
    		vm.forecast = forecast;
    		vm.sortedForecastKey = sortedForecastKey;
    	}
    	
    	function getIconImgSrc(iconId) {
    		return "http://openweathermap.org/img/w/" + iconId + ".png"
    	}
    	
    }
})();