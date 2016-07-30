var app = angular.module('finfloatInvoiceListApp');
app.controller('FinDocViewCtrl', [
  '$http','$scope', 'sharedProperties', function($http, $scope, sharedProperties) {
        console.log(sharedProperties.getProperty());
        $http.get('/floatinvoice/fin/summary/supportDocs?acro='+sharedProperties.getProperty())
                 .success(function(data){
                    $scope.fileList = data.list;
                   });
        $scope.getUrl = function(file) {
            return '/floatinvoice/downloadSupportDocs?refId='+file.refId+'&fileName='+file.fileName+'&type=pdf';
        }
}]);