package rightel.ocs.scripts;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import rightel.ocs.listener.ApplicationLogger;
import rightel.ocs.util.DbManager;
import rightel.ocs.util.ExcelReader;
import rightel.ocs.webservice.BasicWebServices;


public class SanityScripts {

	ApplicationLogger logger ;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\java\\rightel\\ocs\\util\\sanity.xlsx");
	DbManager db;
	BasicWebServices ws;

	@Test(priority = 70, enabled = true)
	public void OffnetCall() throws InterruptedException {

		String testcaseName = "SA - 0007";
		logger.info("Starting " + testcaseName);

		int row = excel.getTestCaseRow(testcaseName);

		Response response;
		JsonPath json;
		Map<Object, Object> responseBody;
		int rcValue;
		int req_no = 0;
		int req_typ = 1;
		int call_typ = 1;
		int cc_tm = 220;

		String sessionId, post_exp_date, sub_id, calli_pty_no, vlr_no, calld_pty_no, calld_pty_id;
		if (row > 0) {

			if (excel.getCellData("Sanity", row, excel.getCellId("Run Mode")).equalsIgnoreCase("N")) {

				throw new SkipException("Skipped becuase run mode is No");

			} else {
				logger.info("Run Mode " + testcaseName + " is Yes");
				sessionId = ws.newSessionId("Voice");
				post_exp_date = excel.getCellData("Sanity", row, excel.getCellId("post_exp_date"));
				sub_id = excel.getCellData("Sanity", row, excel.getCellId("sub_id"));
				calli_pty_no = excel.getCellData("Sanity", row, excel.getCellId("calli_pty_no"));
				vlr_no = excel.getCellData("Sanity", row, excel.getCellId("vlr_no"));
				calld_pty_no = excel.getCellData("Sanity", row, excel.getCellId("calld_pty_no"));
				calld_pty_id = excel.getCellData("Sanity", row, excel.getCellId("calld_pty_id"));

				response = ws.voiceServiceAutomatic(ws.getEpochInMillisecond(post_exp_date), ws.getEpochInSecond(), sessionId,
						req_no, req_typ, sub_id, cc_tm, calli_pty_no, vlr_no, calld_pty_no, calld_pty_id, call_typ);

				if (response.getStatusCode() == 200) {

					json = response.jsonPath();
					responseBody = json.getMap("body");
					rcValue = (int) responseBody.get("rc");

					if (rcValue == 2001) {

						Thread.sleep(10000);
						HashMap<String, Object> resultMap = db.get_VoiceCdr(sub_id, sessionId);
						int used_value = (int) resultMap.get("used_value");
						int pkId = (int) resultMap.get("fk_rating_event_price");
						int calc_amount = (int) resultMap.get("rating_calc_amount");

						HashMap<String, Object> ratingMap = db.get_RatingPrice(pkId);
						int used_unit = (int) ratingMap.get("used_unit_percision");
						double calc_value = (double) ratingMap.get("calc_value");
						logger.info(used_unit + "   " + calc_value + "    " + used_value);
						int ExpectedResult = (int) (used_unit * calc_value * used_value * 10000);
						logger.info("Actual Result: " + calc_amount + ",Expected Result" + ExpectedResult);
						logger.info(resultMap);
						logger.info(ratingMap);
						Assert.assertEquals(calc_amount, ExpectedResult);

					}
				}

			}
		}
	}

