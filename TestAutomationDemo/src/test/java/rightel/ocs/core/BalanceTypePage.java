package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static rightel.ocs.core.OcsDatePicker.*;
import static rightel.ocs.properties.allObjects.*;

public class BalanceTypePage extends OcsBasePage {

	@FindBy(id = BalanceType_allowAdjustment_id)
	private WebElement allowAdjustmentBox;
	@FindBy(id = BalanceType_allowAmountTransfer_id)
	private WebElement allowAmountTransferBox;
	@FindBy(id = BalanceType_Class_id)
	private WebElement classBox;
	@FindBy(id = BalanceType_code_id)
	private WebElement codeBox;
	@FindBy(id = BalanceType_creditType_id)
	private WebElement creditTypeBox;
	@FindBy(id = BalanceType_description_id)
	private WebElement descriptionBox;
	@FindBy(id = BalanceType_effectiveTime_id)
	private WebElement effectiveTimeBox;
	@FindBy(id = BalanceType_feeRestriction_id)
	private WebElement feeRestrictionBox;
	@FindBy(id = BalanceType_chargeCode_id)
	private WebElement chargeCodeBox;
	@FindBy(id = BalanceType_expirationTime_id)
	private WebElement expirationTimeBox;
	@FindBy(id = BalanceType_isRechargeable_id)
	private WebElement isRechargeableBox;
	@FindBy(id = BalanceType_maxPrepaiment_id)
	private WebElement maxPrepaimentBox;
	@FindBy(id = BalanceType_name_id)
	private WebElement nameBox;
	@FindBy(id = BalanceType_Status_id)
	private WebElement statusBox;
	@FindBy(id = BalanceType_taxExemption_id)
	private WebElement taxExemptionBox;
	@FindBy(id = BalanceType_unitPercision_id)
	private WebElement unitPercisionBox;
	@FindBy(id = BalanceType_unitType_id)
	private WebElement unitTypeBox;
	@FindBy(id = BalanceType_usagePriority_id)
	private WebElement usagePriorityBox;
	@FindBy(id = BalanceType_currencyType_id)
	private WebElement currencyTypeBox;

	private WebDriver driver;

	public BalanceTypePage(WebDriver driver) {

		this.driver = driver;
	}

	public void add(String name, String code, String desc, String status, String allowAdjustment, int maxPrepaimnet,
			String taxExemption, String allowAmountTransfer, String currency, String feeRestriction, int usagePriority,
			String creditType, String balanceTypeClass, String unitType, int unitPercision, int chargeCode,
			String isRechargeable, String effectiveTime, String expirationTime) {

		nameBox.sendKeys(name);
		codeBox.sendKeys(code);
		descriptionBox.sendKeys(desc);

		selectByVisibleText(statusBox, status);
		selectByVisibleText(allowAdjustmentBox, allowAdjustment);

		maxPrepaimentBox.sendKeys(Integer.toString(maxPrepaimnet));

		selectByVisibleText(taxExemptionBox, taxExemption);
		selectByVisibleText(allowAmountTransferBox, allowAmountTransfer);
		selectByVisibleText(currencyTypeBox, currency);
		selectByVisibleText(feeRestrictionBox, feeRestriction);

		usagePriorityBox.sendKeys(Integer.toString(usagePriority));

		selectByVisibleText(creditTypeBox, creditType);
		selectByVisibleText(classBox, balanceTypeClass);
		selectByVisibleText(unitTypeBox, unitType);

		unitPercisionBox.sendKeys(Integer.toString(unitPercision));
		chargeCodeBox.sendKeys(Integer.toString(chargeCode));

		selectByVisibleText(isRechargeableBox, isRechargeable);

		effectiveTimeBox.click();
		setDateTime(BalanceType_effectiveTime_id, effectiveTime);

		expirationTimeBox.click();
		setDateTime(BalanceType_expirationTime_id, expirationTime);

	}

	private boolean editRow(String searchItem) {

		if (hasRow()) {

			findRowAndDoAction(searchItem, 12, 0);
			return true;
		}
		return false;
	}

	public void changeBasicInfo(String searchItem, String code, String name, String desc) {

		if (editRow(searchItem)) {

			if (!name.equals("")) {

				nameBox.clear();
				nameBox.sendKeys(name);
			}

			if (!code.equals("")) {

				codeBox.clear();
				codeBox.sendKeys(code);
			}

			if (!desc.equals("")) {

				descriptionBox.clear();
				descriptionBox.sendKeys(desc);
			}
		}
	}

	public boolean changeStatus(String searchItem, String status) {

		if (editRow(searchItem)) {

			if (!status.equals("")) {

				selectByVisibleText(statusBox, status);
				return true;
			}
		}

		return false;
	}

	public boolean changeClass(String searchItem, String balanceTypeClass) {

		if (editRow(searchItem)) {

			if (!balanceTypeClass.equals("")) {

				selectByVisibleText(classBox, balanceTypeClass);
				return true;
			}
		}

		return false;
	}

	public boolean changeUnitType(String searchItem, String unitType) {

		if (editRow(searchItem)) {

			if (!unitType.equals("")) {

				selectByVisibleText(unitTypeBox, unitType);
				return true;
			}
		}
		return false;
	}
}
