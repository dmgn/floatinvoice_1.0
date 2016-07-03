 var nbfcApp = angular.module('nbfcApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages']);

    nbfcApp.config(
      function($locationProvider, $routeProvider, $routeSegmentProvider){
      
      $routeSegmentProvider.
        when('/nbfcHome','seg1').    
        when('/nbfcManageEnquiry','seg2');
       /* when('/e1/list','e1.list').
        when('/e1/pending','e1.pending').        
        when('/e1/staged','e1.staged').        
        when('/e1/released','e1.released');  */

      $routeSegmentProvider
      .segment('seg1', {
        templateUrl: '/floatinvoice/html/nbfcHome.html',
        controller: 'NbfcHomeCtrl'
      });

      $routeSegmentProvider
      .segment('seg2', {
        templateUrl: '/floatinvoice/html/nbfcEnquiry.html',
        controller: 'NbfcEnquiryCtrl'
      });
      
    
    });