'use strict';

angular.module('myApp.pacientes', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/pacientes', {
            templateUrl: '/modulos/pacientes/pacientes.html',
            controller: 'PacientesCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {
                       
                    }
                ]
            }
        });
}]).controller('PacientesCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {

    
    
    
    
    $scope.getPacientes = function () {
        
            $scope.loginPromise = $http({
                    method: 'GET',
                    url: 'http://localhost:8081/pacientes'
                    
                }).then(function successCallback(response) {
                
                $localStorage.pacienteActual=response.data[0];
                   $scope.pacientes=response.data;       
                },
                function errorCallback(response) {
                    
                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

        });
    };
    
    $scope.pacientes=$scope.getPacientes();

    $scope.irPaciente= function (paciente){
         $localStorage.pacienteActual=paciente;
        $location.path('/paciente');
    };
    }]);