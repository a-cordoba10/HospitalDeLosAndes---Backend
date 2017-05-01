'use strict';

angular.module('myApp.doctor', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/doctor', {
            templateUrl: '/modulos/doctor/doctor.html',
            controller: 'DoctorCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {
                       
                    }
                ]
            }
        });
}]).controller('DoctorCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {

    $scope.user=$localStorage.userInfo;
    
    $scope.verPacientes = function (){
        $location.path('/pacientes');
    };
    $scope.agregarPaciente = function (){
        $location.path('/pacienteNuevo');
    };
    }]);