var enquiryDisplayApp = angular.module('enquiryDisplayApp', ['ngMaterial', 'ngMessages','kendo.directives',  'ngAnimate']);
enquiryDisplayApp.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('light-blue')
    .accentPalette('green');
});
enquiryDisplayApp.controller('enquiryDisplayController', function($scope, $window, $http) {
 $scope.selectedoption = "";

 $http.get('/floatinvoice/enquiry/2')
       .success(function(data){
            $scope.enquiryList = data.list;
       });


  $scope.nextAction = function(){
  	console.log($scope.selectedoption);
    $window.location.replace('/floatinvoice/prospectsDocs?refId='+$scope.selectedoption);
  }

  $scope.optionSelected = function(){
  	
  	return $scope.selectedoption == "" ? true : false;
  }


});