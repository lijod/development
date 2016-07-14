(function() {
    angular
        .module("WeatherApp")
        .config(configuration);

    function configuration($routeProvider, $urlRouterProvider, $urlMatcherFactoryProvider) {
    	  $routeProvider
	          .when("/home", {
	              templateUrl: "view/dashboard.html",
	              controller: "WeatherController",
	              controllerAs: "model"
	          })
	          .otherwise({
                redirectTo: "/home"
	          });
    }
    
});