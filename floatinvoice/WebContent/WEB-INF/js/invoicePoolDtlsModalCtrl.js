var app = angular.module('floatInvoiceListApp');

app.controller('ModalInvoicePoolController', [
  '$http','$scope', '$element', 'input', 'close', 'fiService',
  function($http, $scope, $element, input, close, fiService) {
  console.log(input);
 $http.get('/floatinvoice/invoice/'+input+'?acro='+fiService.getAcronym())
      .success(function(data){
          $scope.invoices = data.list;
      });

    $scope.close = function() {
    /*close({
      name: $scope.name,
      age: $scope.age
    }, 500);*/ // close, but give 500ms for bootstrap to animate
    };

  //  This cancel function must use the bootstrap, 'modal' function because
  //  the doesn't have the 'data-dismiss' attribute.
  $scope.cancel = function() {

    //  Manually hide the modal.
    $element.modal('hide');
    
    //  Now call close, returning control to the caller.
    /*close({
      name: $scope.name,
      age: $scope.age
    }, 500);*/ // close, but give 500ms for bootstrap to animate
  };

}]);