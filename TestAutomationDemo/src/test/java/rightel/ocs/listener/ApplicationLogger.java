package rightel.ocs.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import rightel.ocs.core.OcsDate;


public class ApplicationLogger {
	

	private static File applicationLogFile;
	private static FileWriter writer ;
	private static BufferedWriter seleniumBuffer;
	private  boolean persianLogDate = false;

	public  ApplicationLogger(String logFileName, boolean removePriorFile) {

		try {

			// Create responsive log file
			applicationLogFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\rightel\\ocs\\logs\\" +   logFileName);
			if (removePriorFile) {
				applicationLogFile.delete();
			}
			

		} catch (Exception io) {

			io.printStackTrace();
		}

	}
	
	private void generateLog(String type, Object obj) {

		try {
			writer = new FileWriter(applicationLogFile,true);
			seleniumBuffer = new BufferedWriter(writer);
			if(persianLogDate) {
				
				seleniumBuffer.write(OcsDate.jaladiDate() + " " + type + " " + obj.toString() + "\n");
				seleniumBuffer.close();
				
			} else {
				
				seleniumBuffer.write(OcsDate.miladiDate() + " " + type + " " + obj.toString() + "\n");
				seleniumBuffer.close();				
				
			}


		} catch (IOException io) {
			io.printStackTrace();

		} 

	}

	public void info(Object obj) {

		generateLog("INFO",obj);
	}
	
	public  void error(Object obj) {

		generateLog("ERROR",obj);

	}
	
	public void warning(Object obj) {

		generateLog("WARNING",obj);		

	}
	
	
	public void pass(Object obj) {

		generateLog("PASSED",obj);
	}
	
	public void fail(Object obj) {

		generateLog("FAILED",obj);

	}
	
	public void skip(Object obj) {

		generateLog("SKIPPED",obj);
	}

}
