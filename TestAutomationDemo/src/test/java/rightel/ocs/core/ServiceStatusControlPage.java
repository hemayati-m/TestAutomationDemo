package rightel.ocs.core;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ServiceStatusControlPage extends OcsBasePage {

	private List<WebElement> controlsList;
	private WebDriver driver;
	private By byControlList = By.xpath("//div[@class='flex-container']");

	public ServiceStatusControlPage(WebDriver driver) {
		
		this.driver = driver;
		this.controlsList = driver.findElements(byControlList);
	}

	public void printServiceControlandStatus() {

		if (controlsList.size() > 0) {

			for (int i = 0; i < controlsList.size(); i++) {

				System.out.println("The Value is : " + controlsList.get(i).findElement(By.tagName("label")).getText()
						+ "----" + controlsList.get(i).findElement(By.tagName("input")).isSelected());
			}
		}
	}

	public void activeControl(String serviceName) {

		if (controlsList.size() > 0 && !getServiceStatus(serviceName)) {

			for (int i = 0; i < controlsList.size(); i++) {

				if (serviceName
						.equalsIgnoreCase(controlsList.get(i).findElement(By.tagName("label")).getText())) {

						controlsList.get(i).findElement(By.tagName("input")).click();
						break;
				}
			}

		}

		clickOn_Action();
	}
	
	public void deactiveControl(String serviceName) {

		if (controlsList.size() > 0 && getServiceStatus(serviceName)) {

			for (int i = 0; i < controlsList.size(); i++) {

				if (serviceName
						.equalsIgnoreCase(controlsList.get(i).findElement(By.tagName("label")).getText())) {

						controlsList.get(i).findElement(By.tagName("input")).click();
						break;
				}
			}

		}

		clickOn_Action();
	}

	private void clickOn_Action() {

		driver.findElement(By.id("view-entity")).submit();
		//setResponseMessage();

	}

	
	// Return true or false when serviceControl selected or not 
	private boolean getServiceStatus(String serviceName) {
		
		if (controlsList.size() > 0) {
			
			for (int i = 0; i < controlsList.size(); i++) {
				
				if (serviceName
						.equalsIgnoreCase(controlsList.get(i).findElement(By.tagName("label")).getText())) {
					
					return (controlsList.get(i).findElement(By.tagName("input")).isSelected());
				}
				
			}
			
		}

		return false;
	}

}
