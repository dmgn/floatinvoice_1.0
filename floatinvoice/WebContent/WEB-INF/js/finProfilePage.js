 angular.module('finfloatInvoiceListApp')
  .service('sharedProperties', function () {
        var property = '';

        return {
            getProperty: function () {
                return property;
            },
            setProperty: function(value) {
                property = value;
                console.log(property);
            }
        };
    })

 .controller('FinProfileTabsCtrl', ['$scope', '$location', 'sharedProperties',
      function ($scope, $location, sharedProperties) {
      $scope.compName = 'TESTACRO';
      
      sharedProperties.setProperty($scope.compName);
      $scope.tabs = [
          { link : '#/t2/profiles/summary', label : 'Summary' },
          { link : '#/t2/profiles/directors', label : 'Directors' },
          { link : '#/t2/profiles/creditHist', label : 'Credit History' },
          { link : '#/t2/profiles/metrics', label : 'Metrics' },
          { link : '#/t2/profiles/cashflow', label : 'Cashflow' }
        ];  
      var index = -1;
      var tabList =  $scope.tabs;
      for (var i=0; i<tabList.length; i++){
        if( tabList[i].link == '#'+$location.url() ) {
            index = i;
            break;
        }
      }
      $scope.selectedTab = $scope.tabs[index];
      $scope.setSelectedTab = function(tab, alignment) {
        $scope.selectedTab = tab;
      }
      $scope.tabClass = function(tab, alignment) {
        if ($scope.selectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }
    }]);