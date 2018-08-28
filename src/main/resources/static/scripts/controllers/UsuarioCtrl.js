'use strict';

/**
 * Usuários Controller
 */
samaiaApp.controller('UsuarioCtrl', function ($scope, $rootScope, usuarioFactory, usuarios) {

    $scope.usuarios = usuarios.data;

    $rootScope.pageInfo = {
        icon: 'user',
        title: 'Usuários',
        subTitle: 'Cadastro de Usuários'
    };

    let recarregarLista = function () {
        usuarioFactory.list().then(result => {
            $scope.usuarios = result.data;
        });
    };

    $scope.editar = function (usuario) {
        $scope.usuario = {...usuario};
    };

    $scope.gravar = function () {
        usuarioFactory.save($scope.usuario).then(() => {
            recarregarLista();
            $scope.usuario = {};
        });
    };

    $scope.excluir = function (usuario) {
        if (confirm(`Deseja remover o usuário ${usuario.nome}?`)) {
            usuarioFactory.remove(usuario.id).then(() => {
                recarregarLista();
            });
        }
    };

    $scope.cancelar = function () {
        $scope.usuario = {};
    };

});
