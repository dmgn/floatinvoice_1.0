var tmpProspectsDocs = angular.module('tmpProspectsDocs', ['ngMaterial', 'ngMessages','kendo.directives',  'ngAnimate']);
tmpProspectsDocs.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('light-blue')
    .accentPalette('green');
});
// create angular controller
tmpProspectsDocs.controller('tmpProspectsDocsController', function($scope, $window, $http) {
 $http.get('/floatinvoice/register/docs/summary')
       .success(function(data){
            $scope.fileList = data.list;
       });
    $scope.user= {};
    // function to submit the form after all validation has occurred            
    $scope.submitForm = function() {
        // check to make sure the form is completely valid
        if ($scope.userForm.$valid) {
            
            $http({
                method:'POST',
                data:$scope.user,
                url:'/floatinvoice/docUpload',
                headers:{'Content-Type':'application/json', 'remote-user':$scope.user.email}
                }).then(function successCallback(response) {
                    console.log(response);
                  }, function errorCallback(response) {
                    console.log(response);
              });
        }
    };

     $scope.onSelect = function(e) {
              
              e.sender.options.async.saveUrl = '/floatinvoice/register/upload';
              e.sender.options.async.saveUrl = e.sender.options.async.saveUrl +'?category='+$scope.selectedValue
             /* var message = $.map(e.files, function(file) {                
                return file.name; 
              }).join(", ");*/
              // kendoConsole.log("event :: select (" + message + ")");
              $http.get('/floatinvoice/register/docs/summary')
                 .success(function(data){
                    $scope.fileList = data.list;
              });
            
      };

       $scope.notify = function(refId) {

          $http({
                method:'PUT',
                data:$scope.user,
                url:'/floatinvoice/notify/fi/'+refId,
                headers:{'Content-Type':'application/json'}
                }).then(function successCallback(response) {
                    console.log(response);
                  }, function errorCallback(response) {
                    console.log(response);
              });

       }
});

