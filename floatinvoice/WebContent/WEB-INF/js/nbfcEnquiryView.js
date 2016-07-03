 angular.module('nbfcApp')
.controller('NbfcEnquiryCtrl', [ '$scope', '$window', '$http', 'NbfcAcroNameService', 'ModalService', function($scope, $window, $http, NbfcAcroNameService,
  ModalService) {
 $scope.selectedoption = "";

 $http.get('/floatinvoice/enquiries?acro='+NbfcAcroNameService.getAcronym())
       .success(function(data){
            $scope.enquiryList = data.list;
       });


 /* $scope.nextAction = function(){
  	$window.location.replace('/floatinvoice/prospectsDocs?refId='+$scope.selectedoption);
  }*/

  $scope.optionSelected = function(){  	
  	return $scope.selectedoption == "" ? true : false;
  }


  $scope.displayFiles = function(){
      
      console.log($scope.selectedoption);
      ModalService.showModal({
      templateUrl: "html/supportDocModal.html",
      controller: "ModalController",
      inputs: {
        input:$scope.selectedoption
      }
    }).then(function(modal) {
      console.log(modal);
      modal.element.modal();
      modal.close.then(function(result) {
        //$scope.complexResult  = "Name: " + result.name + ", age: " + result.age;
      });
    });
  }


}]);