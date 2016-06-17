
    angular.module("floatInvoiceListApp")
        .controller("NewUploadController", ['$scope', '$http', 'fiService', function($scope, $http, fiService){
            var acro = fiService.getAcronym();  
            $http.get('/floatinvoice/invoice/viewFiles?acro='+acro)
                .success(function(data){
                    $scope.fileList = data.list;
                });

            $scope.getUrl = function(file) {
                return "/floatinvoice/download?acro="+acro+"&refId="+file.refId+"&fileName="+file.fileName+"&type=xlsx";
            }

            $scope.onSelect = function(e) {
                    var message = $.map(e.files, function(file) { return file.name; }).join(", ");
                   // kendoConsole.log("event :: select (" + message + ")");
                    $http.get('/floatinvoice/invoice/viewFiles?acro='+acro)
                         .success(function(data){
                            $scope.fileList = data.list;
                    });

            }
            $scope.fetchAcro = function() {
                  return fiService.getAcronym();
            }
           





        }])
