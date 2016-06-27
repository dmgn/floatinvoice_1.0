
    angular.module("registrationApp")
        .service('fiService', function(){
              this.getAcronym = function(){
              return "${acronym}";
         };
        })
        .controller("RegistrationDocsController", ['$scope', 'fiService', function($scope, fiService){
            $scope.onSelect = function(e) {
                    var message = $.map(e.files, function(file) { return file.name; }).join(", ");
                   // kendoConsole.log("event :: select (" + message + ")");
            }
            $scope.fetchAcro = function() {
                  return fiService.getAcronym();
            }
            
            $scope.selectedValue = function() {
                return $scope.selectedValue;
          }
            
            $scope.downloadNow = function() {
                $http.get('/floatinvoice/invoice/templates')
                  	 .success(function(data){
	   
    			});
            }
           
        }])
