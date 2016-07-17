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
	          .when("/login", {
	              templateUrl: "view/login.html",
	              controller: "LoginController",
	              controllerAs: "model"
	          })
	          .when("/register", {
	              templateUrl: "view/register.html",
	              controller: "RegisterController",
	              controllerAs: "model"
	          })
	          .otherwise({
                redirectTo: "/dashboard"
	          });
    }
    
})();