 // create angular app
  var registrationApp = angular.module('registrationApp', ['ngRoute', 'route-segment', 'view-segment', 
  'ngMaterial', 'ngMessages','kendo.directives', 'angularModalService', 'ngAnimate']);
      /*  registrationApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });*/


registrationApp.config(function ($locationProvider, $routeProvider, $routeSegmentProvider) {
    $routeSegmentProvider.
      when('/signInInfo','s1').
      when('/companyInfo','s2').
      when('/personalInfo','s3').
      when('/documents','s4');
   
    $routeSegmentProvider
      .segment('s1', {
          templateUrl: '/floatinvoice/register/signInPage',
          controller: 'RegistrationSignInController'
    });

    $routeSegmentProvider
      .segment('s2', {
          templateUrl: '/floatinvoice/register/orgInfoPage',
          controller: 'RegistrationOrgInfoController'
    });

    $routeSegmentProvider
      .segment('s3', {
          templateUrl: '/floatinvoice/register/usrInfoPage',
          controller: 'RegistrationUsrInfoController'
    });

    $routeSegmentProvider
      .segment('s4', {
          templateUrl: '/floatinvoice/register/docs',
          controller: 'RegistrationDocsController'
    });

 });

/*registrationApp.service('fiService', function(){
  this.getAcronym = function(){
    return "${acronym}";
  };
});*/

        // create angular controller
registrationApp.controller('RegistrationController', ['$scope', '$location', function($scope, $location) {

      $scope.bctabs = [
        { link : '#/signInInfo', label : 'Sign In Info' },
        { link : '#/companyInfo', label : 'Company Info' },
        { link : '#/personalInfo', label : 'Personal Info' },
        { link : '#/documents', label : 'Documents' },
        
      ];
      $scope.bcselectedTab = $scope.bctabs[0];   

      $scope.bcsetSelectedTab = function(tab) {
        $scope.bcselectedTab = tab;
      }
      $scope.bctabClass = function(tab) {
        if ($scope.bcselectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }

}]);