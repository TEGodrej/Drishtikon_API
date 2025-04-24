package Authentication;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SendingGetLoginCheck_BlockStatus extends SendingRequestToGenerateToken{
	
	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="/LogincheckSet('2002154')";
	String token;
	Map<String, String> cookies=CapturingCookie.cookies;

	
	@Test
	public void LoginCheck_BlockStatus() throws IOException {
		
		Headers headers=new Headers(new Header("Cache-Control","no-Cache"),
				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"),
				new Header("x-csrf-token","fetch"));
		
		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
//		Response response = (Response) given()
//				.headers(headers)
//				.cookie("cookies")
//				.auth().oauth2(token)
//				.baseUri(baseUrl)
//				.get(endPoint);
		
		Response response = given()
			    .headers(headers)
			    .cookie("cookies")
			    .auth().oauth2(token)
			    .baseUri(baseUrl)
			    .get(endPoint); 

//		ValidatableResponse statusCode = response.then().statusCode(200);
//		System.out.println(statusCode);
		
		Headers respheader = response.getHeaders();
		System.out.println(respheader);
		
		String body=response.getBody().toString();
		System.out.println(body);
		
	
		
		
		
	}
}
