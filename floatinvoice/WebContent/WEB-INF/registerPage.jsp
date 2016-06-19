<!-- index.html -->
<!DOCTYPE html>
<html ng-app="registrationApp" >
<head>
  <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.2.504/styles/kendo.common.min.css" />
  <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.2.504/styles/kendo.default.min.css" />
    <!-- CSS ===================== -->
    <!-- load bootstrap -->
  <link rel="stylesheet" href="css/bootstrap.min.css"> 
  <link rel="stylesheet" href="css/angular-material.min.css">
  <link rel="stylesheet" href="css/breadcrumbs.css">
  <style>
      body    { padding-top:30px; }
  </style>
  <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- JS ===================== -->
    <!-- load angular -->
    <!-- Angular Material requires Angular.js Libraries -->
  <script src="js/angular.min.js"></script>
  <script src="js/angular-animate.min.js"></script>
  <script src="js/angular-aria.min.js"></script>
  <script src="js/angular-messages.min.js"></script>
  <!-- Angular Material Library -->
  <script src="js/angular-material.min.js"></script>
  <script src="js/angular-route.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/angular-route-segment/1.4.0/angular-route-segment.min.js"></script>
  <script src="js/angular-modal-service.min.js"></script>
  <script src="js/registerApp.js"></script>
  <script src="js/registerAppSignIn.js"></script>
  <script src="js/registerOrgInfo.js"></script>
  <script src="js/registerUsrInfo.js"></script>
  <script src="js/supportingDoc.js"></script>
  <script src="js/modalcontroller.js"></script>

<link rel="stylesheet" href="//kendo.cdn.telerik.com/2016.1.412/styles/kendo.common-material.min.css" />
  <link rel="stylesheet" href="//kendo.cdn.telerik.com/2016.1.412/styles/kendo.material.min.css" />
  <script src="//kendo.cdn.telerik.com/2016.1.412/js/kendo.all.min.js"></script>
</head>

<!-- apply angular app and controller to our body -->
<body ng-controller="RegistrationController">
  <div class="container">
      <div class="col-sm-12">
          <img src = "img/logo.jpg" height="100"/>
      </div><!-- col-sm-8  col-sm-offset-2-->

      <div class="col-sm-12" style="margin-top: 2em;">       
        
          <div class="custbreadcrumb flat" >
            <a href="{{bctabs[0].link}}" ng-class='bctabClass(bctabs[0])' ng-click='bcsetSelectedTab(bctabs[0])'>{{bctabs[0].label}}</a>
            <a href="{{bctabs[1].link}}" ng-class='bctabClass(bctabs[1])' ng-click='bcsetSelectedTab(bctabs[1])'>{{bctabs[1].label}}</a>
            <a href="{{bctabs[2].link}}" ng-class='bctabClass(bctabs[2])' ng-click='bcsetSelectedTab(bctabs[2])'>{{bctabs[2].label}}</a>
            <a href="{{bctabs[3].link}}" ng-class='bctabClass(bctabs[3])' ng-click='bcsetSelectedTab(bctabs[3])'>{{bctabs[3].label}}</a>

          </div>
        
      </div>
       <div class="col-sm-12">
          <div app-view-segment="0"></div>
        </div>
  </div><!-- /container -->
</body>
</html>