(function () {
    angular
        .module("WeatherApp")
        .controller("HeaderController", HeaderController);
    
    function HeaderController(SessionService) {
    	
    	var vm = this;
    	
    	function init() {
    		vm.SessionService = SessionService;
    		vm.logout = logout;
    	}
    	
    	init();
    	
    	function logout() {
    		SessionService.logout();
    	}
    	
    }
    
})();