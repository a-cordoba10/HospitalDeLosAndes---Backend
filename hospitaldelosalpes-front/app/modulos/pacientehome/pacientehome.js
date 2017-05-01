'use strict';

angular.module('myApp.pacientehome', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/doctor', {
            templateUrl: '/modulos/pacientehome/pacientehome.html',
            controller: 'PacientehomeCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {
                       
                    }
                ]
            }
        });
}]).controller('PacientehomeCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {

    $scope.user=$localStorage.userInfo;
    
    $scope.verPacientes = function (){
        $location.path('/pacientes');
    };
    $scope.agregarPaciente = function (){
        $location.path('/pacienteNuevo');
    };
    }]);