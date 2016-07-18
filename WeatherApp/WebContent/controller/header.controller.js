(function () {
    angular
        .module("WeatherApp")
        .controller("HeaderController", HeaderController);
    
    function HeaderController(SessionService, $location) {
    	
    	var vm = this;
    	
    	function init() {
    		vm.SessionService = SessionService;
    		vm.logout = logout;
    	}
    	
    	init();
    	
    	function logout() {
    		SessionService.logout()
    			.then(function(response) {
    				$location.path("/login");
    			});
    	}
    	
    }
    
})();