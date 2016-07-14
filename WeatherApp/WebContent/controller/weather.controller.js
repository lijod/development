(function () {

    angular
        .module("GadgetGuruApp")
        .controller("WeatherController", WeatherController);

    function WeatherController($scope, WeatherService) {
    	var vm = this;
    	
    	function init() {
    		loadWeather();
    	}
    	
    	init();
    	
    	vm.zipCode = "";
    	
    	function loadWeather() {
    		var zipcode = "";
    		if(vm.zipCode == "") {
    			zipcode = "02120";
    		}
    		
    		WeatherService
    			.getWeatherDetailsForZipcode(zipcode)
    			.then(function(response) {
    				console.log(response)
    			},
    			function(error) {
    				console.log(error);
    			});
    	}
    	
    }
    
});