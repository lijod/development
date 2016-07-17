"use strict";
(function(){
    angular
        .module("WeatherApp")
        .factory("SessionService", sessionService);
    
    function sessionService($http) {
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
    		            alert("login successful");
    		            console.log(data);
    		            localStorage.setItem("session", {});
    		        }, function(data) {
    		            alert("error logging in");
    		        });
    	}
    	
    	function logout() {
            localStorage.removeItem("session");
        }
    	
    	function isLoggedIn() {
            return localStorage.getItem("session") !== null;
        }
    	
    	function currentUser() {
    		return $http.get("user/currentuser.htm");
    	}
        
    }
    
})();