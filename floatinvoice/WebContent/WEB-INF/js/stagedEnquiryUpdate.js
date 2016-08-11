var app = angular.module('adminfloatInvoiceListApp');

app.controller('StagedEnquiryModalViewController', [
  '$http','$scope', '$element', 'input', 'close', 
  function($http, $scope, $element, input, close) {
  //$scope.enquiry = input;
  $http.get('/floatinvoice/staged/3?refId='+input.refId+'&compId='+input.companyId)
      .success(function(data){
          $scope.enquiry = data;
      });
    $scope.respMsg = "";    
    $scope.user= {"refId":input};
    
    $scope.close = function() {
      $element.modal('hide');  
      close({
        "refId":input
      },200); // close, but give 500ms for bootstrap to animate
    //console.log('Close method in modal ended');
    };

  $scope.getUrl = function(file) {
      return '/floatinvoice/downloadSupportDocs?refId='+file.refId+'&fileName='+file.fileName+'&acro='+acro+'&type=pdf';
      //  return "/floatinvoice/download?acro="+acro+"&refId="+file.refId+"&fileName="+file.fileName+"&type=xlsx";
    }

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