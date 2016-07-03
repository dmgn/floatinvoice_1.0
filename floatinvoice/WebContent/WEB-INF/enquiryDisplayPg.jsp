<!-- index.html -->
<!DOCTYPE html>
<html ng-app="enquiryDisplayApp" >
<head>

    <!-- CSS ===================== -->
    <!-- load bootstrap -->
     <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <link rel="stylesheet" href="css/angular-material.min.css">
     <link rel="stylesheet" href="css/breadcrumbs.css">
       <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.2.504/styles/kendo.common.min.css" />
  <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.2.504/styles/kendo.default.min.css" />
    <style>
        body    { padding-top:30px; }
    </style>
    
    <!-- JS ===================== -->
    <!-- load angular -->
    <!-- Angular Material requires Angular.js Libraries -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/angular.min.js"></script>
  <script src="js/angular-animate.min.js"></script>
  <script src="js/angular-aria.min.js"></script>
  <script src="js/angular-messages.min.js"></script>
  <!-- Angular Material Library -->
  <script src="js/angular-material.min.js"></script>
  <script src="js/enquiryDisplay.js"></script>
  <link rel="stylesheet" href="//kendo.cdn.telerik.com/2016.1.412/styles/kendo.common-material.min.css" />
  <link rel="stylesheet" href="//kendo.cdn.telerik.com/2016.1.412/styles/kendo.material.min.css" />
  <script src="//kendo.cdn.telerik.com/2016.1.412/js/kendo.all.min.js"></script>
</head>
<body>
<div class="container">
<div class="row">  
    <div class="col-sm-3">
          <img src = "img/logo.jpg" height="65"/>
    </div>
     <div class="col-sm-offset-6 col-sm-3">
           <span style="float:right">
             <a href="/floatinvoice/logout?tmpUser=true" class="btn btn-primary" title="Logout" data-toggle="tooltip" >
             <span class="glyphicon glyphicon-log-out"></span></a>
          </span>

    </div>
</div>
<div class="row">  
 <div class="col-sm-12">
    <div style="padding-top: 1em;" class="demo-section k-content" ng-controller="enquiryDisplayController">
        <div style="float:right">           
<!--             <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button> -->  
<!--          <button type="button" class="btn btn-primary" ng-Click="nextAction()">Next</button>
 -->     </div>
        <div style="padding-top: 1em;">
            <h4> Enquiry list </h4>
            <table class="table table-striped">
             <tr>
                  <th></th>
                  <th><a href="" ng-click="sortField = 'fileName'">Contact Name</th>
                  <th><a href="" ng-click="sortField = 'timest'">Enquiry Date</th>
                  <th><a href="" ng-click="sortField = 'categ'">Email</th>
                  <th><a href="" ng-click="sortField = 'user'">Status</th>
                </tr>  
                <tr ng-repeat="enquiry in enquiryList | filter:search | orderBy:sortField">
                    <!-- <td><a href ng-click="openFile(file)">{{file.fileName}}</a></td> -->
                    <!--<a ng-href="{{getUrl(file)}}">-->
                   <!--  <td><input type="radio" name="refId" ng-value="{{enquiry.refId}}" ng-model="$parent.refId"/></td> -->
                   <td><input type="radio" name="enquiry" ng-value="enquiry.refId" ng-model="$parent.selectedoption"></td>
                    <td>{{enquiry.contactName}}</a></td>  
                    <td>{{enquiry.enqDate}}</td>
                    <td>{{enquiry.email}}</td>
                    <td>{{enquiry.enqStatus}}</td>
                    
                </tr>

            </table>

        </div>
        <div align="center">   
            <button type="button" class="btn btn-primary"  ng-disabled="optionSelected()" ng-Click="nextAction()">Next</button> 
        </div>

    </div>
  </div>  
  </div>
  </div>
</body>
</html>