'use strict';

angular.module('myApp.pacientehome', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/pacientehome', {
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
    
    
    
    $scope.buscarPaciente = function (){
            $scope.loginPromise = $http({
                method: 'GET',
                url: 'http://localhost:8081/pacientes/5906ba6c61d5c968389b2a82'
                
            }).then(function successCallback(response) {

                $scope.paciente = response.data;
                },
                function errorCallback(response) {
                    
                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

            });
        };
    
    $scope.paciente=$scope.buscarPaciente();
    
    $scope.verPacientes = function (){
        $location.path('/pacientes');
    };
    $scope.agregarPaciente = function (){
        $location.path('/pacienteNuevo');
    };
    }]);