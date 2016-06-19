<div id="example">
    <div class="demo-section k-content" ng-controller="RegistrationDocsController">
        
        <div style="padding-top: 1em; padding-bottom: 3em;">
            <h4>Select a category </h4>
            <select id="customers"
            kendo-drop-down-list style="width: 50%"
            k-options="" ng-model="selectedValue">
                <option></option>
                <option value="IDPROOF">Id Proof</option>
                <option value="ADDRESSPROOF">Address Proof</option>
                <option value="TAXRETURNS">Tax Returns</option>
                <option value="OTHERS">Others</option>
            </select>
        </div>
        <div class="well" style="padding-top: 2em;">
            <h4>Upload Document Files </h4>
            <input name="file"
                   type="file"
                   kendo-upload
                   k-async='{ 
                   saveUrl: "/floatinvoice/register/upload?acro="+fetchAcro(), 
                   autoUpload: true }'
                   k-select="onSelect"
                   />
        </div>
           <div style="float:right">
           
            <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button>  
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
                    <td><a href ng-click="openFile(file)">{{file.fileName}}</a></td>
                    <td>{{file.timest}}</td>
                    <td>{{file.categ}}</td>
                    <td>{{file.user}}</td>
                </tr>
            </table>
          <!-- <div align="center">
            <dir-pagination-controls/>
          </div> -->


        </div>

    </div>
<!-- removeUrl: 'remove' -->
</div>