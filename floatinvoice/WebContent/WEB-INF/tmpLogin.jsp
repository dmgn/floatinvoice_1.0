<!-- index.html -->
<!DOCTYPE html>
<html ng-app="tmpApp" >
<head>
    <!-- CSS ===================== -->
    <!-- load bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <link rel="stylesheet" href="css/angular-material.min.css">
    <style>
        body    { padding-top:30px; }
    </style>
    
    <!-- JS ===================== -->
    <!-- load angular -->
    <!-- Angular Material requires Angular.js Libraries -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/angular.min.js"></script>
  <script src="js/angular-animate.min.js"></script>
  <script src="js/angular-aria.min.js"></script>
  <script src="js/angular-messages.min.js"></script>
  <!-- Angular Material Library -->
  <script src="js/angular-material.min.js"></script>
    <script>
        // create angular app
        var tmpApp = angular.module('tmpApp', ['ngMaterial', 'ngMessages']);
        tmpApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });
        // create angular controller
        tmpApp.controller('tmpAppController', function($scope, $window, $http) {
            $scope.user= {};
            // function to submit the form after all validation has occurred            
            $scope.submitForm = function() {
                // check to make sure the form is completely valid
                if ($scope.userForm.$valid) {
                    
                    $http({
                        method:'POST',
                        data:$scope.user,
                        url:'/floatinvoice/usrLogin',
                        headers:{'Content-Type':'application/json', 'remote-user':$scope.user.email}
                        }).then(function successCallback(response) {
                            $window.location.replace('/floatinvoice/docUpload');
                            console.log(response);
                          }, function errorCallback(response) {
                            console.log(response);
                      });
                }
            };
        });
    </script>
</head>

<!-- apply angular app and controller to our body -->
<body ng-controller="tmpAppController" onload='document.userForm.email.focus();'>
<div class="container">
<div class="col-sm-6 col-sm-offset-2">
    <img src = "img/logo.jpg" height=100/>
    <div class="page-header"><h1>Sign In</h1></div>
    <md-content layout-padding>
        <div>
          <form name="userForm" ng-submit="submitForm()" novalidate>
              <md-input-container class="md-block" flex-gt-sm>
                <label>Email</label>
                <input type="email" name="email" ng-model="user.email">
              </md-input-container>
              <md-input-container class="md-block" flex-gt-sm>
                <label>Password</label>
                <input type="password" name="passwd"  ng-model="user.passwd" ng-minlength="3">
              </md-input-container>
              <md-button type="submit" class="md-raised md-primary" ng-disabled="userForm.$invalid">Submit</md-button>
          </form>
        </div>
    </md-content>
</div>
</div>
</body>
</html>