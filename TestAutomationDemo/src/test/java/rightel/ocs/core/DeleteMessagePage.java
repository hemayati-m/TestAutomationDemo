package rightel.ocs.core;

import static rightel.ocs.properties.allObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteMessagePage {

	@FindBy(xpath = Del_delete_xpath)
	private WebElement deleteBtn;

	@FindBy(xpath = Del_cancel_xpath)
	private WebElement cancelBtn;

	private WebDriver driver;
	
	public DeleteMessagePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void approve() {

		deleteBtn.click();
	}

	public void cancel() {

		cancelBtn.click();
	}

}
