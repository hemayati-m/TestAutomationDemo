package rightel.ocs.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tosan.tools.jalali.JalaliCalendar;
import com.tosan.tools.jalali.JalaliDate;

public class OcsDate {
	
	public static String miladiDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd  HH:mm:ss");
		String output = formatter.format( LocalDateTime.now() );  // 2024-09-24  14:09:23
		
		return output;		
	}
	
	public static String jaladiDate() {
		
		JalaliCalendar j = new JalaliCalendar();		
		JalaliDate jdate = j.getJalaliDate();
		String output = jdate.toString(); //1403/06/03 14:09:23
		
		return output;
	}
	
	public static String generateTimeStamp(boolean Miladi) {
		
		if(Miladi) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd  HH:mm:ss");
			
			return formatter.format( LocalDateTime.now()).toString().replace("  ", "_").replace(":", "_").replace("-", "_");
		} else {
			JalaliCalendar j = new JalaliCalendar();		
			JalaliDate jdate = j.getJalaliDate();
			
			return jdate.toString().replace(" ", "_").replace(":", "_").replace("/", "_");
			
		}
		
	}

}
