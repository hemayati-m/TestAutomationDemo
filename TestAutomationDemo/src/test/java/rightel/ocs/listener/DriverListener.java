package rightel.ocs.listener;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverListener implements WebDriverListener {

	ApplicationLogger logger = new ApplicationLogger("Driver.log",true);

	@Override
	public void afterClick(WebElement element) {

		logger.info("After click on" + element);
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		
		logger.info("Navigate to: " + url);
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {

		logger.info("After send key to element: " + element);
	}

	@Override
	public void afterQuit(WebDriver driver) {

		logger.info("Driver was closed. ");

	}

	@Override
	public void afterGetTitle(WebDriver driver, String result) {

		logger.info("Page title is: " + result);

	}

	@Override
	public void afterGetCurrentUrl(WebDriver driver, String result) {

		logger.info("Current Url is: " + result);

	}

	public void afterFindElement(WebDriver driver, By locator, WebElement result) {

		logger.info("Element founded by: " + locator);
	}

	public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {

		logger.info("Elements founded by: " + locator);
	}

	public void afterClose(WebDriver driver) {

		logger.info("driver has closed.");
	}

	public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
		
		logger.info("driver has closed.");
		
	}

	public void afterGetWindowHandle(WebDriver driver, String result) {
		
		logger.info("Window handle to " + result);
	}

	public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
		
		logger.info("performe actions: " + actions);
	}

	public void afterGetTagName(WebElement element, String result) {
		
		logger.info("Element"  + element +  " has tag name: " + result);
	}

	public void afterGetAttribute(WebElement element, String name, String result) {
		
		logger.info("The attribute of element " + element + " is: " + name);
	}

	public void afterGetText(WebElement element, String result) {
		
		logger.info("The retun text of element  is: " + result);
	}

	public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
		
		logger.info("This elements founded: " + result);
	}

	public void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {
		
		logger.info("Navigate to :" + result);
	}

	public void afterImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
		
		logger.info("Implicitly wait set to: " + duration);
	}

	public void afterPageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
		
		logger.info("PageLoad time set to: " + duration);
	}

}