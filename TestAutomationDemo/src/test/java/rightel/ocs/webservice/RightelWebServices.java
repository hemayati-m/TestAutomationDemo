package rightel.ocs.webservice;

import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rightel.ocs.properties.allObjects;

public class RightelWebServices {

	public static void main(String[] args) {

		Response response = queryPostPaidBilNEW("989210002900");
		if (response.getStatusCode() == 200) {
			// System.out.println(json.getString("OUTSTANDING"));
			System.out.println("Response body: " + response.body().asString());

		} else {
			System.out.println("Status code: " + response.getStatusCode());
			System.out.println("Response body: " + response.getStatusLine());

		}

	}

	public static Response queryPostPaidBilNEW(String msisdn) {

		String url = allObjects.baseURI + allObjects.queryPostPaidBilNEW;

		// Set the base URI for your API
		RestAssured.baseURI = url;

		// Create a request specification
		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).header("REQUEST_ID", "123456")
				.header("username", "OCS_TEST").header("password", "ocs_test_2022")
				.header("ACTION_ID", "QueryPostpaidBillNew");

		// Create a JSON payLoad
		JSONObject requestParams = new JSONObject();
		requestParams.put("MSISDN", msisdn);

		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);

		return response;
	}

	/*
	 * // Get the status code and response body int statusCode =
	 * response.getStatusCode(); JsonPath json = response.jsonPath();
	 * 
	 * 
	 * if (statusCode == 200) {
	 * 
	 * //System.out.println(json.getString("OUTSTANDING"));
	 * System.out.println("Response body: " + response.body().asString());
	 * 
	 * } else { System.out.println("Status code: " + statusCode);
	 * System.out.println("Response body: " + response.getStatusLine());
	 * 
	 * }
	 * 
	 * }
	 */
	public static void offlineRecharge(String transferId, String transferDateTime, String senderMsisdn,
			String receiverMsisdn, String transferStatus, String errorCode, String transferValue,
			String voucherSerialNumber, String requestGateway) {

		String url = allObjects.baseURI + allObjects.offlineRecharge;
		System.out.println(url);

		// Set the base URI for your API
		RestAssured.baseURI = url;

		// Create a request specification
		RequestSpecification request = RestAssured.given();

		// Create header
		request.header("Content-Type", allObjects.ContentType);
		request.header("REQUESTID", allObjects.RequestID);
		request.header("username", allObjects.Username);
		request.header("password", allObjects.Password);
		// request.header("ACTION_ID", "packageInquiry");

		// Create a JSON payLoad
		JSONObject requestParams = new JSONObject();
		requestParams.put("transferId", "123456");
		requestParams.put("transferDateTime", "1401-11-25 22:00:00");
		requestParams.put("senderMsisdn", "989210002900");
		requestParams.put("receiverMsisdn", "9210002901");
		requestParams.put("transferStatus", "200");
		requestParams.put("errorCode", "1016108");
		requestParams.put("transferValue", "12000");
		requestParams.put("voucherSerialNumber", "12760093658568584");
		requestParams.put("requestGateway", "NORUSSD");

		System.out.println(requestParams.toString());

		// Set the request body
		request.body(requestParams);

		// Send the POST request
		Response response = request.post(url);

		// Get the status code and response body
		int statusCode = response.getStatusCode();
		JsonPath json = response.jsonPath();

		if (statusCode == 200) {

			System.out.println(json.getString("responseCode"));
			System.out.println(json.getString("responseDesc"));
			System.out.println("Response body: " + response.body().asString());

		} else {
			System.out.println("Status code: " + statusCode);
			System.out.println("Response body: " + response.getStatusLine());

		}
	}

	public static void packageInquiry(String msisdn, int balanceCode) {

		String url = allObjects.baseURI + allObjects.packageInquiry;
		System.out.println(url);

		// Set the base URI for your API
		RestAssured.baseURI = url;

		// Create a request specification
		RequestSpecification request = RestAssured.given();

		// Create header
		request.header("Content-Type", allObjects.ContentType);
		request.header("REQUEST_ID", allObjects.RequestID);
		request.header("username", allObjects.Username);
		request.header("password", allObjects.Password);
		request.header("ACTION_ID", "packageInquiry");

		// Create a JSON payLoad
		JSONObject requestParams = new JSONObject();
		requestParams.put("msisdn", msisdn);
		requestParams.put("balanceCode", balanceCode);

		System.out.println(requestParams.toString());

		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);

		// Get the status code and response body
		int statusCode = response.getStatusCode();
		JsonPath json = response.jsonPath();

		if (statusCode == 200) {

			System.out.println(json.getString("inquiryResponse"));
			System.out.println("Response body: " + response.body().asString());

		} else {
			System.out.println("Status code: " + statusCode);
			System.out.println("Response body: " + response.getStatusLine());

		}
	}

	public static void barOneWay(Object subscriberNumber, String createdBy, int reason, String createdAt) {

		String url = allObjects.baseURI + allObjects.barOneWay;
		System.out.println(url);

		// Set the base URI for your API
		RestAssured.baseURI = url;

		// Create a request specification
		RequestSpecification request = RestAssured.given();

		// Create header
		request.header("Content-Type", allObjects.ContentType);
		request.header("REQUESTID", allObjects.RequestID);
		request.header("username", allObjects.Username);
		request.header("password", allObjects.Password);
		request.header("ACTION_ID", "barOneWay");

		// Create a JSON payLoad
		JSONObject requestParams = new JSONObject();
		requestParams.put("subscriberNumber", subscriberNumber);
		requestParams.put("createdBy", createdBy);
		requestParams.put("reason", reason);
		requestParams.put("createdAt", createdAt);

		System.out.println(requestParams.toString());

		// Set the request body
		request.body(requestParams.toString());

		// Send the POST request
		Response response = request.post(url);

		// Get the status code and response body
		int statusCode = response.getStatusCode();
		JsonPath json = response.jsonPath();

		if (statusCode == 200) {

			System.out.println(json.getString("responseCode"));
			System.out.println(json.getString("responseDesc"));
			System.out.println("Response body: " + response.body().asString());

		} else {
			System.out.println("Status code: " + statusCode);
			System.out.println("Response body: " + response.getStatusLine());

		}

	}

}
