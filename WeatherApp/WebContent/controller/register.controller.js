(function () {

    angular
        .module("WeatherApp")
        .controller("RegisterController", RegisterController);
    
    function RegisterController($scope, SessionService, AccountService, $location) {
    	
    	var vm = this;
    	
    	vm.register = register;
    	
    	function register(user) {
    		AccountService.register(user,
    				function(response) {
    					console.log(response);
    					SessionService.login(user)
		    					.then(function(response) {
		        					$location.path("#/dashboard");
		        				}, 
		        				function(error) {
		        					alert("Error logging in");
		        				});
		    		},
		    		function () {
		    			alert("Error registering user!")
		    		});
        }
    }

})();