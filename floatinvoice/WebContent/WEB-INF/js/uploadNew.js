
    angular.module("floatInvoiceListApp")
        .controller("NewUploadController", ['$scope', 'fiService', function($scope, fiService){
            $scope.onSelect = function(e) {
                    var message = $.map(e.files, function(file) { return file.name; }).join(", ");
                   // kendoConsole.log("event :: select (" + message + ")");
            }
            $scope.fetchAcro = function() {
                  return fiService.getAcronym();
            }
           
        }])
