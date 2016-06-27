package com.floatinvoice.business.listeners;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import com.floatinvoice.business.EmailService;
import com.floatinvoice.business.dao.ThirdPartyNotificationDao;
import com.floatinvoice.common.NotificationStatusEnum;
import com.floatinvoice.messages.NotificationInfoMsg;

public class ThirdPartyEmailNotifier {

	ThirdPartyNotificationDao thirdPartyNotificationDao;
	EmailService emailService;
	
	public ThirdPartyEmailNotifier(){
		
	}
	
	public ThirdPartyEmailNotifier( ThirdPartyNotificationDao thirdPartyNotificationDao, EmailService emailService ){
		this.thirdPartyNotificationDao = thirdPartyNotificationDao;
		this.emailService = emailService;
	}
	
	@Scheduled(fixedRate=5000)
	public void processTask( ){
		List<NotificationInfoMsg> workAssignments = poll();
		if(workAssignments != null && workAssignments.size() > 0){
			for(NotificationInfoMsg notifInfoMsg : workAssignments){
				if(notifInfoMsg.getRetry() < 3){
					emailService.sendEmail("FloatInvoice Notification", notifInfoMsg.getEmail(), new StringBuffer(""));
					thirdPartyNotificationDao.markNotificationStatus(notifInfoMsg.getNotificationId(), NotificationStatusEnum.DEQUEUE.getCode());					
				}else{
					thirdPartyNotificationDao.markNotificationStatus(notifInfoMsg.getNotificationId(), NotificationStatusEnum.SETASIDE.getCode());
				}
			}
		}
		
	}
	
	public List<NotificationInfoMsg> poll(){
		return thirdPartyNotificationDao.fetchAllPendingRecords();
	}
}
