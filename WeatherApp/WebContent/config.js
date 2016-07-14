(function() {
    angular
        .module("WeatherApp")
        .config(configuration);

    function configuration($routeProvider) {
    	  $routeProvider
	          .when("/dashboard", {
	              templateUrl: "view/dashboard.html",
	              controller: "WeatherController",
	              controllerAs: "model"
	          })
	          .otherwise({
                redirectTo: "/dashboard"
	          });
    }
    
})();