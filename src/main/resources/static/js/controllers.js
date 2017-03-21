/**
 * Created by Marlon Olaya on 19/03/2017.
 */
app.controller('aveCtrl', function ($scope, $rootScope, restSrv, crudSrv, commonService, $timeout) {
    $scope.myOrder = "id";
    $scope.message = "Welcome to your first angular :)";
    $scope.successMessage = '';
    $scope.listPaises = [];

    $scope.resetForm = function(){
        $scope.modalTitle = "Crea una nueva ave!!!";
        resetForm();
    }

    $scope.paisesRelese=function(){
        $scope.listPaises=[];
        $scope.validator = false;
    }

    $scope.paisesPush = function () {
        if ($scope.zonaId === undefined || $scope.paisDsNombre === undefined
            || $scope.zonaId === null || $scope.paisDsNombre === null) {
            return false;
        }
        var row = {
            dsNombre: $scope.paisDsNombre,
            zona: $scope.zonaId
        };

        $scope.listPaises.push(row);
        console.log("Push Pais " + JSON.stringify($scope.listPaises));
        $scope.paisDsNombre = "";
        $scope.validator = true;

    }

    restSrv.getZonas().then(function (response) {
        $scope.zonas = response.data;
    });

    $scope.loadData = function (x) {
        $scope.validator=true;
        $scope.aveId = x.id;
        $scope.dsNombreComun = x.dsNombreComun;
        $scope.dsNombreCientifico = x.dsNombreCientifico;
        $scope.listPaises = x.paises;
        $scope.modalTitle = "Edita el ave id="+x.id+"...";
        $("#customModal").modal("toggle");
    }

    $scope.reset = function () {
        self.successMessage = '';
        $scope.myForm.$setPristine(); //reset Form
    }

    $scope.search = function () {
        var zonaid = undefined;
        var searchName = $scope.searchName;

        if ($scope.selectedZona != undefined)
            zonaid = $scope.selectedZona.id;

        crudSrv.search(searchName, zonaid).then(function (response) {
            $scope.resultSearch = response.data;
        });
    };

    $scope.submit = function () {

        if (!$scope.validator)
            return false;
        var ave = {
            dsNombreComun: $scope.dsNombreComun,
            dsNombreCientifico: $scope.dsNombreCientifico,
            paises: $scope.listPaises
        }
        console.log("Submit ave id: " + $scope.aveId + " data: " + JSON.stringify(ave));
        if ($scope.aveId == 0) {
            crudSrv.createAve(ave).then(function (response) {
                console.log("Something");
                resetForm();
                $scope.successMessage = "Ave creada correctamente! -> " + JSON.stringify(response.data);
                restSrv.getAves().then(function (response) {
                    $scope.resultSearch = response.data;
                });
            });
        } else {
            crudSrv.updateAve(ave, $scope.aveId).then(function (response) {
                resetForm();
                $scope.successMessage = "Ave actualizada correctamente! -> " + JSON.stringify(response.data);
                restSrv.getAves().then(function (response) {
                    $scope.resultSearch = response.data;
                });
            });
        }

        $("#customModal").modal("toggle");
        $timeout(function(){
           $("#success-alert").alert("close");
        }, 5000);
    }

    $scope.removeAve = function (id) {
        crudSrv.removeAve(id).then(function (response) {
            $scope.successMessage = "Se eliminó exitosamente el Ave con id: " + id;
            restSrv.getAves().then(function (response) {
                $scope.resultSearch = response.data;
            });
        });
    }

    function resetForm() {
        $scope.dsNombreComun = "";
        $scope.dsNombreCientifico = "";
        $scope.paisDsNombre = "";
        $scope.zonaId = "";
        $scope.aveId = "0";
        $scope.listPaises = [];
    }


});

