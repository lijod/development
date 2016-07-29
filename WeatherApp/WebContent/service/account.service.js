"use strict";
(function(){
    angular
        .module("WeatherApp")
        .factory("AccountService", AccountService);
    
    function AccountService($resource) {
    	
    	var api = {
    		register: register	
    	};
    	
    	return api;
    	
    	
    	function register(user, success, failure) {
    		var User = $resource("user/register.htm");
    		User.save({}, user, success, failure);
    	}
    }
    
})();