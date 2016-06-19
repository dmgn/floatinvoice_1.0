
    angular.module("registrationApp")
        .controller("RegistrationDocsController", 
          ['$scope', '$http','$window', 'fiService', 'ModalService', 
          function($scope, $http, $window, fiService, ModalService){
            var acro = fiService.getAcronym();
            $http.get('/floatinvoice/register/docs/summary?acro='+acro)
                 .success(function(data){
                    $scope.fileList = data.list;
            });
            $scope.nextAction = function(){
               $window.location.replace('/floatinvoice/welcomePage');
            }
            $scope.onSelect = function(e) {
              e.sender.options.async.saveUrl = e.sender.options.async.saveUrl +'&category='+$scope.selectedValue
              var message = $.map(e.files, function(file) { 
                return file.name; 
              }).join(", ");
              // kendoConsole.log("event :: select (" + message + ")");
            }
            $scope.fetchAcro = function() {
                  return fiService.getAcronym();
            }
            
            $scope.selectedValueFn = function() {
                console.log($scope.selectedValue);
                return $scope.selectedValue;
            }
            
            $scope.downloadNow = function() {
                $http.get('/floatinvoice/invoice/templates')
                  	 .success(function(data){	   
    			            });
            }

            $scope.openFile = function(file){
                
                console.log(file);
                ModalService.showModal({
                templateUrl: "html/supportDocModal.html",
                controller: "ModalController",
                inputs: {
                  input:file
                }
              }).then(function(modal) {
                console.log(modal);
                modal.element.modal();
                modal.close.then(function(result) {
                  //$scope.complexResult  = "Name: " + result.name + ", age: " + result.age;
                });
              });
            }
           
        }])
