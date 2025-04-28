package Authentication;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import GenericUtility.FileUtility;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
/**
 *@author DivyaPrakashAmar
 *This class is being use to send GET Request to DistributorDetail
 */
public class SendingGetRequestToDistributorDetail {
	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/DistributorDetailsSet?$filter=ImDistributor eq '2000408'";
	String token;
	Map<String, String> cookies=CapturingCookie.cookies;
	
	@Test
	public void GetDistributorDetail() throws IOException {
		Headers headers=new Headers(new Header("Cache-Control","no-Cache"),
				new Header("Accept","*/*"),
				new Header("Accept-Encoding","gzip,deflate,br"),
				new Header("Connection","keep-alive"),
				new Header("Accept","application/json"));
		
		token=FileUtility.getDataFromPropertyFile("bearer_TOKEN");
		
		
		Response response = given()
			    .headers(headers)
			    .cookie("cookies")
			    .auth().oauth2(token)
			    .baseUri(baseUrl)
			    .get(endPoint); 

			response.then().statusCode(200);
			String respBody = response.then().extract().toString();

		
		Headers respheader = response.getHeaders();
		System.out.println(respheader);
		
		System.out.println(respBody);
	}

}
