'use strict';

samaiaApp.factory('usuarioFactory', function($http, SERVER_URL) {

	const URL = SERVER_URL + '/usuarios';

	let list = function() {
		return $http.get(URL);
	};

	let save = function(usuario) {
		if (usuario.id) {
            return $http.put(URL + `/${usuario.id}`, usuario);
		} else {
			return $http.post(URL, usuario);
		}
	};

	let find = function(id) {
		return $http.get(URL, id);
	};

	let remove = function(id) {
		return $http.delete(URL + `/${id}`);
	};

	return {list, save, find, remove};
});
