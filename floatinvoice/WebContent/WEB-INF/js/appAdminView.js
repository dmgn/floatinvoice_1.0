 var adminfloatInvoiceListApp = angular.module('adminfloatInvoiceListApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages']);


    adminfloatInvoiceListApp.config(
      function($locationProvider, $routeProvider, $routeSegmentProvider){
      $routeSegmentProvider.
        when('/adHome','a0').    
        when('/adManageEnquiry','e1').
        when('/e1/list','e1.list').
        when('/e1/pending','e1.pending').        
        when('/e1/staged','e1.staged').        
        when('/e1/released','e1.released');  

      $routeSegmentProvider
      .segment('a0', {
        templateUrl: '/floatinvoice/html/adminHome.html',
        controller: 'AdminHomeCtrl'
      });

      
      $routeSegmentProvider
      .segment('e1', {
        templateUrl: '/floatinvoice/html/adminManageEnquiry.html',
        controller: 'ManageEnquiryCtrl'
      });

      $routeSegmentProvider
      .within('e1')
      .segment('list', {
        templateUrl:'/floatinvoice/html/enquiryListView.html',
        controller:'EnquiryListViewCtrl'
      });

      $routeSegmentProvider
      .within('e1')
      .segment('pending', {
        templateUrl:'/floatinvoice/html/enquiryPendingView.html',
        controller:'EnquiryPendingViewCtrl'
      });

         $routeSegmentProvider
      .within('e1')
      .segment('staged', {
        templateUrl:'/floatinvoice/html/enquiryStagedView.html',
        controller:'EnquiryStagedViewCtrl'
      });

         $routeSegmentProvider
      .within('e1')
      .segment('released', {
        templateUrl:'/floatinvoice/html/enquiryReleasedView.html',
        controller:'EnquiryReleasedViewCtrl'
      });
    
    });