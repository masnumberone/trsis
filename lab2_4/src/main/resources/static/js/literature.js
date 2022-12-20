var app = angular.module('literature', []);

app.controller("LiteratureController", function ($scope, $http) {

    $scope.getLiterature = function () {
        $http.get('/public/rest/literature').success(function (data, status, headers, config) {
            $scope.literatureList = data;
            for (var i = 0; i < $scope.literatureList.length; i++) {
                $scope.literatureList[i].edit = 0;
            }
        }).error(function (data, status, headers, config) {
            if (data.message === 'Time is out') {
                $scope.finishByTimeout();
            }
        });
    };

    $scope.delete = function (id) {
        $http.delete('/public/rest/literature/' + id).success(function (data, status, headers, config) {
            for (var i = 0; i < $scope.literatureList.length; i++) {
                var j = $scope.literatureList[i];
                if (j.id === id) {
                    $scope.literatureList.splice(i, 1);
                    break;
                }
            }
        }).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });
    };

    $scope.addSource = function () {
        $http.post('/public/rest/literature/' + $scope.source).success(function (data, status, headers, config) {
            $scope.literatureList.splice($scope.literatureList.length, 0, data);
        }
        ).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });

    };

    $scope.putSource = function (id) {
        $http.put('/public/rest/literature/' + $scope.id + "/" + $scope.sourcePatch).success(function (data, status, headers, config) {
            for (var i = 0; i < $scope.literatureList.length; i++) {
                var m = $scope.literatureList[i];
                if (m.id === id) {
                    $scope.literatureList.splice(i, 1, data);
                    break;
                }
                if (i === $scope.literatureList.length - 1) {
                    $scope.literatureList.splice(i + 1, 0, data);
                    break;
                }
            }
        }
        ).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });
    }
});
