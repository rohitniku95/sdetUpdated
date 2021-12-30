package com.crm.autodesk.genericUtility;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	/**
	 * 
	 * @author Rohit
	 *
	 */
	public class FileUTiltiy {
		/**
		 * its used to read the data from commonData.properties File based on Key which you pass as an argument
		 * @param key
		 * @throws Throwable 
		 */
	    public String getPropertyKeyValue(String key) throws Throwable {
	    	 FileInputStream fis = new FileInputStream("./data/ComData.properties");
	    	 Properties pobj = new Properties();
	    	 pobj.load(fis);
	    	 String value = pobj.getProperty(key);
			 return value;
	    }
	}



