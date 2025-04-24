package Authentication;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SendingRequestToGenerateToken {
	
	String baseUrl="https://gavl-mobility-application-i2pf2gjb.authentication.ap10.hana.ondemand.com";
	String endPoint="/oauth/token";
	String basicUserName="sb-nsBTPCCQA!t42302";
	String basicPassWord="641a2612-29b9-499b-b3a7-81264e188d0c$MKvbpsBptxB9elFyC21CipXUOz88G4l_E7TuKb_rWvk=";
	String grantType="password";
	String Username="P2008910612";
	String password="btpcc@Gavlit01";
	String csrfbaseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String csrfendPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/";
//	public static Map<String, String> cookies;
	public static String  X_CSRF_TOKEN;
	public static String bearerToken;

	
	
	
	@BeforeMethod
	@Test
	public void captureBearerToken() throws IOException {
		Response response = (Response) given()
				.auth()
				.basic(basicUserName, basicPassWord)
				.formParam("username", Username)
				.formParam("password", password)
				.formParam("grant_type", grantType)
				.baseUri(baseUrl)
				.post(endPoint)
				.then()
				.extract().response();
		//
		response.then().log().all();
		
		response.then().statusCode(200);
		
		bearerToken = response.jsonPath().getString("access_token");
		System.out.println("captured bearer Token :"+ bearerToken);
		
//		saveTokensToPropertyFile(bearerToken);
	}
	
	@Test
	public void GetRequestToXCsrfToken() throws IOException {
		
		Assert.assertNotNull("bearer Access Token is null before fetching CSRF token", bearerToken);
		Headers headers=new Headers(new Header("Cache-Control","no-Cache"),
//				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
//		
//		bearerToken=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		
		Response response = given()
				.auth().oauth2(bearerToken)
			    .headers(headers)
			    .header("X-CSRF-Token", "Fetch")
			    .cookie("cookies")
			    .baseUri(csrfbaseUrl)
			    .get(csrfbaseUrl)
			    .then()
			    .log().all()
				.extract().response();
		
		X_CSRF_TOKEN = response.getHeader("x-csrf-token");
		
//		cookies=response.getCookies();
//        CapturedCookies.cookies=cookies;
		
		
		System.out.println("The captured X_CSRF_Token is : "+ X_CSRF_TOKEN);
//		System.out.println(cookies);
		
		// Save both tokens
        saveTokensToPropertyFile( bearerToken,X_CSRF_TOKEN);
        
	}
//	
	public void saveTokensToPropertyFile(String bearerToken, String X_CSRF_TOKEN) throws IOException {
        Properties properties = new Properties();
        if (bearerToken != null) properties.setProperty("bearer_TOKEN", bearerToken);
        if (X_CSRF_TOKEN != null) properties.setProperty("X_CSRF_TOKEN", X_CSRF_TOKEN);

        try (FileOutputStream out = new FileOutputStream("C:\\Users\\testing.engineer\\eclipse-workspace\\DirshtikonSAP_RestAPI\\Token\\token.property")) {
            properties.store(out, "Stored bearer tokens");
            System.out.println("Tokens saved to tokens.properties");
        }
    }
}
