package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rightel.ocs.properties.allObjects;

public class SystemParameterPage extends OcsBasePage {

	@FindBy(id = allObjects.SystemParam_key_id)
	private WebElement keyBox;
	@FindBy(id = allObjects.SystemParam_value_id)
	private WebElement valueBox;
	@FindBy(id = allObjects.SystemParam_defaultValue_id)
	private WebElement defaultValueBox;
	@FindBy(id = allObjects.SystemParam_status_id)
	private WebElement statusBox;
	@FindBy(id = allObjects.SystemParam_description_id)
	private WebElement descriptionBox;

	private WebDriver driver;

	public SystemParameterPage(WebDriver driver) {

		this.driver = driver;
	}

	public void fillFields(String key, String value, String defaultValue, String status, String desc) {

		keyBox.sendKeys(key);
		valueBox.sendKeys(value);
		defaultValueBox.sendKeys(defaultValue);

		selectByVisibleText(statusBox, status);
		descriptionBox.sendKeys(desc);
	}

	public DeleteMessagePage deleteItem(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 6, 1);
			return PageFactory.initElements(driver, DeleteMessagePage.class);
		}
		return null;
	}

	private boolean editRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 6, 0);
			return true;

		}

		return false;
	}

	public void changeStatus(String searchItem, String status) {

		if (editRow(searchItem)) {

			selectByVisibleText(statusBox, status);
		}
	}

}
