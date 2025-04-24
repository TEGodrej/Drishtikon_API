package Authentication;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CapturingCookie {
	
	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMOB_PCRM_DISTRIBUTOR_SRV";
	String token;
	public static Map<String, String> cookies;
	
	@Test
	public void captureCookie() throws IOException {
		Headers header=new Headers(new Header("Accpet","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		Response response = (Response) given()
                .auth()
				.oauth2(token)
				.headers(header)
				.baseUri(baseUrl)
				.when()
				.get(endPoint) ;
//				.then().log().all();
		cookies = response.getCookies();
		
		System.out.println(cookies);
				
	}

}
