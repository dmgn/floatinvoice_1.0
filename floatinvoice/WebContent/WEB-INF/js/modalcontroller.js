var app = angular.module('registrationApp');

app.controller('ModalController', [
  '$scope', '$element', 'input', 'close', 'fiService',
  function($scope, $element, input, close, fiService) {
  $scope.file = input;
  $scope.file_url = '/floatinvoice/downloadSupportDocs?refId='+$scope.file.refId+'&fileName='+$scope.file.fileName+'&acro=COTIND&type=pdf';
  //  This close function doesn't need to use jQuery or bootstrap, because
  //  the button has the 'data-dismiss' attribute.
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