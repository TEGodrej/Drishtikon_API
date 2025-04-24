package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public static String getDataFromPropertyFile(String key) throws IOException {

	FileInputStream fis= new FileInputStream("C:\\Users\\testing.engineer\\eclipse-workspace\\DirshtikonSAP_RestAPI\\Token\\token.property");
	Properties prop = new Properties();
	prop.load(fis);
	
	String value = prop.getProperty(key);
	
	return value;
 }
}