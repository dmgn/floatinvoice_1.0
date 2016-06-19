 // create angular app
  var registrationApp = angular.module('registrationApp');
        // create angular controller
        registrationApp.controller('RegistrationSignInController', function($scope, $window, $http, $location) {
            $scope.user = {};
            $scope.respMsg = "";
            //$scope.respMsg = undefined;
            // function to submit the form after all validation has occurred            
            $scope.submitForm = function() {
                // check to make sure the form is completely valid
                if ($scope.userForm.$valid) {
                    
                    $http({
                        method:'POST',
                        url:'/floatinvoice/register/signInInfo',
                        data:$scope.user,
                        headers:{'Content-Type':'application/json'}
                        }).then(function successCallback(response) {
                            //$window.location.replace('/floatinvoice/register/orgInfoPage');
                            console.log(response);
                            $scope.errRespMsg = "";
                            $scope.respMsg = "Message saved successfully."
                          }, function errorCallback(response) {
                            $scope.errRespMsg = "Error saving data."
                      });
                }
            }
            $scope.checkRespMsg = function(){

                if($scope.respMsg.length > 1){
                    console.log("returning true");
                    return true;
                }else{
                    console.log("returning false");
                    return false;
                }   
            }

            $scope.nextAction = function(){
                //bcsetSelectedTab(bctabs[1]);
                $location.path("/companyInfo");
            }

            $scope.nextPage = function(){
                      $http({
                        method:'GET',
                        url:'/floatinvoice/register/orgInfoPage',
                        data:$scope.user,
                        headers:{'Content-Type':'application/json'}
                        }).then(function successCallback(response) {
                            //$window.location.replace('/floatinvoice/register/orgInfoPage');
                            console.log(response);
                          }, function errorCallback(response) {
                            console.log(response);
                      });
            }

        });

