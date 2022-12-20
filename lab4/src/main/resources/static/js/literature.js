var app = angular.module('literature', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});

app.controller("LiteratureController", function ($scope, $http) {

    $scope.successGetLiteraturesCallback = function (response) {
        $scope.literatureList = response.data;
        $scope.errormessage = "";
    };

    $scope.errorGetLiteraturesCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get list of literature";
    };

    $scope.getLiterature = function () {
        $http.get('/public/rest/literature').then($scope.successGetLiteraturesCallback, $scope.errorGetLiteraturesCallback);
    };

    $scope.successDeleteLiteratureCallback = function (response) {
        for (var i = 0; i < $scope.literatureList.length; i++) {
            var j = $scope.literatureList[i];
            if (j.id === $scope.deletedId) {
                $scope.literatureList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage = "";
    };

    $scope.errorDeleteLiteratureCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Unable to delete literature; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteLiterature = function (id) {
        $scope.deletedId = id;

        $http.delete('/public/rest/literature/' + id).then($scope.successDeleteLiteratureCallback, $scope.errorDeleteLiteratureCallback);
    };


    $scope.successGetLiteratureCallback = function (response) {
        $scope.literatureList.splice(0, 0, response.data);
        $scope.errormessage = "";
    };

    $scope.errorGetLiteratureCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get information on literature number " + $scope.inputnumber;
    };

    $scope.successAddLiteratureCallback = function (response) {

        $http.get('/public/rest/literature/' + $scope.inputnumber).then($scope.successGetLiteratureCallback, $scope.errorGetLiteratureCallback);
        $scope.errormessage = "";
    };

    $scope.errorAddLiteratureCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Impossible to add new literature; check if it's number is unique";
    };

    $scope.addLiterature = function () {
        $http.post('/public/rest/literature/' + $scope.inputnumber + "/" + $scope.inputname).then($scope.successAddLiteratureCallback, $scope.errorAddLiteratureCallback);
    };

});
