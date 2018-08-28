'use strict';

/**
 * Modulo principal da aplicação
 */
const samaiaApp = angular
    .module('samaiaApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.router'
    ])
    .config(function ($urlRouterProvider, $stateProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                controller: 'HomeCtrl',
                templateUrl: '/samaia-crud/views/home.html'
            })
            .state('usuario', {
                url: '/usuario',
                controller: 'UsuarioCtrl',
                templateUrl: '/samaia-crud/views/usuario.html',
                resolve: {
                    usuarios: function (usuarioFactory) {
                        return usuarioFactory.list();
                    }
                }
            });

        $urlRouterProvider.otherwise('/');
    }).constant(
        'SERVER_URL', '/samaia-crud/api'
    );
