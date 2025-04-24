package ProofOfDelivery;

import org.testng.annotations.Test;

public class SendingPostRequestToSubmitProofOfDelivery {

	String baseUrl="https://gavl-mobility-application-i2pf2gjb-quality-com-btpcc-dev.cfapps.ap10.hana.ondemand.com/GAVL_NQT_BITEAM/sap/opu/odata/SAP/";
	String endPoint="ZMOB_PCRM_DISTRIBUTOR_SRV/ProofOfDlvSet";
	String bearerToken;
	String csrfToken;
	
	@Test
	public void test() {
		System.out.println("test");
	}
}
