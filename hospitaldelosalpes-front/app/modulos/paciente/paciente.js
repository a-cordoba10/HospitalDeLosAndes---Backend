'use strict';

angular.module('myApp.paciente', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/paciente', {
            templateUrl: '/modulos/paciente/paciente.html',
            controller: 'PacienteCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {

                    }
                ]
            }
        });
}]).controller('PacienteCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {

        $scope.show = false;
        $scope.paciente = $localStorage.pacienteActual;
        $scope.msj1 = 'Se cambio la frecuencia';
        $scope.msj2 = 'Se agrego el reporte';
        $scope.tipos = [{
            ti: 'DIAGNOSTICO'
        }, {
            ti: 'DIETA'
        }, {
            ti: 'PROCEDIMIENTO'
        }, {
            ti: 'OTRO'
        }];
    $scope.setFalse = function (){
        $scope.show=false;
    }
        $scope.cambiarFrecuencia = function () {
            $scope.paciente.fecuenciaMarcapasos = $scope.frecuenciaMarcapasos;
            $scope.loginPromise = $http({
                method: 'PUT',
                url: 'http://localhost:8081/pacientes/' + $scope.paciente.id,
                data: angular.toJson($scope.paciente)
            }).then(function successCallback(response) {
                    $scope.show = true;
                },
                function errorCallback(response) {
                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

            });
        };





        $scope.agregarReporte = function () {
            var tip = $scope.tipoReporte.ti;
            var rep = {
                idDoctor: 'iuj6754g',
                descripcion: $scope.descripcionReporte,
                tipo: tip
            };
            if ($scope.paciente.historiaClinica == undefined) {
                $scope.paciente.historiaClinica = [];
                $scope.paciente.historiaClinica.push(rep);

            } else {
                $scope.paciente.historiaClinica.push(rep);
            }

            $scope.loginPromise = $http({
                method: 'PUT',
                url: 'http://localhost:8081/pacientes/' + $scope.paciente.id,
                data: angular.toJson($scope.paciente)
            }).then(function successCallback(response) {

                    $scope.show = true;
                },
                function errorCallback(response) {

                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

            });
        };


    }]);


$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').focus()
})