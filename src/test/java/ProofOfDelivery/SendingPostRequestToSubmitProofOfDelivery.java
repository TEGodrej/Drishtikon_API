package ProofOfDelivery;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import GenericUtility.FileUtility;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class SendingPostRequestToSubmitProofOfDelivery {

	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/ProofOfDlvSet";
	String bearerToken;
	String csrfToken;
//	Map<String, String> cookies=CapturedCookies.cookies;
	@Test
	public void test() throws IOException {
		File jsonFile = new File("C:\\Users\\testing.engineer\\eclipse-workspace\\DirshtikonSAP_RestAPI\\src\\main\\resources\\TestData.json");
		
		
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
			    .body(jsonFile)
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
