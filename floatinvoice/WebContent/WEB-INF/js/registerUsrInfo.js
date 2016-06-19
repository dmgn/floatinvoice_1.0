 var validationApp = angular.module('registrationApp');

     /*   validationApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });*/
        // create angular controller
        validationApp.controller('RegistrationUsrInfoController', 
            function($scope, $window, $http, $location) {
            $scope.user= {};
            $scope.respMsg = "";
            $scope.nextAction = function(){
                //bcsetSelectedTab(bctabs[1]);
                $location.path("/documents");
            }

            // function to submit the form after all validation has occurred            
            $scope.submitForm = function() {
                // check to make sure the form is completely valid
                if ($scope.userForm.$valid) {
                    
                    $http({
                        method:'POST',
                        url:'/floatinvoice/register/usrInfo',
                        data:$scope.user,
                        xhrFields: {
                            withCredentials: true
                        },
                        headers:{'Content-Type':'application/json'}
                        }).then(function successCallback(response) {
                            //$window.location.replace('/floatinvoice/welcomePage');
                            console.log(response);
                            $scope.errRespMsg = "";
                            $scope.respMsg = "Message saved successfully."
                          }, function errorCallback(response) {
                            console.log(response);
                            $scope.errRespMsg = "Error saving data."

                      });
                }
            };

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
                $location.path("/documents");
            }

            $scope.nextPage = function(){
                      $http({
                        method:'GET',
                        url:'/floatinvoice/register/docs',
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