package rightel.ocs.core;

import static rightel.ocs.properties.allObjects.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BalanceTypeGroupMemberPage extends OcsBasePage {

	@FindBy(id = BtGroupMember_balanceTypeGroup_id)
	private WebElement balanceGroupBox;
	@FindBy(id = BtGroupMember_balanceType_id)
	private WebElement balanceTypeBox;
	@FindBy(id = BtGroupMember_viewList_id)
	private WebElement viewListBtn;

	private WebDriver driver;

	public boolean checkType(int inoutVal)
	{
		if (inoutVal<0)
			return false;
		return true;
	}
	public BalanceTypeGroupMemberPage(WebDriver driver) {

		this.driver = driver;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public DeleteMessagePage deleteRow(String searchItem) {

		findRowAndDoAction(searchItem, 3, 0);
		return PageFactory.initElements(driver, DeleteMessagePage.class);

	}

	public void fillFields(String Balancegroup, String balanceType) {

		balanceGroupBox.click();
		selectItem(Balancegroup);

		balanceTypeBox.click();
		selectItem(balanceType);

	}

	private boolean selectItem(String searchItem) {

		WebElement ul = driver.findElement(By.xpath("//div[@role='listbox']"));
		List<WebElement> list = ul.findElements(By.tagName("span"));

		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				if (searchItem.equalsIgnoreCase(list.get(i).getText())) {

					list.get(i).click();
					return true;
				}
			}
		}

		return false;
	}
}
