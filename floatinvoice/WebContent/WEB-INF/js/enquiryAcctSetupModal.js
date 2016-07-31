var app = angular.module('adminfloatInvoiceListApp');

app.controller('EnquiryModalAcctSetupController', [
  '$http','$scope', '$element', 'input', 'close', 
  function($http, $scope, $element, input, close) {
 /* $http.get('/floatinvoice/invoice/'+input+'?acro='+fiService.getAcronym())
      .success(function(data){
          $scope.invoices = data.list;
      });*/
    $scope.respMsg = "";
    $scope.enquiry = input;
    $scope.user= {"refId":input};
    
    $scope.close = function() {
    $element.modal('hide');  
    close({
      "refId":input
    },200); // close, but give 500ms for bootstrap to animate
    //console.log('Close method in modal ended');
    };

  //  This cancel function must use the bootstrap, 'modal' function because
  //  the doesn't have the 'data-dismiss' attribute.
  $scope.cancel = function() {
    $element.modal('hide');
    //close();
    //  Manually hide the modal.
    
    
    //  Now call close, returning control to the caller.
    /*close({
      name: $scope.name,
      age: $scope.age
    }, 500);*/ // close, but give 500ms for bootstrap to animate
  };

  $scope.checkRespMsg = function(){
    if($scope.respMsg.length > 1){
        console.log("returning true");
        return true;
    }else{
        console.log("returning false");
        return false;
    }   
  };
  $scope.submitForm = function() {
                // check to make sure the form is completely valid
      console.log("invalid form");
      if ($scope.userForm.$valid) {
          console.log("valid form");
          $http({
              method:'POST',
              url:'/floatinvoice/notify/acctSetup',
              data:$scope.user,
              xhrFields: {
                withCredentials: true
              },
              headers:{
                'Content-Type':'application/json'
              }
              }).then(function successCallback(response) {
                  console.log(response);
                  $scope.errRespMsg = "";
                  $scope.respMsg = "Message saved successfully."
                  $scope.close();
                }, function errorCallback(response) {
                  console.log(response);
                  $scope.errRespMsg = "Error saving data."
                  
            });
      }
  };

}]);