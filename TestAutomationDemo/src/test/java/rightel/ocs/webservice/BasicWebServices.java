package rightel.ocs.webservice;

import java.io.FileInputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rightel.ocs.listener.ApplicationLogger;
import rightel.ocs.properties.allObjects;

public class BasicWebServices {

	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> rgArrayList = new ArrayList<HashMap>();

	private final int thresholdVoiceTime = 180;
	private final int thresholdDataVolume = 3145728;
	
	private ApplicationLogger logger = null;

	public BasicWebServices() {
		
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
	// set rg and volume from user and put it in arrayList
	public void setRgList(long rg, long volume) {

		HashMap<String, Long> rgMap = new HashMap<String, Long>();
		rgMap.put("rg", rg);
		rgMap.put("cc_tot_octs", volume);
		rgArrayList.add(rgMap);
	}

	// clear all rg inserted into arrayList
	public void rgListClear() {

		rgArrayList.clear();

	}

	// return arrayList of rg and their volume
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> getRgList() {

		return rgArrayList;
	}

	public int getThresholdVoiceTime() {

		return thresholdVoiceTime;
	}

	// if length of number equal 10 , add 98 in first of msisdn
	private String addPrefix(String msisdn) {

		if (!msisdn.startsWith("98")) {

			return "98" + msisdn;

		} else {

			return msisdn;
		}
	}

	// if length of number equal 12 , remove 98 in first of msisdn
	private String removePrefix(String msisdn) {

		if (msisdn.startsWith("98")) {

			return msisdn.substring(2);

		} else {

			return msisdn;
		}
	}

	public String newSessionId(String prefix) {
		String sessionId;
		long counter = getEpochInSecond();

		if (prefix.equals("")) {

			sessionId = Long.toString(counter);

		} else {
			sessionId = prefix + "_" + Long.toString(counter);
		}

		return sessionId;
	}

	// Return milladi date and time in moment
	private String getCurrentDateTime() {

		Instant instant = Instant.now();
		LocalDateTime localDateTime = instant.atZone(ZoneId.of("Asia/Tehran")).toLocalDateTime();
		String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		return formattedDate;
	}

	// return EpochTime in miliSecond in moment
	public long getEpochInMillisecond() {

		long epochInMillisecond = Instant.now().toEpochMilli();
		// System.out.println("Epoch in Millisecond: " + epochInMillisecond);

		return epochInMillisecond;
	}

	// return EpochTime of desire date in miliSecond
	public long getEpochInMillisecond(String dateTime) {

		try {
			// Date format is like "2024-08-05T13:15:30"
			ZoneId zoneId = ZoneId.of("Asia/Tehran");

			LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
			long epochInMillisecond = localDateTime.atZone(zoneId).toInstant().toEpochMilli();

			// System.out.println("Epoch in Millisecond: " + epochInMillisecond);
			return epochInMillisecond;

		} catch (DateTimeParseException e) {

			return 0;
		}
	}

	// return EpochTime in second in moment
	public long getEpochInSecond() {

		long epochInSecond = Instant.now().getEpochSecond();
		// System.out.println("Epoch in Millisecond: " + epochInMillisecond);

		return epochInSecond;
	}

	// return EpochTime of desire date in second
	public long getEpochInSecond(String dateTime) {

		try {
			// Date format is like "2024-08-05T13:15:30"
			ZoneId zoneId = ZoneId.of("Asia/Tehran");

			LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
			long epochInSecond = localDateTime.atZone(zoneId).toInstant().getEpochSecond();

			// System.out.println("Epoch in Millisecond: " + epochInMillisecond);
			return epochInSecond;

		} catch (DateTimeParseException e) {

			return 0;
		}
	}

	public Response smsService(long post_exp, long evt_ts, String sess_id, int reqd_act, String sub_id_dat,
			String calli_vlr_no, String src_addr, String dest_addr, int code_scheme) {

		String url = allObjects.basicUri +  "smsService";
		
		// Set the base URI for your API
		RestAssured.baseURI = url;

		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

		// Configure Json Body
		JSONObject requestParams = new JSONObject();

		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("cmd_method", "ccr");
		header.put("svc_ctx_type", 3);
		header.put("post_exp", post_exp);

		HashMap<String, String> subId = new HashMap<String, String>();
		subId.put("sub_id_dat", addPrefix(sub_id_dat));
		subId.put("sub_id_typ", "0");

		ArrayList<Object> subIdArray = new ArrayList<Object>();
		subIdArray.add(subId);

		HashMap<String, Object> p2pSmsInfo = new HashMap<String, Object>();
		p2pSmsInfo.put("00__calli_vlr_no", addPrefix(calli_vlr_no));
		p2pSmsInfo.put("00__smsc_addr", "989200000920");
		p2pSmsInfo.put("00__sm_id", "6e7b858d52");
		p2pSmsInfo.put("00__sm_len", 6);
		p2pSmsInfo.put("00__mo_msc_addr", "989200000110");
		p2pSmsInfo.put("00__mt_msc_addr", "");
		p2pSmsInfo.put("00__src_addr", addPrefix(src_addr));
		p2pSmsInfo.put("00__dest_addr", addPrefix(dest_addr));
		p2pSmsInfo.put("00__fee_addr", addPrefix(src_addr));
		p2pSmsInfo.put("00__fee_flag", 1);
		p2pSmsInfo.put("00__fee_typ", 0);
		p2pSmsInfo.put("00__fee_ltd_msg_num", 0);
		p2pSmsInfo.put("00__fee_single", 0);
		p2pSmsInfo.put("00__fee_fixed", 0);
		p2pSmsInfo.put("00__msg_pid", 0);
		p2pSmsInfo.put("00__stat_rpt_req", 1);
		p2pSmsInfo.put("00__org_grp", 48);
		p2pSmsInfo.put("00__snd_res", 0);
		p2pSmsInfo.put("00__calli_svc", "00 00");
		p2pSmsInfo.put("00__calld_svc", 33);
		p2pSmsInfo.put("00__org_msg_id", "");
		p2pSmsInfo.put("00__dat_code_scheme", code_scheme);

		HashMap<String, Object> svcInfo = new HashMap<String, Object>();

		svcInfo.put("00__p2psms_info", p2pSmsInfo);

		HashMap<String, Object> body = new HashMap<String, Object>();
		body.put("evt_ts", evt_ts);
		body.put("sess_id", sess_id);
		body.put("cc_req_no", 1);
		body.put("cc_req_typ", 4);
		body.put("reqd_act", 0);
		body.put("svc_id", 0);
		body.put("sub_id__arr", subIdArray);
		body.put("00__svc_info", svcInfo);

		requestParams.put("header", header);
		requestParams.put("body", body);

		logger.info(requestParams);
		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);

		// System.out.println(response.getStatusCode());

		return response;
	}

