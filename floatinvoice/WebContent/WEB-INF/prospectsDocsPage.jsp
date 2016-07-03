<!-- index.html -->
<!DOCTYPE html>
<html ng-app="tmpProspectsDocs" >
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
  <script src="js/prospectsDocs.js"></script>
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
    <div style="padding-top: 1em;" class="demo-section k-content" ng-controller="tmpProspectsDocsController">

        <div class="well" style="padding-top: 1em;">
            <h4>Upload Document Files </h4>
               <!-- <h4>Select a category </h4> -->
            <span>Select a category : </span>
            <select id="customers"
            kendo-drop-down-list 
            ng-model="selectedValue">
                <option value="IDPROOF" selected>Id Proof</option>
                <option value="ADDRESSPROOF">Address Proof</option>
                <option value="TAXRETURNS">Tax Returns</option>
                <option value="STATEMENTS">Bank Statements</option>
                <option value="OTHERS">Others</option>
            </select><br/><br/>
            <input name="file"
                   type="file"
                   kendo-upload
                   k-async='{ 
                   saveUrl: "/floatinvoice/register/upload", 
                   autoUpload: true }'
                   k-select="onSelect"
                   />
        </div>
        <div style="float:right">           
<!--             <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button> -->  
         <button type="button" class="btn btn-primary" ng-Click=notify('${refId}')>Notify Float Invoice</button>
        </div>
        <div style="padding-top: 1em;">
            <h4> File list </h4>
            <table class="table table-striped">
                <tr>
                  <th><a href="" ng-click="sortField = 'fileName'">Name</td>
                  <th><a href="" ng-click="sortField = 'timest'">Upload Time</th>
                  <th><a href="" ng-click="sortField = 'categ'">Category</th>
                  <th><a href="" ng-click="sortField = 'user'">User</th>
                </tr>                
                <tr ng-repeat="file in fileList | filter:search | orderBy:sortField">
                    <!-- <td><a href ng-click="openFile(file)">{{file.fileName}}</a></td> -->
                    <td><a ng-href="{{getUrl(file)}}">{{file.fileName}}</a></td>  


                    <td>{{file.timest}}</td>
                    <td>{{file.categ}}</td>
                    <td>{{file.user}}</td>
                </tr>
            </table>
        </div>
        
    </div>
  </div>  
  </div>
  </div>
</body>
</html>