package rightel.ocs.util;

import static rightel.ocs.listener.DriverListener.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import rightel.ocs.listener.ApplicationLogger;
import rightel.ocs.properties.allObjects;

public class DbManager {

	private Connection con = null;
	private ApplicationLogger logger = null;

	public DbManager() {

		try {
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\rightel\\ocs\\properties\\config.properties");
		config.load(fis);
		String logFileName = config.getProperty("testlogfile");
		logger = new ApplicationLogger(logFileName,false);
		
		} catch (Throwable t) {
			t.printStackTrace();
			
		}


	}
	
	public boolean setConncetion() {
		
		try {
			Class.forName(allObjects.dbDriver);
			con = DriverManager.getConnection(allObjects.dbIOTConnectionURL, allObjects.dbusername,
					allObjects.dbPassword);

			if (!con.isClosed())
				//logger.info("Successfully connected to PLSQL");
			return true;
		
		} catch(SQLException s) {
			
			//s.printStackTrace();
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			//e.printStackTrace();
		}
		
		//logger.info("Could not connect to PLSQL");
		return false;
	}

	public void printQuery(String query) {
		
		//logger.info(query);
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				//System.out.println(rs.getString(3));
				//System.out.println(rs.getString(4));
				//System.out.println(rs.getString(5));
				//System.out.println(rs.getString(6));
				//System.out.println(rs.getString(7));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public HashMap<String, Object> get_SmsCdr(String msisdn, String sessionId) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int even_price=0;
		int calc_amount=0;
		String query = "select r.fk_rating_event_price, r.rating_calc_amount from ocscdrdb.tbl_sms_cdr r where r.servedmsisdn='" + msisdn
				+ "' and r.session_id='" + sessionId + "' and r.start_time > sysdate-1 order by r.pk_sms_cdr_id desc";
		logger.info(query);
		
		try {

			ResultSet rs = getQueryResult(query);
			rs.next();
			even_price = rs.getInt(1);
			calc_amount = rs.getInt(2);
			resultMap.put("fk_rating_event_price", even_price);
			resultMap.put("rating_calc_amount", calc_amount);
			return resultMap;

		} catch (Exception e) {

			return null;
		}
		
	}
	
	public HashMap<String, Object> get_VoiceCdr(String msisdn, String sessionId) {
		
		int used_value=0,price=0,calc_amount=0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String query = "select r.used_value, r.fk_rating_event_price, r.rating_calc_amount   from ocscdrdb.tbl_voice_cdr r  "
				+ "where r.servedmsisdn = '" + msisdn +"'    and r.session_id = '"+ sessionId +"' "
				+ "and r.start_time > sysdate - 1  order by r.pk_voice_cdr_id desc";
		logger.info(query);
		
		try {

			ResultSet rs = getQueryResult(query);
			while(rs.next()) {
				used_value = used_value + rs.getInt(1);
				price = rs.getInt(2);
				calc_amount = calc_amount + rs.getInt(3);
			}
			resultMap.put("used_value", used_value);
			resultMap.put("fk_rating_event_price", price);
			resultMap.put("rating_calc_amount", calc_amount);
			
			return resultMap;

		} catch (Exception e) {

			return null;
		}		
	}
	
	public ArrayList<HashMap> get_DataCdr(String msisdn, String sessionId ){
		
		ArrayList<HashMap> resultArray = new ArrayList<HashMap>();
		HashMap<String, Object> resultMap;
		String query = "select r.rating_group,r.fk_rating_event_price,r.rating_calc_amount,r.used_value,r.rating_used_value_rounded "
				+ "from ocscdrdb.tbl_data_cdr r where r.servedmsisdn = '"+msisdn+"'   and r.session_id = '"+ sessionId +"'  and r.start_time > sysdate - 1 order by r.pk_data_cdr_id desc";
		logger.info(query);
		
		try {
			ResultSet rs = getQueryResult(query);
			while(rs.next()) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("rating_group", rs.getInt(1));
				resultMap.put("fk_rating_event_price", rs.getInt(2));
				resultMap.put("rating_calc_amount", rs.getInt(3));
				resultMap.put("used_value", rs.getInt(4));
				resultMap.put("rating_used_value_rounded", rs.getInt(5));
				//resultMap.put("fk_rating_event_price_step", rs.getString(6));
				//resultMap.put("fk_rating_event_price_timespan", rs.getString(7));
				resultArray.add(resultMap);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return resultArray;
		
	}

	
	public HashMap<String, Object> get_RatingPrice(int pkId) {
		
		String query = "select  r.used_unit_percision, r.calc_value,r.price_name from ocsdb.tbl_rating_event_price r where r.pk_id=" + pkId;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();  
		
		logger.info(query);
		try {

			ResultSet rs = getQueryResult(query);
			rs.next();
			resultMap.put("used_unit_percision", rs.getInt(1));
			resultMap.put("calc_value", rs.getDouble(2));
			resultMap.put("price_name", rs.getString(3));
			return resultMap;

		} catch (Exception e) {

			return null;
		}		
	}
	

	public List<String> getQuery(String query) {

		logger.info(query);
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<String> values = new ArrayList<String>();
			while (rs.next()) {
				values.add(rs.getString(1));
			}

			return values;			
		} catch(Exception e) {
			
			return null;
		}

	}

	public ResultSet getQueryResult(String query) {

		logger.info(query);
		try {

			Statement st = con.createStatement();
			return st.executeQuery(query);

		} catch (Exception e) {
			
			e.printStackTrace();

			return null;
		}

	}


	public void setDBClose() {

		try {

			con.close();
			logger.info("Connection closed successfully.");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
