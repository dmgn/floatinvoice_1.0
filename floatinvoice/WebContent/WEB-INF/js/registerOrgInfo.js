  // create angular app
        var validationApp = angular.module('registrationApp');
         /*validationApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('customTheme')
            .primaryPalette('grey')
            .accentPalette('pink');
        });*/
        // create angular controller
        validationApp.controller('RegistrationOrgInfoController', function($scope, $window, $http, $location) {
            $scope.user= {};
            $scope.respMsg = "";
            $scope.states = ('Andhra Pradesh,Arunachal Pradesh,Assam,Bihar,Chhattisgarh,Goa,Gujarat,Haryana,Himachal Pradesh,Jammu and Kashmir,Jharkhand,Karnataka,Kerala,Madhya Pradesh,Maharashtra,Manipur,Meghalaya,Mizoram,Nagaland,Orissa,Punjab,Rajasthan,Sikkim,Tamil Nadu,Tripura,Uttarakhand,Uttar Pradesh,West Bengal,Andaman and Nicobar Islands,Chandigarh,Dadra and Nagar Haveli,Daman and Diu,Delhi,Lakshadweep,Pondicherry').split(',').map(function(state) {
            return {abbrev: state};
            });

            $scope.nextAction = function(){
                //bcsetSelectedTab(bctabs[1]);
                $location.path("/personalInfo");
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
            // function to submit the form after all validation has occurred            
            $scope.submitForm = function() {
                // check to make sure the form is completely valid
                if ($scope.userForm.$valid) {
                    
                    $http({
                        method:'POST',
                        url:'/floatinvoice/register/corpInfo',
                        data:$scope.user,
                        xhrFields: {
                            withCredentials: true
                        },
                        headers:{'Content-Type':'application/json'
                                 }
                        }).then(function successCallback(response) {
                            //$window.location.replace('/floatinvoice/register/usrInfoPage');
                            console.log(response);
                            $scope.errRespMsg = "";
                            $scope.respMsg = "Message saved successfully."
                          }, function errorCallback(response) {
                            console.log(response);
                            $scope.errRespMsg = "Error saving data."
                            
                      });
                }
            };
        });