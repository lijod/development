"use strict";
(function(){
    angular
        .module("WeatherApp")
        .factory("SessionService", sessionService);
    
    function sessionService($http, $rootScope) {
    	var api = {
    		login: login,
    		logout: logout,
    		isLoggedIn: isLoggedIn,
    		currentUser: currentUser
    	};
    	
    	return api;
    	
    	function login(user) {
    		return $http.post("/WeatherApp/login", "username=" + user.username +
    		        "&password=" + user.password, {
    		            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    		        } ).then(function(data) {
    		            console.log("login successful");
    		            console.log(data);
    		            localStorage.setItem("session", {});
    		            $rootScope.username = user.username;
    		        }, function(data) {
    		        	console.log("error logging in");
    		        });
    	}
    	
    	function logout() {
    		return $http.get("user/logout.htm")
    			.then(function(response) {
    				if(response.data === 200) {
	    				console.log(response);
	    				$rootScope.username = null;
	    				localStorage.removeItem("session");
    				} else {
    					console.log("could not logout user");
    				}
    			}, 
    			function(error) {   
    				console.log("Error logging out user");
    			});
        }
    	
    	function isLoggedIn() {
            return localStorage.getItem("session") !== null;
        }
    	
    	function currentUser() {
    		return $http.get("user/currentuser.htm");
    	}
        
    }
    
})();