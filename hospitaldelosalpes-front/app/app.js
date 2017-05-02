'use strict';
// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngStorage',
  'myApp.version',
   'myApp.auth',
    'myApp.doctor',
    'myApp.pacientes',
    'myApp.pacienteNuevo',
    'myApp.paciente',
    'myApp.pacientehome'
]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.otherwise({
        redirectTo: '/auth'
    });
}]);
