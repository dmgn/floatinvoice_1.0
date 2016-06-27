 angular.module('adminfloatInvoiceListApp')
 .controller('ManageEnquiryCtrl', ['$scope', '$location', 
      function ($scope, $location) {

      $scope.adtabs = [
          { link : '#/e1/list', label : 'New' },
          { link : '#/e1/pending', label: 'Pending'},
          { link : '#/e1/staged', label : 'Staged' },
          { link : '#/e1/released', label : 'Released' }
        ];  
/*     var index = -1;
      var adtabList =  $scope.adtabs;
      for (var i=0; i<adtabList.length; i++){
        if( adtabList[i].link == '#'+$location.url() ) {
            index = i;
            break;
        }
      }
      $scope.adselectedTab = $scope.adtabs[index];
      $scope.adsetSelectedTab = function(tab, alignment) {
        $scope.adselectedTab = tab;
      }
      $scope.adtabClass = function(tab, alignment) {
        if ($scope.adselectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }*/
    }]);