	@Test(priority = 50, enabled = true)
	public void IntlRoamingData() throws InterruptedException {

		String testcaseName = "SA - 0005";
		logger.info("Starting " + testcaseName);
		int row = excel.getTestCaseRow(testcaseName);

		Response response;
		JsonPath json;
		Map<Object, Object> responseBody;
		String post_exp_date, sub_id;
		String sessionId = ws.newSessionId("Data");
		int req_no = 0, req_typ = 1, rcValue = 0;

		if (row > 0) {

			if (excel.getCellData("Sanity", row, excel.getCellId("Run Mode")).equalsIgnoreCase("N")) {

				throw new SkipException("Skipped becuase run mode is No");

			} else {
				logger.info("Run Mode " + testcaseName + " is Yes");
				post_exp_date = excel.getCellData("Sanity", row, excel.getCellId("post_exp_date"));
				sub_id = excel.getCellData("Sanity", row, excel.getCellId("sub_id"));

				// Setting rg before calling webService
				ws.setRgList(70, 4000000);
				ws.setRgList(72, 2000000);

				response = ws.dataServiceAutomatic(ws.getEpochInMillisecond(post_exp_date), ws.getEpochInSecond(),
						sessionId, req_no, req_typ, sub_id, ws.getRgList(), "43220");
				if (response.getStatusCode() == 200) {

					json = response.jsonPath();
					responseBody = json.getMap("body");
					rcValue = (int) responseBody.get("rc");

					if (rcValue == 2001) {

						Thread.sleep(10000);

						ArrayList<HashMap> resultArray = db.get_DataCdr(sub_id, sessionId);
						HashMap<String, Object> ratingmap = new HashMap<String, Object>();
						int pkId;
						int actualResult;
						int used_value;
						int used_unit;
						double calc_value;

						for (int i = 0; i < resultArray.size(); i++) {

							used_value = (int) resultArray.get(i).get("rating_used_value_rounded");
							pkId = (int) resultArray.get(i).get("fk_rating_event_price");

							ratingmap = db.get_RatingPrice(pkId);
							used_unit = (int) ratingmap.get("used_unit_percision");
							calc_value = (double) ratingmap.get("calc_value");
							actualResult = (int) (used_value / used_unit * calc_value * 10000);
							logger.info("Expected Result: " + resultArray.get(i).get("rating_calc_amount")
									+ ",Actual Amout: " + actualResult);

						}

					}
				}
			}
		}
	}

	@Test(priority = 10, enabled = true)
	public void OffnetSMS() throws InterruptedException {

		String testcaseName = "SA - 0001";
		logger.info("Starting " + testcaseName);

		Response response;
		JsonPath json;
		Map<Object, Object> responseBody;

		int rc_ResponseBody;
		String post_exp_date, sub_id, src_addr, dest_addr, calli_vlr;

		int reqd_act = 0;
		int code_scheme = 0;

		int row = excel.getTestCaseRow(testcaseName);

		if (row > 0) {

			if (excel.getCellData("Sanity", row, excel.getCellId("Run Mode")).equalsIgnoreCase("N")) {

				throw new SkipException("Skipped becuase run mode is No");

			} else {

				logger.info("Run Mode " + testcaseName + " is Yes");
				String sessionId = ws.newSessionId("SMS");
				post_exp_date = excel.getCellData("Sanity", row, excel.getCellId("post_exp_date"));
				sub_id = excel.getCellData("Sanity", row, excel.getCellId("sub_id"));
				src_addr = excel.getCellData("Sanity", row, excel.getCellId("src_addr"));
				dest_addr = excel.getCellData("Sanity", row, excel.getCellId("dest_addr"));
				calli_vlr = excel.getCellData("Sanity", row, excel.getCellId("calli_vlr"));

				response = ws.smsService(ws.getEpochInMillisecond(post_exp_date), ws.getEpochInSecond(), sessionId, reqd_act,
						sub_id, calli_vlr, src_addr, dest_addr, code_scheme);
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				HashMap<String, Object> ratingMap = new HashMap<String, Object>();

				if (response.getStatusCode() == 200) {

					json = response.jsonPath();
					responseBody = json.getMap("body");
					rc_ResponseBody = (int) responseBody.get("rc");

					if (rc_ResponseBody == 2001) {
						Thread.sleep(10000);

						resultMap = db.get_SmsCdr(sub_id, sessionId);

						int used_value = (int) resultMap.get("rating_calc_amount");
						int pkId = (int) resultMap.get("fk_rating_event_price");

						ratingMap = db.get_RatingPrice(pkId);
						int used_unit = (int) ratingMap.get("used_unit_percision");
						double calc_value = (double) ratingMap.get("calc_value");

						logger.info(resultMap);
						logger.info(ratingMap);

						Assert.assertEquals(calc_value * 10000, used_value);
						Assert.assertTrue(ratingMap.get("price_name").toString().contains("Off_Net"));
					} else {

						json.prettyPrint();
					}

				} else {
					logger.info(response.getStatusCode());
					logger.info(response.getStatusLine());
				}
			}
		}
	}

	@BeforeSuite
	public void beforeSuite() {

		db = new DbManager();
		if (!db.setConncetion()) {

			logger.fail("Could not connect to database...");
			Assert.fail("Could not connect to database...");
		}
	}

	@AfterSuite
	public void afterSuit() {

		db.setDBClose();
	}

	@BeforeClass
	public void beforeClass() {
		try {
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\rightel\\ocs\\properties\\config.properties");
		config.load(fis);
		String logFileName = config.getProperty("testlogfile");
		logger = new ApplicationLogger(logFileName,true);
		
		} catch (Throwable t) {
			t.printStackTrace();
			
		}
		ws = new BasicWebServices();
	}
}
