 var adminfloatInvoiceListApp = angular.module('adminfloatInvoiceListApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages']);


    adminfloatInvoiceListApp.config(
      function($locationProvider, $routeProvider, $routeSegmentProvider){
      $routeSegmentProvider.
        when('/buyerAlleged','a0').    
        when('/buyerRejected','a1');

      $routeSegmentProvider
      .segment('a0', {
        templateUrl: '/floatinvoice/html/adminHome.html',
        controller: 'AdminHomeCtrl'
      });

      
      $routeSegmentProvider
      .segment('a1', {
        templateUrl: '/floatinvoice/html/adminDashboard.html',
        controller: 'AdminDasboardCtrl'
      });

    
    });