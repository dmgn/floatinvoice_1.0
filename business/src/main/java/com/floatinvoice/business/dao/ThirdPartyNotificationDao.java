package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.NotificationInfoMsg;

public interface ThirdPartyNotificationDao {

		List<NotificationInfoMsg> fetchAllPendingRecords();
		void markNotificationStatus(int notificationId, int statusFlag);
}
