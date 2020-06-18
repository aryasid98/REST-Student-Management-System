package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtil;

public class PaymentDaoImpl implements PaymentDao{
	private static Logger logger= Logger.getLogger(PaymentDaoImpl.class);

	//Payment Modes available
	@Override
	public HashMap<Integer, String> paymentModes() {
		HashMap<Integer, String> paymentModes=new HashMap<Integer,String>();
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.VIEW_PAYMENT_MODES; 
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){	
			int paymentId= rs.getInt("paymentId");
			String mode=rs.getString("mode");
			paymentModes.put(paymentId,mode);
			}
			return paymentModes;
	       
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}

}
