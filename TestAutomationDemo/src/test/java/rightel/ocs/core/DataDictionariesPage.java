package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static rightel.ocs.properties.allObjects.*;

public class DataDictionariesPage extends OcsBasePage {

	@FindBy(id = DataDic_createNew_id)
	private WebElement newBtn;

	private WebDriver driver;

	public DataDictionariesPage(WebDriver driver) {

		this.driver = driver;
	}

	public DataDicPage createNew() {

		newBtn.click();
		return PageFactory.initElements(driver, DataDicPage.class);
	}

	public DataDicViewListPage viewList(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 6, 0);

			// driver.findElement(By.xpath("//jhi-data-dictionary-detail//button[2]")).click();
			return PageFactory.initElements(driver, DataDicViewListPage.class);

		}

		return null;
	}

	public DataDicPage editRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 6, 1);
			return PageFactory.initElements(driver, DataDicPage.class);

		}

		return null;
	}

	public DeleteMessagePage deleteRow(String searchItem) {

		if (searchItem(searchItem)) {

			findRowAndDoAction(searchItem, 6, 2);
			return PageFactory.initElements(driver, DeleteMessagePage.class);

		}

		return null;
	}

}
