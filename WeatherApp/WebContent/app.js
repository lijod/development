"use strict";
(function(){
    angular
        .module("WeatherApp", ["ngRoute", "ngMap", "ngResource"])
        .directive('popover', function($compile){
								    return {
								        restrict : 'A',
								        link : function(scope, elem){
								            
								            var content = $("#popover-content").html();
								            var compileContent = $compile(content)(scope);
								            var title = $("#popover-title").html();
								            var options = {
								                content: compileContent,
								                html: true,
								                title: title
								            };
								            
								            $(elem).popover(options);
								        }
								    }
								});
})();