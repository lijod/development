(function () {

    angular
        .module("WeatherApp")
        .controller("LoginController", LoginController);
    
    function LoginController($scope, $rootScope, SessionService, $location) {
    	
    	var vm = this;
    	
    	vm.login = login;
    	
    	function login(user) {
    		SessionService.login(user)
    				.then(function(response) {
    					$location.path("/dashboard");
    				}, 
    				function(error) {
    					alert("Error logging in");
    				});
    		
        }
    }

})();