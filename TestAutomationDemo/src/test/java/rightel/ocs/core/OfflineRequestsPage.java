package rightel.ocs.core;

import static rightel.ocs.core.OcsDatePicker.*;
import static rightel.ocs.properties.allObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OfflineRequestsPage extends OcsBasePage {

	@FindBy(id = OfflineRequest_serviceType_id)
	private WebElement serviceTypeListBox;
	@FindBy(id = OfflineRequest_subsNumber_id)
	private WebElement subsNumberBox;
	@FindBy(id = OfflineRequest_query_id)
	private WebElement queryBtn;
	@FindBy(id = OfflineRequest_processStartDate_id)
	private WebElement processStartDateBox;
	@FindBy(id = OfflineRequest_processEndDate_id)
	private WebElement processEndDateBox;
	@FindBy(id = OfflineRequest_usageStartDate_id)
	private WebElement usageStartDateBox;
	@FindBy(id = OfflineRequest_usageEndDate_id)
	private WebElement usageEndDateBox;
	
	private WebDriver driver;
	

	public OfflineRequestsPage(WebDriver driver) {

		this.driver = driver;
	}

	public void selectServiceType(String serviceType) {

		selectByVisibleText(serviceTypeListBox, serviceType);
	}

	public void selectSubscriberNumber(String serviceNumber) {

		subsNumberBox.clear();
		subsNumberBox.sendKeys(serviceNumber);
	}

	public void selectProcessDate(String startDate, String endDate) {

		processStartDateBox.click();
		setDateTime(OfflineRequest_processStartDate_id, startDate);

		processEndDateBox.click();
		setDateTime(OfflineRequest_processEndDate_id, endDate);

	}

	public void selectUsageDate(String startDate, String endDate) {

		usageStartDateBox.click();
		setDateTime(OfflineRequest_usageStartDate_id, startDate);

		usageEndDateBox.click();
		setDateTime(OfflineRequest_usageEndDate_id, endDate);

	}

	public boolean runQuery() {

		if (isButtonDisabled(queryBtn)) {

			setFieldsError();
			return false;
		} else {

			queryBtn.click();
			updateGridRows();
			return true;
		}
	}

	public String getCellValue(int row, int cell) {

		return getTableCellValue(row, cell);
	}

}
