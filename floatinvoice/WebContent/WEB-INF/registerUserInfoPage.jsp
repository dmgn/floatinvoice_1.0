


<!-- apply angular app and controller to our body -->
 
    <!-- PAGE HEADER -->
    <div class="col-sm-8">
     
      <md-content layout-padding>
    <div>
     <span >
       <h5 ng-style={color:'green'}>{{respMsg}}</h5>
       <h5 ng-style={color:'red'}>{{errRespMsg}}</h5>
     </span>
      <form name="userForm" style="margin-top: 5em;" ng-submit="submitForm()" novalidate>
<!--         <div layout-gt-xs="row">
          <md-input-container class="md-block" flex-gt-xs>
            <label>Company (Disabled)</label>
            <input ng-model="user.company" disabled>
          </md-input-container>
          <md-datepicker ng-model="user.submissionDate" md-placeholder="Enter date">
          </md-datepicker>
        </div> -->
        <div layout-gt-xs="row"> 
          <md-input-container class="md-block" flex-gt-sm>
           <label>Director's First Name</label>
           <input type="text" name="directorFName" ng-model="user.directorFName"/>
          </md-input-container>
          <md-input-container class="md-block" flex-gt-sm>
           <label>Director's Last Name</label>
           <input type="text" name="directorLName"  ng-model="user.directorLName"/>
          </md-input-container>
        </div>
        <div layout-gt-xs="row">
         <md-input-container class="md-block" flex-gt-sm>
          <label>Pan Card Number</label>
          <input type="text" name="panCardNo" ng-model="user.panCardNo"/>         
         </md-input-container>
         <md-input-container class="md-block" flex-gt-sm>
          <label>Aadhar Card Number</label>
          <input type="text" name="aadharCardId" ng-model="user.aadharCardId"/>         
         </md-input-container>
        </div>    
       
        <md-input-container class="md-block" flex-gt-sm>
          <label>Bank Account Number</label>
          <input type="text" name="bankAcctNo" ng-model="user.bankAcctNo" ng-pattern="/^([0-9]{10})$/" >
          <div class="hint" ng-show="showHints">(##########)</div>
          <div ng-messages="userForm.bankAcctNo.$error" ng-hide="showHints">
            <div ng-message="pattern">########## - Please enter a valid acct number.</div>
          </div>
        </md-input-container>

        <div layout-gt-xs="row">
         <md-input-container class="md-block" flex-gt-sm>
          <label>Bank Name</label>
          <input type="text" name="bankName" ng-model="user.bankName"/>         
         </md-input-container>
         <md-input-container class="md-block" flex-gt-sm>
          <label>IFSC Code</label>
          <input type="text" name="ifscCode" ng-model="user.ifscCode" ng-pattern="/^([0-9]{5})$/"/>  
          <div class="hint" ng-show="showHints">(#####)</div>
          <div ng-messages="userForm.ifscCode.$error" ng-hide="showHints">
            <div ng-message="pattern">##### - Please enter a valid 5 digit IFSC code.</div>
          </div>       
         </md-input-container>
        </div>    
        
         <div style="float:right">
            <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid || checkRespMsg()">Save</button>  
            <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button>  
         </div>
     </form>
    </div>
  </md-content>
    <!-- FORM -->
    <!-- pass in the variable if our form is valid or invalid -->

    <!-- <form name="userForm" ng-submit="submitForm()" novalidate>
         <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.directorFName.$invalid }">
            <label>Director First Name</label>
            <input type="text" name="directorFName" class="form-control" ng-model="user.directorFName">
        </div>  
        <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.directorLName.$invalid }">
            <label>Director Last Name</label>
            <input type="text" name="directorLName" class="form-control" ng-model="user.directorLName">
        </div> 
        <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.panCardNo.$invalid }">
            <label>Pan Card Number</label>
            <input type="text" name="panCardNo" class="form-control" ng-model="user.panCardNo">
        </div>  
        <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.aadharCardId.$invalid }">
            <label>Aadhar Card Id</label>
            <input type="text" name="aadharCardId" class="form-control" ng-model="user.aadharCardId">
        </div> 
        <div class="col-sm-8 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.bankAcctNo.$invalid }">
            <label>Bank Account Number</label>
            <input type="text" name="bankAcctNo" class="form-control" ng-model="user.bankAcctNo">            
        </div>
        <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.bankName.$invalid }">
            <label>Bank Name</label>
            <input type="text" name="bankName" class="form-control" ng-model="user.bankName">
        </div>  
        <div class="col-sm-3 col-sm-offset-2 form-group" ng-class="{ 'has-error' : userForm.ifscCode.$invalid }">
            <label>IFSC Code</label>
            <input type="text" name="ifscCode" class="form-control" ng-model="user.ifscCode">
        </div> 
        <br/>        
        <div class="col-sm-8 col-sm-offset-2">
            <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid">Submit</button>
        </div>
    </form> -->
    </div>
