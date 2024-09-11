package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static rightel.ocs.properties.allObjects.*;

public class DataDicViewListPage extends OcsBasePage {

	@FindBy(xpath = DataDic_back_xpath)
	private WebElement backBtn;

	@FindBy(xpath = DataDic_edit_xpath)
	private WebElement editBtn;

	private WebDriver driver;

	public DataDicViewListPage(WebDriver driver) {

		this.driver = driver;
	}

	public DataDictionariesPage back() {

		backBtn.click();
		return PageFactory.initElements(driver, DataDictionariesPage.class);
	}

	public DataDicPage edit() {

		editBtn.click();
		System.out.println("Edit Item");
		return PageFactory.initElements(driver, DataDicPage.class);
	}

}
