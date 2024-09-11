package rightel.ocs.core;

import static rightel.ocs.core.OcsCoreClass.*;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class OcsDatePicker {

	public static int targetDay = 0, targetMonth = 0, targetYear = 0, targetHour = 0, targetMinute = 0,	targetSecond = 0;
	public static int currentDay = 0, currentMonth = 0, currentYear = 0;
	private static String showMonths, datePickerYear, datePickernavDec, datePickernavInc;
	private static List<WebElement> datePickerDays;

	static boolean increment = true;
	static int jumpMonthsBy = 0;
	static int jumpYearsBy = 0;


	//private static String responseMessage = "0";

	// Check presence of webElement
	public static boolean isElementPresent(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Check presence of webElement
	public static boolean isElementPresent(By by, int waitingTime) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitingTime));
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void getCurrentDay() {

		Calendar cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		currentYear = cal.get(Calendar.YEAR);
	}

	private static void goto_DateAndTime(String webElementId) {

		// Initial static variable
		datePickerYear = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[1]/button";

		datePickernavDec = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[1]/button[2]";

		datePickernavInc = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[2]/button[1]";

		showMonths = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/div/dp-calendar-nav/div/div[1]/button";

		getCurrentDay();

		// click on show months webElement
		driver.findElement(By.xpath(showMonths)).click();

		// Set year
		setTargetYear(currentYear);

		// Finding WebElement of current month
		String monthWebElement = "//button[text()='" + getMonthName(0) + "']";
		driver.findElement(By.xpath(monthWebElement)).click();

		// Find Days in DatePicker and assign to variable
		datePickerDays = driver
				.findElements(By.xpath("//*[@id='" + webElementId + "']//button[contains(@class,'dp-current-month')]"));

		// Set day
		setTargetDay(currentDay);
	}

	// Separate date from "/" and put them in correct variables
	private static void separateDate(String newDate) {
		if (newDate.length() == 19) {

			try {
				targetYear = Integer.parseInt(newDate.substring(0, newDate.indexOf("/")));
				targetMonth = Integer.parseInt(newDate.substring(newDate.indexOf("/") + 1, newDate.lastIndexOf("/")));
				targetDay = Integer.parseInt(newDate.substring(newDate.lastIndexOf("/") + 1, 10));

				targetHour = Integer.parseInt(newDate.substring(newDate.indexOf(" ") + 1, newDate.indexOf(":")));
				targetMinute = Integer.parseInt(newDate.substring(newDate.indexOf(":") + 1, newDate.lastIndexOf(":")));
				targetSecond = Integer.parseInt(newDate.substring(newDate.lastIndexOf(":") + 1, newDate.length()));


			} catch (Exception e) {

			}

		} else if (newDate.length() == 10) {

			try {
				targetYear = Integer.parseInt(newDate.substring(0, newDate.indexOf("/")));
				targetMonth = Integer.parseInt(newDate.substring(newDate.indexOf("/") + 1, newDate.lastIndexOf("/")));
				targetDay = Integer.parseInt(newDate.substring(newDate.lastIndexOf("/") + 1, 10));


			} catch (Exception e) {

			}
		}
	}

	// OverLoading
	private static void goto_DateAndTime(String webElementId, String newDate) {

		// Initial static variable
		datePickerYear = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[1]/button";
		
		datePickernavDec = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[1]/button[2]";
		
		datePickernavInc = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[2]/button[1]";
		
		showMonths = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/div/dp-calendar-nav/div/div[1]/button";

		// Separate date into details
		separateDate(newDate);

		// click on show months webelement
		driver.findElement(By.xpath(showMonths)).click();

		// Set year
		setTargetYear(targetYear);

		// Finding WebElement of current month
		String monthWebElement = "//button[text()='" + getMonthName(targetMonth) + "']";
		driver.findElement(By.xpath(monthWebElement)).click();

		// Initial current days in datepicker and assign to variable
		datePickerDays = driver
				.findElements(By.xpath("//*[@id='" + webElementId + "']//button[contains(@class,'dp-current-month')]"));

		// Set day
		setTargetDay(targetDay);

		setTargetTime(webElementId);
	}
	
	// OverLoading
	private static void goto_Date(String webElementId, String newDate) {

		// Initial static variable
		datePickerYear = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[1]/button";
		
		datePickernavDec = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[1]/button[2]";
		
		datePickernavInc = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/dp-month-calendar/div/dp-calendar-nav/div/div[2]/div[2]/button[1]";
		
		showMonths = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-day-calendar/div/dp-calendar-nav/div/div[1]/button";

		// Separate date into details
		separateDate(newDate);

		// click on show months webelement
		driver.findElement(By.xpath(showMonths)).click();

		// Set year
		setTargetYear(targetYear);

		// Finding WebElement of current month
		String monthWebElement = "//button[text()='" + getMonthName(targetMonth) + "']";
		driver.findElement(By.xpath(monthWebElement)).click();

		// Initial current days in datepicker and assign to variable
		datePickerDays = driver
				.findElements(By.xpath("//*[@id='" + webElementId + "']//button[contains(@class,'dp-current-month')]"));

		// Set day
		setTargetDay(targetDay);

		//setTargetTime(webElementId);
	}
	
	public static void setDateTime(String webElementId, String newDate) {
		if(!newDate.equals("")) {
			
			if(newDate.length()==10) {
				
				goto_Date(webElementId,newDate);
				
			} else if(newDate.length()==19) {
				
				goto_DateAndTime(webElementId,newDate);
				
			}
		}
		
	}

	
	//Change value of time elements with new time value
	private static void setTargetTime(String webElementId) {

		int datePickerHour = 0, datePickerMinute = 0, datePickerSecond = 0;

		//Finding WebElement of relevant time objects in DatePicker
		String hourUp = "//*[@id='" + webElementId
				+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[1]/button[1]",
				hourDown = "//*[@id='" + webElementId
						+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[1]/button[2]",
				minuteUp = "//*[@id='" + webElementId
						+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[3]/button[1]",
				minuteDown = "//*[@id='" + webElementId
						+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[3]/button[2]",
				secondUp = "//*[@id='" + webElementId
						+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[5]/button[1]",
				secondDown = "//*[@id='" + webElementId
						+ "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[5]/button[2]";

		
		// Put value of times in to variables
		datePickerSecond = Integer.parseInt(driver
				.findElement(By.xpath(
						"//*[@id='" + webElementId + "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[5]/span"))
				.getText());
		datePickerMinute = Integer.parseInt(driver
				.findElement(By.xpath(
						"//*[@id='" + webElementId + "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[3]/span"))
				.getText());
		datePickerHour = Integer.parseInt(driver
				.findElement(By.xpath(
						"//*[@id='" + webElementId + "']/div[2]/div/dp-day-time-calendar/dp-time-select/ul/li[1]/span"))
				.getText());

		// set second
		if (targetSecond <= 60 && targetSecond >= 0) {
			if (datePickerSecond > targetSecond) {

				for (int i = 0; i < datePickerSecond - targetSecond; i++) {

					driver.findElement(By.xpath(secondDown)).click();
				}
			} else {

				for (int i = 0; i < targetSecond - datePickerSecond; i++) {

					driver.findElement(By.xpath(secondUp)).click();
				}

			}
		}

		// set minute
		if (targetMinute <= 60 && targetMinute >= 0) {
			if (datePickerMinute > targetMinute) {

				for (int i = 0; i < datePickerMinute - targetMinute; i++) {

					driver.findElement(By.xpath(minuteDown)).click();
				}
			} else {

				for (int i = 0; i < targetMinute - datePickerMinute; i++) {

					driver.findElement(By.xpath(minuteUp)).click();
				}

			}
		}

		// set hour
		if (targetHour < 24 && targetHour >= 0) {
			if (datePickerHour > targetHour) {

				for (int i = 0; i < datePickerHour - targetHour; i++) {

					driver.findElement(By.xpath(hourDown)).click();
				}
			} else {

				for (int i = 0; i < targetHour - datePickerHour; i++) {

					driver.findElement(By.xpath(hourUp)).click();
				}

			}
		}

	}

	
	//Find desire day and click on it
	private static boolean setTargetDay(int targetDay) {

		if (datePickerDays.size() > 0) {

			for (int i = 0; i < datePickerDays.size(); i++) {
				int day = Integer.parseInt(datePickerDays.get(i).getText());

				if (targetDay == day) {
					datePickerDays.get(i).click();
					return true;

				}

			}

		}
		return false;

	}

	
	//Find desire year and click on it
	private static boolean setTargetYear(int year) {

		// Find the year value of DatePicker
		int yearOfDatePicker = Integer.parseInt(driver.findElement(By.xpath(datePickerYear)).getText());

		try {
			if (yearOfDatePicker > year) {

				jumpYearsBy = yearOfDatePicker - year;
				increment = false;
				for (int i = 0; i < jumpYearsBy; i++) {

					driver.findElement(By.xpath(datePickernavDec)).click();

				}

			} else {

				jumpYearsBy = year - yearOfDatePicker;

				for (int i = 0; i < jumpYearsBy; i++) {

					driver.findElement(By.xpath(datePickernavInc)).click();

				}

			}
		} catch (Exception e) {

			return false;
		}

		return true;

	}

	// get a month number and return it's name 
	private static String getMonthName(int monthNumber) {

		if (monthNumber > 0 && monthNumber < 13) {

			switch (monthNumber) {
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			}
		} else {

			getCurrentDay();
			monthNumber = currentMonth;

			switch (monthNumber) {
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			}

		}
		return "January";

	}
}
