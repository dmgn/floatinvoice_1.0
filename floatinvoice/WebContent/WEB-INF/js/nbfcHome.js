 angular.module('nbfcApp')
.controller('NbfcHomeCtrl', function($scope, $window, $http) {
 $scope.selectedoption = "";

 $http.get('/floatinvoice/enquiry/2')
       .success(function(data){
            $scope.enquiryList = data.list;
       });


  $scope.nextAction = function(){
  	$window.location.replace('/floatinvoice/prospectsDocs?refId='+$scope.selectedoption);
  }

  $scope.optionSelected = function(){
  	
  	return $scope.selectedoption == "" ? true : false;
  }


});