	public Response voiceService(long post_exp, long evt_ts, String sess_id, int req_no, int req_typ,
			String sub_id_dat, int cc_tm, String calli_pty_no, String vlr_no, String calld_pty_no, String calld_pty_id,
			int call_typ) {

		String url = allObjects.basicUri +  "voiceService";
		
		// Set the base URI for your API
		RestAssured.baseURI = url;

		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

		// Configure Json Body
		JSONObject requestParams = new JSONObject();

		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("cmd_method", "ccr");
		header.put("svc_ctx_type", 1);
		header.put("post_exp", post_exp);

		HashMap<String, Object> subId1 = new HashMap<String, Object>();
		subId1.put("sub_id_dat", addPrefix(sub_id_dat));
		subId1.put("sub_id_typ", 0);

		HashMap<String, Object> subId2 = new HashMap<String, Object>();
		subId2.put("sub_id_dat", "34234234523423");
		subId2.put("sub_id_typ", 0);

		ArrayList<Object> subIdArray = new ArrayList<Object>();
		subIdArray.add(subId1);
		subIdArray.add(subId2);

		HashMap<String, Object> usuMap = new HashMap<String, Object>();
		usuMap.put("cc_tm", cc_tm);

		ArrayList<Object> usuArray = new ArrayList<Object>();
		usuArray.add(usuMap);

		HashMap<String, Object> msccMap = new HashMap<String, Object>();
		msccMap.put("usu__arr", usuArray);

		ArrayList<Object> msccArray = new ArrayList<Object>();
		msccArray.add(msccMap);

		HashMap<String, Object> userEquipMap = new HashMap<String, Object>();
		userEquipMap.put("usr_equip_info_typ", 0);
		userEquipMap.put("usr_equip_info_val", "34 33 32 32 30 30 32 30 31 31 39 30 36 39 32");

		HashMap<String, Object> inInfoMap = new HashMap<String, Object>();
		inInfoMap.put("chm__called_imei", 2602);

		HashMap<String, Object> csInfoMap = new HashMap<String, Object>();
		csInfoMap.put("00__svc_key", 101);
		csInfoMap.put("00__calli_ptys_cat", 10);
		csInfoMap.put("00__calli_pty_no", addPrefix(calli_pty_no));
		csInfoMap.put("00__calli_pty_no_nat", 3);
		csInfoMap.put("00__vlr_no", addPrefix(vlr_no));
		csInfoMap.put("00__calli_cellid_or_sai", "47922");
		csInfoMap.put("00__calli_lai", "4322029116");
		csInfoMap.put("00__calld_pty_no", addPrefix(calld_pty_no));
		csInfoMap.put("00__calld_imei", 365214);
		csInfoMap.put("00__calld_pty_no_nat", 0);
		csInfoMap.put("00__org_calld_pty_id", addPrefix(calld_pty_id));
		csInfoMap.put("00__org_calld_pty_no_nat", 0);
		csInfoMap.put("00__bearer_svc_code", "31 37");
		csInfoMap.put("00__tele_svc_code", "37 37");
		csInfoMap.put("00__call_ref_no", "30 30 36 30 34 32 30 36 39 30 32 31 30 30 31 30 30 30 30 30 30 30 30 30");
		csInfoMap.put("00__msc_addr", "989200000110");
		csInfoMap.put("00__tmz", "31 34");
		csInfoMap.put("00__start_tm", evt_ts);
		csInfoMap.put("00__on_off_net_flag", 3);
		csInfoMap.put("00__hid_calli_pty_flag", 1);
		csInfoMap.put("00__ft_call_typ", call_typ);
		csInfoMap.put("orig_state_id", "3715376741");

		HashMap<String, Object> svc_infoMap = new HashMap<String, Object>();
		svc_infoMap.put("00__cs_info", csInfoMap);

		HashMap<String, Object> body = new HashMap<String, Object>();
		body.put("usr_name", "Rightel");
		body.put("evt_ts", evt_ts);
		body.put("sess_id", sess_id);
		body.put("orig_host", "PC001");
		body.put("term_cause", 4);
		body.put("orig_state_id", 524);
		body.put("orig_realm", "ExampleRealm");
		body.put("cc_req_no", req_no);
		body.put("cc_req_typ", req_typ);
		body.put("sub_id__arr", subIdArray);
		body.put("msi", 1);
		body.put("mscc__arr", msccArray);
		body.put("usr_equip_info", userEquipMap);
		body.put("in_info", inInfoMap);
		body.put("00__svc_info", svc_infoMap);

		requestParams.put("header", header);
		requestParams.put("body", body);

		logger.info(requestParams.toString());
		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);

