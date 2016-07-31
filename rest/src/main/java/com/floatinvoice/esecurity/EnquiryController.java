package com.floatinvoice.esecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.floatinvoice.business.EnquiryService;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.OrgDtlsMsg;


@Controller
public class EnquiryController {
	
	@Autowired
	EnquiryService enquiryService;

	@RequestMapping(value = { "/enquiries"}, method = RequestMethod.GET)
    public ResponseEntity<ListMsg<EnquiryFormMsg>> viewAllEnquiries(@RequestParam(value="acro", required=true) String acro) throws Exception {
        return new ResponseEntity<>(enquiryService.viewEnquiryNotifications(acro), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/enquiry/{enqStatus}"}, method = RequestMethod.GET)
    public ResponseEntity<ListMsg<EnquiryFormMsg>> viewEnquiries(@PathVariable("enqStatus") int enqStatus,
    		@RequestParam(value="orgType", required=false) String orgType) throws Exception {
        return new ResponseEntity<>(enquiryService.viewEnquiry(enqStatus, orgType), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/prospectsDocs"}, method = RequestMethod.GET)
    public ModelAndView fetchProspectsDocsPage(@RequestParam(value="refId", required=true) String refId) throws Exception {
		ModelAndView model = new ModelAndView();
        model.addObject("refId", refId);
        model.setViewName("prospectsDocsPage");
        return model;
    }
		
	@RequestMapping(value = { "/notify/acctSetup"}, method = RequestMethod.POST)
    public ResponseEntity<BaseMsg> sendAcctSetupNotification(@RequestBody OrgDtlsMsg orgDtls/*@PathVariable("refId") String refId*/) throws Exception {
        return new ResponseEntity<>(enquiryService.setupTempAcct(orgDtls), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/notify/thirdParty/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> sendThirdPartyNotification(@PathVariable("refId") String refId) throws Exception {
        return new ResponseEntity<>(enquiryService.sendThirdPartyNotification(refId), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/notify/fi/{refId}"}, method = RequestMethod.PUT)
    public ResponseEntity<BaseMsg> notifyFloatInvoice(@PathVariable("refId") String refId,
    		@RequestParam(value="acro", required=false) String acronym,
    		@RequestParam(value="user", required=false) String user) throws Exception {
        return new ResponseEntity<>(enquiryService.notifyFloatInvoice(user, acronym, refId), HttpStatus.OK);
    }
}
