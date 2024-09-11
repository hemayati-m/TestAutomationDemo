package rightel.ocs.core;

import static rightel.ocs.properties.allObjects.sitemap_Child111Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child112Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child1210Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child1211Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child1212Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child1213Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child121Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child122Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child123Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child124Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child125Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child126Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child127Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child128Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child129Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Child2173Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent1Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent2Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent3Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent4Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent5Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent6Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent7Btn_xpath;
import static rightel.ocs.properties.allObjects.sitemap_Parent8Btn_xpath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import static rightel.ocs.core.OcsCoreClass.*;

public class SiteMapPage {

	@FindBy(xpath = sitemap_Parent1Btn_xpath)
	private WebElement UsmBtn;
	@FindBy(xpath = sitemap_Parent2Btn_xpath)
	private WebElement UpcBtn;
	@FindBy(xpath = sitemap_Parent3Btn_xpath)
	private WebElement InvoicingBtn;
	@FindBy(xpath = sitemap_Parent4Btn_xpath)
	private WebElement BcBtn;
	@FindBy(xpath = sitemap_Parent5Btn_xpath)
	private WebElement ArmBtn;
	@FindBy(xpath = sitemap_Parent6Btn_xpath)
	private WebElement CcmBtn;
	@FindBy(xpath = sitemap_Parent7Btn_xpath)
	private WebElement NgBtn;
	@FindBy(xpath = sitemap_Parent8Btn_xpath)
	private WebElement DcBtn;
	@FindBy(xpath = sitemap_Child111Btn_xpath)
	private WebElement SubsPermissionBtn;
	@FindBy(xpath = sitemap_Child112Btn_xpath)
	private WebElement PermissionBtn;
	@FindBy(xpath = sitemap_Child121Btn_xpath)
	private WebElement OfflineRequestsBtn;
	@FindBy(xpath = sitemap_Child122Btn_xpath)
	private WebElement ClearCacheBtn;
	@FindBy(xpath = sitemap_Child123Btn_xpath)
	private WebElement BalanceTypeBtn;
	@FindBy(xpath = sitemap_Child124Btn_xpath)
	private WebElement BtGroupMemberBtn;
	@FindBy(xpath = sitemap_Child125Btn_xpath)
	private WebElement BtGroupBtn;
	@FindBy(xpath = sitemap_Child126Btn_xpath)
	private WebElement DataDicBtn;
	@FindBy(xpath = sitemap_Child127Btn_xpath)
	private WebElement SystemActionParameterBtn;
	@FindBy(xpath = sitemap_Child128Btn_xpath)
	private WebElement SystemMenuBtn;
	@FindBy(xpath = sitemap_Child129Btn_xpath)
	private WebElement SwitchOffileChargingBtn;
	@FindBy(xpath = sitemap_Child1210Btn_xpath)
	private WebElement RefundConfigurationBtn;
	@FindBy(xpath = sitemap_Child1211Btn_xpath)
	private WebElement SystemParameterBtn;
	@FindBy(xpath = sitemap_Child1212Btn_xpath)
	private WebElement AuditEventBtn;
	@FindBy(xpath = sitemap_Child1213Btn_xpath)
	private WebElement ServiceStatusControlBtn;
	@FindBy(xpath = sitemap_Child2173Btn_xpath)
	private WebElement RatingEventPriceBtn;

	private WebDriver driver;
	private int isExpansion = 0;

	public SiteMapPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public RatingEventPricePage goto_RatingEventPricePage() {

		if (isExpansion == 2) {

			RatingEventPriceBtn.click();
			return PageFactory.initElements(driver, RatingEventPricePage.class);

		} else {

			isExpansion = 2;
			UpcBtn.click();
			RatingEventPriceBtn.click();
			return PageFactory.initElements(driver, RatingEventPricePage.class);

		}

	}

