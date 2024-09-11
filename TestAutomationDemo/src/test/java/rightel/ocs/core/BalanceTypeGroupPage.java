package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static rightel.ocs.core.OcsDatePicker.*;
import static rightel.ocs.properties.allObjects.*;

public class BalanceTypeGroupPage extends OcsBasePage {

	@FindBy(id = BtGroup_groupName_id)
	private WebElement groupNameBox;
	@FindBy(id = BtGroup_groupCode_id)
	private WebElement groupCodeBox;
	@FindBy(id = BtGroup_groupStatus_id)
	private WebElement groupStatusBox;
	@FindBy(id = BtGroup_usagePriority_id)
	private WebElement usagePriorityBox;
	@FindBy(id = BtGroup_description_id)
	private WebElement descriptionBox;
	@FindBy(id = BtGroup_parent_id)
	private WebElement parentBox;
	@FindBy(id = BtGroup_effectiveTime_id)
	private WebElement effectiveTimeBox;
	@FindBy(id = BtGroup_expirationTime_id)
	private WebElement expirationTimeBox;

	private WebDriver driver;

	public BalanceTypeGroupPage(WebDriver driver) {

		this.driver = driver;
	}

	private boolean editRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 9, 0);
			return true;

		}
		return false;
	}

	public void changeParent(String searchItem, String Perent) {

		if (editRow(searchItem)) {

			if (!Perent.equals("")) {

				Select parentList = new Select(parentBox);
				parentList.selectByVisibleText(Perent);

			}
		}
	}

	public void changeEffectiveTime(String searchItem, String effectiveTime) {

		if (editRow(searchItem)) {

			expirationTimeBox.click();
			setDateTime(BtGroup_effectiveTime_id, effectiveTime);

		}

	}

	public void changeExpireTime(String searchItem, String expireTime) {

		if (editRow(searchItem)) {

			expirationTimeBox.click();
			setDateTime(BtGroup_expirationTime_id, expireTime);

		}

	}

	public void changeStatus(String searchItem, String status) {

		if (editRow(searchItem)) {

			Select statusList = new Select(groupStatusBox);
			statusList.selectByVisibleText(status);

		}
	}

	public void add(String name, String code, String status, int priority, String Descrption, String parent,
			String effectiveTime, String expireTime) {

		groupNameBox.sendKeys(name);
		groupCodeBox.sendKeys(code);

		Select statusList = new Select(groupStatusBox);
		statusList.selectByVisibleText(status);

		usagePriorityBox.sendKeys(String.valueOf(priority));
		descriptionBox.sendKeys(Descrption);

		Select parentList = new Select(parentBox);
		parentList.selectByVisibleText(parent);

		if (!effectiveTime.equals("")) {

			effectiveTimeBox.click();
			setDateTime(BtGroup_effectiveTime_id, effectiveTime);
		}

		if (!expireTime.equals("")) {

			expirationTimeBox.click();
			setDateTime(BtGroup_expirationTime_id, expireTime);
		}

		descriptionBox.click();

	}

}
