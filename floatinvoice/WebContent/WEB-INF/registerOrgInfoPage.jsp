
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
               <label>Name</label>
               <input type="text" name="compName" ng-model="user.compName">
              </md-input-container>
              <md-input-container class="md-block" flex-gt-sm>
               <label>Acronym</label>
               <input type="text" name="acronym"  ng-model="user.acronym" ng-minlength="3">
              </md-input-container>
        </div>
        <div layout-gt-xs="row">
         <md-input-container class="md-block" flex-gt-sm>
          <label>Phone Number</label>
          <input name="phoneNo" ng-model="user.phoneNo" ng-pattern="/^([0-9]{10})$/" />
          <div class="hint" ng-show="showHints">(##########)</div>
          <div ng-messages="userForm.phoneNo.$error" ng-hide="showHints">
            <div ng-message="pattern">########## - Please enter a valid phone number.</div>
          </div>
         </md-input-container>
        </div>    
       <div layout-gt-xs="row">   
        <md-input-container class="md-block" flex-gt-sm>
          <label>Street</label>
          <input type="text" name="street" ng-model="user.street">
        </md-input-container>
        <md-input-container class="md-block" flex-gt-sm>
          <label>City</label>
          <input type="text" name="city" ng-model="user.city">
        </md-input-container>
        </div>
        <div layout-gt-xs="row">
        <md-input-container class="md-block" flex-gt-sm>
            <label>State</label>
            <md-select ng-model="user.state">
              <md-option ng-repeat="state in states" value="{{state.abbrev}}">
                {{state.abbrev}}
              </md-option>
            </md-select>
          </md-input-container>
          <md-input-container class="md-block" flex-gt-sm>
            <label>Postal Code</label>
            <input name="zipCode" ng-model="user.zipCode" placeholder="12345"
                   required ng-pattern="/^[0-9]{5}$/" md-maxlength="5">
            <div ng-messages="userForm.zipCode.$error" role="alert" multiple>
              <div ng-message="required" class="my-message">You must supply a postal code.</div>
              <div ng-message="pattern" class="my-message">That doesn't look like a valid postal code.
              </div>
              <!-- <div ng-message="md-maxlength" class="my-message">
                Don't use the long version silly...we don't need to be that specific...
              </div> -->
            </div>
          </md-input-container>
          </div>
          <label class="md-block" flex-gt-sm>Profession Type</label>        
              <div class="radio">
                <label>
                    <input type="radio" name="orgType" value="BANK" ng-model="user.orgType">
                    Financier
                </label>
              </div>
                <div class="radio">
                <label>
                    <input type="radio" name="orgType" value="SELLER" ng-model="user.orgType">
                    Seller
                </label>
              </div>
              <div class="radio">
                <label>
                    <input type="radio" name="orgType" value="BUYER" ng-model="user.orgType">
                    Buyer
                </label>
              </div>
             <div style="float:right">
              <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid || checkRespMsg()">Save</button>  
              <button type="button" class="btn btn-primary" ng-disabled="checkRespMsg()? false : true" ng-Click="nextAction()">Next</button>  
            </div>
     </form>
    </div>
  </md-content>
</div><!-- col-sm-8 -->
