package BalanceConfirmation;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import Authentication.SendingRequestToGenerateToken;
import GenericUtility.FileUtility;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class SendingPostRequestToSubmitBalanceConfirmation extends SendingRequestToGenerateToken{

	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/BalanceConfirmationSet";
	String bearerToken;
//	Map<String, String> cookies=CapturedCookies.cookies;
	String csrfToken;
	
	
	String balconfid="00020195491742372587860";
	String customer="0002019549";
	String amount="334643.05";
	String remarks="";
	String baldate="28/02/2025";
	String status="A";
	String companycode="6100";
	String subject="";
	String latitude="";
	String longitude="";
	String canConfirmBal="X";
	String createdon="2025-03-19T13:53:07";
	String canConfirmFrom="62";
	String changedon="2025-03-19T13:53:07";
	String canConfirmTo="9";
	String createdtime="PT13H53M07S";
	String changedtime="PT13H53M07S";
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void PostRequestToSubmitBalanceConfirmation() throws IOException {
		
		JSONObject job= new JSONObject();
		job.put("Balconfid", balconfid);
		job.put("Customer", customer);
		job.put("Amount", amount);
		job.put("Remarks", remarks);
		job.put("Baldate", baldate);
		job.put("Status", status);
		job.put("Companycode", companycode);
		job.put("Subject", subject);
		job.put("Latitude", latitude);
		job.put("Longitude", longitude);
		job.put("CanConfirmBal", canConfirmBal);
		job.put("Createdon", createdon);
		job.put("CanConfirmFrom", canConfirmFrom);
		job.put("Changedon", changedon);
		job.put("CanConfirmTo", canConfirmTo);
		job.put("Createdtime", createdtime);
		job.put("Changedtime", changedtime);
		
		csrfToken=FileUtility.getDataFromPropertyFile("X_CSRF_TOKEN");
		bearerToken=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		
		Headers header=new Headers(new Header("Cache-Control","no-Cache"),
//				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
		
//		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		
		Response response = given()
			    .headers(header)
			    .header("X-CSRF-Token", csrfToken)
//			    .cookies(cookies)
			    .contentType(ContentType.JSON)
			    .body(job.toString())
			    .auth().oauth2(bearerToken)
			    .baseUri(baseUrl)
			    .post(endPoint)
			    .then()
				.log().all()
				.extract().response();
		
		ValidatableResponse responseBody = response.then().log().body();
		System.out.println(responseBody);
	}
}
