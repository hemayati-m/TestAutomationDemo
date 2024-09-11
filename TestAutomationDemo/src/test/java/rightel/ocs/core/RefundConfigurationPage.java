package rightel.ocs.core;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import rightel.ocs.properties.allObjects;



public class RefundConfigurationPage extends OcsBasePage {
	
	@FindBy(id = allObjects.RefundConfig_key_id)
	private WebElement keyBox;
	
	@FindBy(id = allObjects.RefundConfig_value_id)
	private WebElement valueBox;
	
	@FindBy(id = allObjects.RefundConfig_cancel_id)
	private WebElement cancelBtn;
	
	@FindBy(id = allObjects.RefundConfig_save_id)
	private WebElement saveBtn;
	
	public void add(String key, int value) {
		
		Select keyList = new Select(keyBox);
		keyList.selectByVisibleText(key);
		
		valueBox.sendKeys(Integer.toString(value));
		
		saveBtn.click();
	}

}
