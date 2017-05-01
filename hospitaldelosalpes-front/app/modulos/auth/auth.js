'use strict';

angular.module('myApp.auth', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/auth', {
            templateUrl: '/modulos/auth/auth.html',
            controller: 'AuthCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {
                        console.log('llego a controlador');
                    }
                ]
            }
        });
}]).controller('AuthCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {


        $scope.login = function () {
            $scope.loginPromise = $http({
                method: 'GET',
                url: 'http://localhost:8081/auth',
                params: {
                    user: $scope.usuario,
                    password: $scope.password
                }
            }).then(function successCallback(response) {


                    var userInfo = {
                        estado: response.data.estado,
                        elemento: response.data.elemento,
                    };


                    if (userInfo.elemento == 'doctor' || userInfo.elemento == 'paciente') {
                        $localStorage.userInfo = $scope.usuario;
                        if (userInfo.elemento == 'doctor') {
                            $location.path("/doctor")
                        } else {
                            $location.path("/paciente");
                        }
                    }
                    if (userInfo.elemento == 'no existe usuario') {
                        $scope.msj = userInfo.elemento;
                    }
                    if (userInfo.elemento == 'password incorrecto') {
                        $scope.msj = userInfo.elemento;
                    }
                    if (userInfo.elemento == 'password incorrecto') {
                        $scope.msj = userInfo.elemento;
                    }

                },
                function errorCallback(response) {
                    $scope.loginForm.password = "";
                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

            });
        };

    }]);