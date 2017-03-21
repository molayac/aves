var restMod = angular.module("restMod", []);
var crudMod = angular.module("crudMod", []);

var app = angular.module('aveApp',['restMod', 'crudMod']).run(function($rootScope) {
    $rootScope.aveId = 0;
});

app.constant('urls', {
    BASE: 'http://localhost:8080/',
    AVE_SERVICE_API : 'http://localhost:8080/aves/'
});

app.service('commonService', function ($http) {
    var info;

    return {
        getInfo: getInfo,
        setInfo: setInfo
    };

    function getInfo() {
        return info;
    }
    function setInfo(value) {
        info = value;
    }
});