		// System.out.println(response.getStatusCode());

		return response;

	}

	public Response voiceServiceAutomatic(long post_exp, long evt_ts, String sess_id, int req_no, int req_typ,
			String sub_id_dat, int cc_tm, String calli_pty_no, String vlr_no, String calld_pty_no, String calld_pty_id,
			int call_typ) {

		Response response;
		JsonPath json;
		Map<Object, Object> responseBody;
		int rcValue;
		int remainVoiceTime = 0;
		int runMethodCounter = 0;
		boolean voiceUpdate = true;

		// Initialize Voice Call
		response = voiceService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, 0, calli_pty_no, vlr_no,
				calld_pty_no, calld_pty_id, call_typ);

		// System.out.println(response.getStatusCode());
		// To Check if initializing voice method was successful.
		if (response.getStatusCode() == 200) {

			// Get json body of response
			json = response.jsonPath();

			// get json of bodyMap of response and check rc value
			responseBody = json.getMap("body");
			rcValue = (int) responseBody.get("rc");

			// System.out.println(rcValue);
			if (rcValue == 2001) {

				// Starting run voice webSerice in update mode
				req_typ = 2;

				// Check cc_time is greater than threshold or not
				if (cc_tm > thresholdVoiceTime) {

					// Calculate Out of part and remaining of cc_time
					runMethodCounter = cc_tm / thresholdVoiceTime;
					remainVoiceTime = cc_tm % thresholdVoiceTime;

					// run voice method in update mode
					while (runMethodCounter > 0) {

						response = voiceService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat,
								thresholdVoiceTime, calli_pty_no, vlr_no, calld_pty_no, calld_pty_id, call_typ);

						// To Check call voice method was successful or not
						if (response.getStatusCode() == 200) {

							json = response.jsonPath();
							responseBody = json.getMap("body");
							rcValue = (int) responseBody.get("rc");

							// if calling voice method was not successful, running voice method in terminate
							// mode and getting out of while loop
							if (rcValue != 2001) {

								req_typ = 3;
								response = voiceService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, 0,
										calli_pty_no, vlr_no, calld_pty_no, calld_pty_id, call_typ);

								if (response.getStatusCode() != 200) {

									System.out.println(response.getStatusCode());
								}

								// Change status of voiceUpdate value to false;
								voiceUpdate = false;
								break;
							}
						}
						runMethodCounter--;
					}

					// Checking if voice method successfully run in update mode or not
					if (voiceUpdate) {

						// run voice method in terminate mode
						req_typ = 3;
						response = voiceService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, remainVoiceTime,
								calli_pty_no, vlr_no, calld_pty_no, calld_pty_id, call_typ);

						if (response.getStatusCode() != 200) {

						}
					}

				} else {

					// run voice method in terminate mode
					req_typ = 3;
					response = voiceService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, remainVoiceTime,
							calli_pty_no, vlr_no, calld_pty_no, calld_pty_id, call_typ);
		
				}
			}

		}

		return response;
	}

	@SuppressWarnings("rawtypes")
	public Response dataService(long post_exp, long evt_ts, String sess_id, int req_no, int req_typ,
			String sub_id_dat, ArrayList<HashMap> usageInfoArray, String sgsn_mcc_mnc) {

		String url = allObjects.basicUri +  "dataService";
		
		// Set the base URI for your API
		RestAssured.baseURI = url;

		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

		// Configure Json Body
		JSONObject requestParams = new JSONObject();

		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("cmd_method", "ccr");
		header.put("svc_ctx_type", 2);
		header.put("post_exp", post_exp);

		HashMap<String, Object> subId1 = new HashMap<String, Object>();
		subId1.put("sub_id_dat", addPrefix(sub_id_dat));
		subId1.put("sub_id_typ", 0);

		HashMap<String, Object> subId2 = new HashMap<String, Object>();
		subId2.put("sub_id_dat", "432200307909245");
		subId2.put("sub_id_typ", 1);

		ArrayList<Object> subIdArray = new ArrayList<Object>();
		subIdArray.add(subId1);
		subIdArray.add(subId2);

		ArrayList<Object> msccArray = new ArrayList<Object>();

		int[] svcIdArray = { 1 };
		int[] rpt_reasArray = { 2 };

		int count = 0;

		do {

			HashMap<String, Object> msccMap = new HashMap<String, Object>();
			HashMap<String, Object> usuMap = new HashMap<String, Object>();
			ArrayList<Object> usuArray = new ArrayList<Object>();

			msccMap.put("rg", usageInfoArray.get(count).get("rg"));
			msccMap.put("svc_id__arr", svcIdArray);

			usuMap.put("cc_in_octs", 0);
			usuMap.put("cc_out_octs", 0);
			usuMap.put("cc_tot_octs", usageInfoArray.get(count).get("cc_tot_octs"));
			usuArray.add(usuMap);
			msccMap.put("usu__arr", usuArray);

			msccMap.put("rpt_reas__arr", rpt_reasArray);

			msccArray.add(msccMap);
			count++;

		} while (count < usageInfoArray.size());

		HashMap<String, Object> userEquipMap = new HashMap<String, Object>();
		userEquipMap.put("usr_equip_info_typ", 0);
		userEquipMap.put("usr_equip_info_val__tr", "35452009055509012121");

		String[] sgsn_addr_Array = { "91.229.215.55" };
		String[] pdp_addr_Array = { "100.70.244.199" };

		HashMap<String, Object> psInfoMap = new HashMap<String, Object>();
		psInfoMap.put("3gpp_chrg_id", "2642912507");
		psInfoMap.put("3gpp_pdp_typ", 0);
		psInfoMap.put("3ggp_gprs_nego_qos_prof", "08-0408000f424000027100");
		psInfoMap.put("3gpp_imsi_mcc_mnc", "43220");
		psInfoMap.put("3gpp_ggsn_mcc_mnc", "43220");
		psInfoMap.put("3gpp_nsapi", "35");
		psInfoMap.put("3gpp_sel_mod", "0");
		psInfoMap.put("3gpp_chrg_charcs", "0800");
		psInfoMap.put("3gpp_sgsn_mcc_mnc", sgsn_mcc_mnc);
		psInfoMap.put("3gpp_rat_typ", "06");
		psInfoMap.put("3gpp_usr_loc_info__tr", "130;43220;71be1;432201;35db20c;");
		psInfoMap.put("3gpp_ms_tmz__tr", "GMT+3:31");
		psInfoMap.put("cg_addr", "10.200.34.14");
		psInfoMap.put("ggsn_addr__arr", sgsn_addr_Array);
		psInfoMap.put("chrg_rule_base_name", "up_test4");
		psInfoMap.put("pdp_addr__arr", pdp_addr_Array);
		psInfoMap.put("sgsn_addr__arr", sgsn_addr_Array);
		psInfoMap.put("pdp_ctx_typ", 0);

		HashMap<String, Object> svc_infoMap = new HashMap<String, Object>();
		svc_infoMap.put("ps_info", psInfoMap);

		HashMap<String, Object> body = new HashMap<String, Object>();
		body.put("evt_ts", evt_ts);
		body.put("sess_id", sess_id);
		body.put("cc_req_no", req_no);
		body.put("cc_req_typ", req_typ);
		body.put("sub_id__arr", subIdArray);
		body.put("msi", 1);
		body.put("mscc__arr", msccArray);
		body.put("usr_equip_info", userEquipMap);
		body.put("svc_info", svc_infoMap);

		requestParams.put("header", header);
		requestParams.put("body", body);

		//System.out.println(requestParams.toString());
		//logger.info("Request body: " + requestParams.toString());
		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);
		return response;
	}

	@SuppressWarnings("rawtypes")
	public Response dataServiceAutomatic(long post_exp, long evt_ts, String sess_id, int req_no, int req_typ,
			String sub_id_dat, ArrayList<HashMap> usageInfoArray, String sgsn_mcc_mnc) throws InterruptedException {

		Response response = null;
		JsonPath json;
		Map<Object, Object> responseBody;

		ArrayList<HashMap> initRgArray = new ArrayList<HashMap>();
		ArrayList<HashMap> updateRgArray = new ArrayList<HashMap>();
		ArrayList<HashMap> terminateRgArray = new ArrayList<HashMap>();
		ArrayList<HashMap> remainRgArray = new ArrayList<HashMap>();
		HashMap<String, Long> rgMap;
		HashMap<String, Long> remainRgMap;
		int times = 1;
		int rcValue;
		boolean runUpdate = true;
		long[] totalVolume;

		// Building array for running dataService method in initializing mode.
		for (int i = 0; i < usageInfoArray.size(); i++) {

			rgMap = new HashMap<String, Long>();
			rgMap.put("rg", (Long) usageInfoArray.get(i).get("rg"));
			rgMap.put("cc_tot_octs", 0L);
			initRgArray.add(rgMap);
		}

		// Calling dataService method in initializing mode
		logger.info("Running dataService in initializing mode");
		response = dataService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, initRgArray, sgsn_mcc_mnc);
		

		// Check if status of response of initializing data is successful or not
		if (response.getStatusCode() == 200) {

			// Get json body of response
			json = response.jsonPath();

			// get json of bodyMap of response and check rc value
			responseBody = json.getMap("body");
			rcValue = (int) responseBody.get("rc");

			// Check value of rc and if is true then prepare for building dataService in
			// update mode
			if (rcValue == 2001 ) {
				
				
				req_typ = 2;
				remainRgArray = usageInfoArray;
				int count = remainRgArray.size();
				totalVolume = new long[count];

				do {
					for (int i = 0; i < count; i++) {

						totalVolume[i] = (long) remainRgArray.get(i).get("cc_tot_octs");

						// check volume of rg is smaller than threshold
						if (totalVolume[i] > thresholdDataVolume) {

							rgMap = new HashMap<String, Long>();
							rgMap.put("rg", (Long) remainRgArray.get(i).get("rg"));
							rgMap.put("cc_tot_octs", (long) thresholdDataVolume);
							updateRgArray.add(rgMap);

							remainRgMap = new HashMap<String, Long>();
							remainRgMap.put("rg", (Long) remainRgArray.get(i).get("rg"));
							remainRgMap.put("cc_tot_octs",
									(long) remainRgArray.get(i).get("cc_tot_octs") - thresholdDataVolume);
							remainRgArray.remove(i);
							remainRgArray.add(i, remainRgMap);
						}
					}

					if (updateRgArray.size() > 0) {
						req_no++;
						logger.info("Running dataService in updating mode");
						response = dataService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, updateRgArray,
								sgsn_mcc_mnc);
						times++;
						count = updateRgArray.size();

						updateRgArray.clear();

						if (response.getStatusCode() == 200) {

							json = response.jsonPath();
							responseBody = json.getMap("body");
							rcValue = (int) responseBody.get("rc");
							if(rcValue!=2001) {
								break;
							}
						}

					} else {

						runUpdate = false;
						terminateRgArray = remainRgArray;
						req_no++;
					}

				} while (runUpdate);
				
			}
			
			if (terminateRgArray.size() > 0) {
				req_typ = 3;
				logger.info("Running dataService in terminating mode");
				response = dataService(post_exp, evt_ts, sess_id, req_no, req_typ, sub_id_dat, terminateRgArray,
						sgsn_mcc_mnc);
			}
		}
		return response;
	}
}
