var tmpLogin = angular.module('tmpLogin', ['ngMaterial', 'ngMessages']);
        tmpLogin.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });
        // create angular controller
        tmpLogin.controller('tmpLoginController', function($scope, $window, $http) {
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
                            $window.location.replace('/floatinvoice/enquiryDisplay');
                            console.log(response);
                          }, function errorCallback(response) {
                            console.log(response);
                      });
                }
            };
        });