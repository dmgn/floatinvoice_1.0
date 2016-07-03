
    angular.module("registrationApp")
        .controller("RegistrationDocsController", 
          ['$scope', '$http','$window', 'ModalService', 
          function($scope, $http, $window, ModalService){
            var acro = "";
            $http.get('/floatinvoice/register/docs/summary')
                 .success(function(data){
                    $scope.fileList = data.list;
            });

//  $scope.file_url = '/floatinvoice/downloadSupportDocs?refId='+$scope.file.refId+'&fileName='+$scope.file.fileName+'&acro=COTIND&type=pdf';

            $scope.getUrl = function(file) {
              return '/floatinvoice/downloadSupportDocs?refId='+file.refId+'&fileName='+file.fileName+'&acro='+acro+'&type=pdf';
              //  return "/floatinvoice/download?acro="+acro+"&refId="+file.refId+"&fileName="+file.fileName+"&type=xlsx";
            }


            $scope.getUploadUrl = function() {

                return '/floatinvoice/register/upload?category='+$scope.selectedValue;
            }
           /* $scope.getUploadUrl = function() {
              console.log("test");
              var json = { 
                         "saveUrl": '/floatinvoice/register/upload?category='+$scope.selectedValue,
                         "autoUpload": true 
                        };
              console.log(JSON.stringify(json));
              return JSON.stringify(json);

            }*/

            $scope.nextAction = function(){
               $window.location.replace('/floatinvoice/welcomePage');
            }
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
