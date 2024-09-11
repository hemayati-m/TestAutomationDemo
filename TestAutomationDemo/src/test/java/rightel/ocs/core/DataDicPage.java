package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static rightel.ocs.properties.allObjects.*;


public class DataDicPage extends OcsBasePage  {
	
	@FindBy(id = DataDic_dataCode_id)
	private WebElement codeBox;
	
	@FindBy(id = DataDic_dataTitle_id)
	private WebElement titleBox;
	
	@FindBy(id = DataDic_description_id)
	private WebElement descBox;
	
	@FindBy(id = DataDic_status_id)
	private WebElement statusBox;
	
	@FindBy(id = DataDic_parentDataCode_id)
	private WebElement parentBox;
	
	private WebDriver driver;
	

	public DataDicPage(WebDriver driver) {

		this.driver = driver;
	}
	
	public void add(String code, String title, String desc, String status, String parent) {
		
		codeBox.sendKeys(code);
		titleBox.sendKeys(title);
		descBox.sendKeys(desc);
		
		Select statusList = new Select(statusBox);
		statusList.selectByVisibleText(status);
		
		parentBox.sendKeys(parent);
	
	}
	
	public DataDicViewListPage changeStatusReturnViewList(String status) {
		
		Select statusList = new Select(statusBox);
		statusList.selectByVisibleText(status);
		
		return PageFactory.initElements(driver, DataDicViewListPage.class);		
	}
	
	public DataDictionariesPage changeStatusReturnDataDic(String status) {
		
		Select statusList = new Select(statusBox);
		statusList.selectByVisibleText(status);
		
		return PageFactory.initElements(driver, DataDictionariesPage.class);		
	}

}
