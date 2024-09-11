package rightel.ocs.core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static rightel.ocs.properties.allObjects.*;

public class PermissionsPage extends OcsBasePage {

	@FindBy(id = Permissions_Code_id)
	private WebElement codeBox;
	@FindBy(id = Permissions_Name_id)
	private WebElement nameBox;
	@FindBy(id = Permissions_Status_id)
	private WebElement StatusListBox;
	@FindBy(id = Permissions_Product_id)
	private WebElement productListBox;

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public PermissionsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jhi-permission-heading")));
	}

	public void add(String code, String name, String status, String product) {

		codeBox.sendKeys(code);
		nameBox.sendKeys(name);

		selectByVisibleText(StatusListBox, status);
		selectByVisibleText(productListBox, product);
	}

	public boolean editRow(String searchItem, String code, String name, String status, String product) {

		if (hasRow()) {

			if (findRowAndDoAction(searchItem, 5, 0)) {

				updateFields(code, name, status, product);
				return true;
			}
		}
		return false;
	}

	public boolean deleteRow(String searchItem) {

		if (hasRow()) {

			if (findRowAndDoAction(searchItem, 5, 1)) {

				DeleteMessagePage del = new DeleteMessagePage(driver);
				del.approve();
				return true;
			}
		}

		return false;
	}

	private void updateFields(String code, String name, String status, String product) {

		if (!code.equalsIgnoreCase("")) {

			codeBox.clear();
			codeBox.sendKeys(code);
		}

		if (!name.equalsIgnoreCase("")) {

			nameBox.clear();
			nameBox.sendKeys(name);
		}

		if (!status.equalsIgnoreCase("")) {

			selectByVisibleText(StatusListBox, status);
		}

		if (!product.equalsIgnoreCase("")) {

			selectByVisibleText(productListBox, product);
		}
	}
}
