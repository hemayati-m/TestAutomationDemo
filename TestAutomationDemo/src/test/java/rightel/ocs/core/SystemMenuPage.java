package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static rightel.ocs.properties.allObjects.*;


public class SystemMenuPage extends OcsBasePage  {

	@FindBy(id = Systemmenu_description_id) 		private WebElement descriptionBox;
	@FindBy(id = Systemmenu_icon_id)				private WebElement iconBox;
	@FindBy(id = Systemmenu_isActive_id)			private WebElement activeChkBox;
	@FindBy(id = Systemmenu_name_id)				private WebElement nameBox;
	@FindBy(id = Systemmenu_parent_id)				private WebElement parentBox;	
	@FindBy(id = Systemmenu_selector_id)			private WebElement selectorBox;
	@FindBy(id = Systemmenu_state2_id)				private WebElement state2Box;
	@FindBy(id = Systemmenu_state_id)				private WebElement stateBox;
	@FindBy(id = Systemmenu_type_id)				private WebElement typeBox;

	
	private WebDriver driver;
	

	public SystemMenuPage(WebDriver driver) {

		this.driver = driver;
	}
	
	
	public void add(String name, String state, String state2, String type, String icon, String selector, String desc,
			String parent, boolean isActive) {

		nameBox.sendKeys(name);
		stateBox.sendKeys(state);
		state2Box.sendKeys(state2);
		typeBox.sendKeys(type);
		iconBox.sendKeys(icon);
		selectorBox.sendKeys(selector);
		descriptionBox.sendKeys(desc);

		Select parentList = new Select(parentBox);
		parentList.selectByVisibleText(parent);

		if (isActive) {

			activateCheckbox();

		} else {

			deactivateCheckbox();

		}

	}

	private void activateCheckbox() {

		if (!activeChkBox.isSelected()) {

			activeChkBox.click();

		}
	}

	private void deactivateCheckbox() {

		if (activeChkBox.isSelected()) {

			activeChkBox.click();

		}
	}


	private boolean editRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 10, 0);

			return true;

		}

		return false;
	}

	public void activeMenu(String searchItem) {

		if (editRow(searchItem)) {

			activateCheckbox();

		}

	}

	public DeleteMessagePage deleteRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 10, 1);
			return PageFactory.initElements(driver, DeleteMessagePage.class);

		}

		return null;
	}

}
