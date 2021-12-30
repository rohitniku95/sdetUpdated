package com.crm.autodesk.genericUtility;

import java.util.Date;
import java.util.Random;



/**
 * 
 * @author Rohit
 *
 */
public class JavaUtility {
	/**
	 * its used to generate the random number 
	 * @return int data
	 */

	public int getRandomNumber() {
		Random random = new Random();
		int intRandom = random.nextInt(1000);
        return intRandom;
       }

	/**
	 * used to get system date&time in ISt format
	 * @return 
	 */
	public String getSystemDateAndTime() {
		Date date = new Date();
		return date.toString();
		}

	/**
	 * used to get the system date in YYYY-MM-DD
	 * @return
	 */
    public String getSystemDateWithFormat() {
		Date date = new Date();
		String dateAndTime = date.toString();
		
    	String YYYY = dateAndTime.split(" ")[5];
    	String DD = dateAndTime.split(" ")[2];
    	int MM = date.getMonth()+1;
    	
    	String finalFormat = YYYY +"-"+MM+"-"+DD;
 
    	return finalFormat;
    	
    }
   }

