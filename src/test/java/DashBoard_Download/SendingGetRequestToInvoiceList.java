package DashBoard_Download;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import Authentication.CapturingCookie;
import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SendingGetRequestToInvoiceList {
	
	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMBO_PCRM_SRV/CustomerInvoiceListSet?$filter=CustomerNo eq '2013978' and FromDate eq datetime'2024-02-19T00:00:00' and ToDate eq datetime'2025-03-19T00:00:00'";
	String token;
	Map<String, String> cookies=CapturingCookie.cookies;
	
	@Test
	public void GetRequestToInvoiceList() throws IOException {
		Headers headers=new Headers(new Header("Cache-Control","no-Cache"),
//				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
		
		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		System.out.println("THIS IS TOKEN "+token);
		
		Response response = given()
			    .headers(headers)
			    .cookie("cookies")
			    .auth().oauth2(token)
			    .baseUri(baseUrl)
			    .get(endPoint); 

//			response.then().statusCode(200);
//			String respBody = response.then().extract().toString();
			
			String respBody = response.then().log().body().toString();

		
		Headers respheader = response.getHeaders();
		System.out.println(respheader);
		
		System.out.println(respBody);
	}

}
