package BalanceConfirmation;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import Authentication.CapturingCookie;
import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SendingGetRequestToXCsrfToken {

	String csrfbaseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String csrfendPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/";
	String token;
	 Map<String, String> cookies=CapturingCookie.cookies;
     String  X_CSRF_TOKEN;
	
	@Test
	public void GetRequestToXCsrfToken() throws IOException {
		Headers headers=new Headers(new Header("Cache-Control","no-Cache"),
//				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
		
		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		
		Response response = given()
			    .headers(headers)
			    .header("X-CSRF-Token", "Fetch")
			    .cookie("cookies")
			    .auth().oauth2(token)
			    .baseUri(csrfbaseUrl)
			    .get(csrfbaseUrl);
		
		X_CSRF_TOKEN = response.getHeader("x-csrf-token");
		cookies=response.getCookies();
		
		
		System.out.println("The captured X_CSRF_Token is : "+ X_CSRF_TOKEN);
		
		// Save both tokens
        saveTokensToPropertyFile( X_CSRF_TOKEN);
	}
	
	  public void saveTokensToPropertyFile( String csrfToken) throws IOException {
	        Properties properties = new Properties();
//	        if (sapToken != null) properties.setProperty("SAP_ACCESS_TOKEN", sapToken);
	        if (csrfToken != null) properties.setProperty("X_CSRF_TOKEN", csrfToken);

	        try (FileOutputStream out = new FileOutputStream("C:\\Users\\testing.engineer\\eclipse-workspace\\DirshtikonSAP_RestAPI\\Token\\token.property")) {
	            properties.store(out, "Stored SAP and CSRF tokens");
	            System.out.println("Tokens saved to tokens.properties");
	        }
	    }
	
}