	public ServiceStatusControlPage goto_ServiceStatusControlPage() {

		if (isExpansion == 1) {

			ServiceStatusControlBtn.click();
			return PageFactory.initElements(driver, ServiceStatusControlPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			ServiceStatusControlBtn.click();
			return PageFactory.initElements(driver, ServiceStatusControlPage.class);

		}

	}

	public SystemParameterPage goto_SystemParameterPage() {

		if (isExpansion == 1) {

			SystemParameterBtn.click();
			return PageFactory.initElements(driver, SystemParameterPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			SystemParameterBtn.click();
			return PageFactory.initElements(driver, SystemParameterPage.class);

		}

	}

	public RefundConfigurationPage goto_RefundConfigurationPage() {

		if (isExpansion == 1) {

			RefundConfigurationBtn.click();
			return PageFactory.initElements(driver, RefundConfigurationPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			RefundConfigurationBtn.click();
			return PageFactory.initElements(driver, RefundConfigurationPage.class);

		}

	}

	public SwitchOffileChargingPage goto_SwitchOffileChargingPage() {

		if (isExpansion == 1) {

			SwitchOffileChargingBtn.click();
			return PageFactory.initElements(driver, SwitchOffileChargingPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			SwitchOffileChargingBtn.click();
			return PageFactory.initElements(driver, SwitchOffileChargingPage.class);

		}

	}

	public SystemMenuPage goto_SystemMenuPage() {

		if (isExpansion == 1) {

			SystemMenuBtn.click();
			return PageFactory.initElements(driver, SystemMenuPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			SystemMenuBtn.click();
			return PageFactory.initElements(driver, SystemMenuPage.class);

		}

	}

	public SystemActionParameterPage goto_SystemActionParameterPage() {

		if (isExpansion == 1) {

			SystemActionParameterBtn.click();
			return PageFactory.initElements(driver, SystemActionParameterPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			SystemActionParameterBtn.click();
			return PageFactory.initElements(driver, SystemActionParameterPage.class);

		}

	}

	public DataDictionariesPage goto_DataDictionariesPage() {

		if (isExpansion == 1) {

			DataDicBtn.click();
			return PageFactory.initElements(driver, DataDictionariesPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			DataDicBtn.click();
			return PageFactory.initElements(driver, DataDictionariesPage.class);

		}

	}

	public BalanceTypeGroupPage goto_BtGroupPage() {

		if (isExpansion == 1) {

			BtGroupBtn.click();
			return PageFactory.initElements(driver, BalanceTypeGroupPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			BtGroupBtn.click();
			return PageFactory.initElements(driver, BalanceTypeGroupPage.class);

		}

	}

	public BalanceTypeGroupMemberPage goto_BtGroupMemberPage() {

		if (isExpansion == 1) {

			BtGroupMemberBtn.click();
			return PageFactory.initElements(driver, BalanceTypeGroupMemberPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			BtGroupMemberBtn.click();
			return PageFactory.initElements(driver, BalanceTypeGroupMemberPage.class);

		}

	}

	public BalanceTypePage goto_BalanceTypePage() {

		if (isExpansion == 1) {

			BalanceTypeBtn.click();
			return PageFactory.initElements(driver, BalanceTypePage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			BalanceTypeBtn.click();
			return PageFactory.initElements(driver, BalanceTypePage.class);

		}

	}

	public ClearCachePage goto_ClearCachePage() {

		if (isExpansion == 1) {

			ClearCacheBtn.click();
			return PageFactory.initElements(driver, ClearCachePage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			ClearCacheBtn.click();
			return PageFactory.initElements(driver, ClearCachePage.class);

		}

	}

	public OfflineRequestsPage goto_OfflineRequestsPage() {

		if (isExpansion == 1) {

			OfflineRequestsBtn.click();
			return PageFactory.initElements(driver, OfflineRequestsPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			OfflineRequestsBtn.click();
			return PageFactory.initElements(driver, OfflineRequestsPage.class);

		}

	}

	public PermissionsPage goto_SubsPermissionPage() {

		if (isExpansion == 1) {

			SubsPermissionBtn.click();
			return PageFactory.initElements(driver, PermissionsPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			SubsPermissionBtn.click();
			return PageFactory.initElements(driver, PermissionsPage.class);

		}

	}

	public PermissionsPage goto_PermissionPage() {

		if (isExpansion == 1) {

			PermissionBtn.click();
			return PageFactory.initElements(driver, PermissionsPage.class);

		} else {

			isExpansion = 1;
			UsmBtn.click();
			PermissionBtn.click();
			return PageFactory.initElements(driver, PermissionsPage.class);

		}

	}

}
