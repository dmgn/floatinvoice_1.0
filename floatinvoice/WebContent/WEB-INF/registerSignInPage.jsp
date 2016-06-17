<!-- index.html -->

<!-- apply angular app and controller to our body -->
<body  onload='document.userForm.email.focus();'>
<div class="col-sm-6 ">
    <!-- <img src = "../img/logo.jpg" height=100/> -->
    <!-- PAGE HEADER -->
<!--     <div><h2>Sign Up</h2></div>
 -->   
    <!-- FORM -->
    <!-- pass in the variable if our form is valid or invalid -->

    <!-- <form name="userForm" ng-submit="submitForm()" novalidate>
        <div class="form-group" ng-class="{ 'has-error' : userForm.email.$invalid }">
            <label>Email</label>
            <input type="email" name="email" class="form-control" ng-model="user.email">
            <p ng-show="userForm.email.$invalid" class="help-block">Enter a valid email.</p>
        </div>        
        <br/>
        <div class="form-group" ng-class="{ 'has-error' : userForm.passwd.$invalid }">
            <label>Password</label>
            <input type="password" name="passwd" class="form-control" ng-model="user.passwd" ng-minlength="3">
            <p ng-show="userForm.passwd.$error.minlength" class="help-block">Password is too short.</p>           
        </div>
        </br>
        <div class="form-group" ng-class="{ 'has-error' : userForm.passwd.$invalid }">
            <label>Confirm Password</label>
            <input type="password" name="confirmPasswd" class="form-control" ng-model="user.confirmPasswd" ng-minlength="3">
            <p ng-show="userForm.confirmPasswd.$error.minlength" class="help-block">Password is too short.</p>           
        </div>
        </br>
        <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid">Submit</button>
    </form> -->
    <md-content layout-padding>
    <div>
     <span >
       <h5 ng-style={color:'green'}>{{respMsg}}</h5>
       <h5 ng-style={color:'red'}>{{errRespMsg}}</h5>
     </span>
      <form name="userForm" style="margin-top: 5em;" ng-submit="submitForm()" novalidate>
        
          <md-input-container class="md-block" flex-gt-sm>
            <label>Email</label>
            <input type="email" name="email" ng-model="user.email" required>
          </md-input-container>
          <md-input-container class="md-block" flex-gt-sm>
            <label>Password</label>
            <input type="password" name="passwd"  ng-model="user.passwd" ng-minlength="3" required>
          </md-input-container>
          <md-input-container class="md-block" flex-gt-sm>
            <label>Confirm Password</label>
            <input type="password" name="passwd"  ng-model="user.confirmPasswd" ng-minlength="3" required>
          </md-input-container>
          <div style="float:right">
            <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid || checkRespMsg()">Save</button>  
            <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button>  
          </div>
      </form>
    </div>
  </md-content>
</div><!-- col-sm-8 -->
</body>
