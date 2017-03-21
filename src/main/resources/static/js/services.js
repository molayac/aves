/**
 * Created by Marlon Olaya on 20/03/2017.
 */

restMod.constant('urls', {
    BASE: 'http://localhost:8080/',
    AVE_SERVICE_API: 'http://localhost:8080/aves/'
});

restMod.factory('restSrv', ['$http', 'urls', function ($http, urls) {

    var restSrv = {};

    restSrv.getAves = function () {
        return $http.get(urls.AVE_SERVICE_API);
    };

    restSrv.getZonas = function () {
        return $http.get(urls.AVE_SERVICE_API + 'zonas');
    };

    return restSrv;

}]);

crudMod.constant('urls', {
    BASE: 'http://localhost:8080/',
    AVE_SERVICE_API: 'http://localhost:8080/aves/'
});

crudMod.factory('crudSrv',
    ['$http', '$q', 'urls',
        function ($http, $q, urls) {

            var factory = {
                createAve: createAve,
                updateAve: updateAve,
                removeAve: removeAve,
                search: search

            };
            return factory;

            function search(searchName, zonaid) {
                var config = {
                    params: {
                        "zona": zonaid,
                        "n": searchName
                    }
                };
                return $http.get(urls.AVE_SERVICE_API + "search", config);
            }

            function createAve(ave) {
                console.log('Creating Ave');
                return $http.post(urls.AVE_SERVICE_API, ave);
            }

            function updateAve(ave, id) {
                console.log('Actualizar datos del ave con id: ' + id);
                return $http.put(urls.AVE_SERVICE_API + id, ave);
            }

            function removeAve(id) {
                console.log('Eliminando Ave con id: ' + id);
                return $http.delete(urls.AVE_SERVICE_API + id);
            }

        }
    ]);
