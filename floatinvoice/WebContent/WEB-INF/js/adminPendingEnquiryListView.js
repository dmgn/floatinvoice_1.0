 angular.module('adminfloatInvoiceListApp')
 .controller('EnquiryPendingViewCtrl', ['$scope', '$http', '$routeParams',
      function($scope, $http, $routeParams){
        $http.get('/floatinvoice/enquiry/2?orgType=ADMIN')
        .success(function(data){
          $scope.enquiries = data.list;
          $scope.sortField = 'enqDate';
          $scope.refresh = function(refId){
            console.log(refId);
          var index = -1;
          var invoiceList =  $scope.enquiries;
          for (var i=0; i<invoiceList.length; i++){
            if( invoiceList[i].refId == refId ) {
                index = i;
                break;
            }
          }
          $scope.enquiries.splice(index, 1);
        };
          $scope.notify = function (refId){
          $scope.refId = refId;
          var data = JSON.stringify({
            refId : $scope.refId            
          });
          $http.put('/floatinvoice/notify/thirdParty'+$scope.refId, data).success($scope.refresh($scope.refId));
        };

          });
    }]);