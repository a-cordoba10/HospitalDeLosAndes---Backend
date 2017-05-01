'use strict';

angular.module('myApp.pacienteNuevo', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/pacienteNuevo', {
            templateUrl: '/modulos/pacienteNuevo/pacienteNuevo.html',
            controller: 'PacienteNuevoCtrl',
            resolve: {
                "logged": ['$localStorage', '$location', function ($localStorage, $location) {
                        
                    }
                ]
            }
        });
}]).controller('PacienteNuevoCtrl', ['$scope', '$location', '$localStorage', '$http', function ($scope, $location, $localStorage, $http) {

    
    
    
    
    
    $scope.addPaciente = function () {
        
        
        var paciente={
            nombre:$scope.newPaciente.nombre ,
            edad:$scope.newPaciente.edad,
            direccion: $scope.newPaciente.direccion,
            telefono: $scope.newPaciente.telefono,
            docIdentidad:$scope.newPaciente.docIdentidad,
            montoPago:$scope.newPaciente.montoPago,
            idDispositivo:$scope.newPaciente.idDispositivo,
            fecuenciaMarcapasos:$scope.newPaciente.frecuenciaMarcapasos,
            historiaClinica:[],
            consejos:[],
            eventos:[],
            usuario:$scope.newPaciente.usuario,
            contrasenia:$scope.newPaciente.contrasenia
        }
        
        console.log(paciente);
            $scope.loginPromise = $http({
                    method: 'POST',
                    url: 'http://localhost:8081/pacientes',
                    data: angular.toJson(paciente)
                }).then(function successCallback(response) {
                    console.log(response);
                    $location.path('/pacientes');
                },
                function errorCallback(response) {
                   
                    console.log("LOGIN: " + JSON.stringify(response.data));
                    $scope.msj = "Incorrect username or password.";
                }).finally(function () {

        });
    };

    }]);