package com.floatinvoice.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.floatinvoice.messages.NotificationInfoMsg;


@Repository
public class JdbcThirdPartyNotificationDao implements ThirdPartyNotificationDao {
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcThirdPartyNotificationDao(){
		
	}

	public JdbcThirdPartyNotificationDao( DataSource dataSource){
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<NotificationInfoMsg> fetchAllPendingRecords() {
		final String sql = "SELECT * FROM THIRDPARTY_NOTIFICATION_QUEUE WHERE ENQ_FLAG = 0 LIMIT 10";
		List<NotificationInfoMsg> lst = jdbcTemplate.query(sql, new NotificationRowMapper());
		return lst;
	}
	
	private class NotificationRowMapper implements RowMapper<NotificationInfoMsg>{

		@Override
		public NotificationInfoMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			NotificationInfoMsg notification = new NotificationInfoMsg();
			notification.setEmail(rs.getString("EMAIL"));
			notification.setRecipientOrgId(rs.getInt("RECIPIENT_ORG_ID"));
			notification.setRefId(rs.getString("REF_ID"));
			notification.setEnquiryId(rs.getInt("ENQUIRY_ID"));
			notification.setNotificationId(rs.getInt("NOTIFICATION_ID"));
			notification.setEnqFlag(rs.getInt("ENQ_FLAG"));			
			return notification;
		}
		
	}

	@Override
	public void markNotificationStatus(int notificationId, int statusFlag) {
		final String sql = "UPDATE THIRDPARTY_NOTIFICATION_QUEUE TNQ SET TNQ.ENQ_FLAG = :statusFlag WHERE TNQ.NOTIFICATION_ID = :notificationId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("notificationId", notificationId);
		paramMap.addValue("statusFlag", statusFlag);
		int row = jdbcTemplate.update(sql, paramMap);		
	}
}
