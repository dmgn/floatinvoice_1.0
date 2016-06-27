package com.floatinvoice.esecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.floatinvoice.business.EnquiryService;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;


@Controller
public class EnquiryController {
	
	@Autowired
	EnquiryService enquiryService;

	@RequestMapping(value = { "/enquiry/{enqStatus}"}, method = RequestMethod.GET)
    public ResponseEntity<ListMsg<EnquiryFormMsg>> viewEnquiries(@PathVariable("enqStatus") int enqStatus) throws Exception {
        return new ResponseEntity<>(enquiryService.viewEnquiry(enqStatus), HttpStatus.OK);
    }
	
	/*@RequestMapping(value = { "/notify/acctSetup/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> sendAcctSetupNotification(@PathVariable("refId") String refId) throws Exception {
        return new ResponseEntity<>(enquiryService.sendAcctSetupNotification(refId), HttpStatus.OK);
    }*/
	
	@RequestMapping(value = { "/notify/acctSetup/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> sendAcctSetupNotification(@PathVariable("refId") String refId) throws Exception {
        return new ResponseEntity<>(enquiryService.setupTempAcct(refId), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/notify/thirdParty/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> sendThirdPartyNotification(@PathVariable("refId") String refId) throws Exception {
        return new ResponseEntity<>(enquiryService.sendThirdPartyNotification(refId), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/notify/fi/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> notifyFloatInvoice(@PathVariable("refId") String refId,
    		@RequestParam(value="acro", required=true) String acronym,
    		@RequestParam(value="user", required=true) String user) throws Exception {
        return new ResponseEntity<>(enquiryService.notifyFloatInvoice(user, acronym, refId), HttpStatus.OK);
    }
}
