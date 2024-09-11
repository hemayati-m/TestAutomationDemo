package rightel.ocs.core;

import static rightel.ocs.core.OcsCoreClass.*;
//import static rightel.ocs.core.ViewListSection.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OcsBasePage {
	
	private By bySaveBtn = By.id("save-entity");
	private By byListEntityBtn = By.id("list-entity");
	private By byViewEntityBtn = By.id("view-entity");
	private By byViewListBtn = By.xpath("//*[@class='d-flex justify-content-center']//button[3]");
	private By searchBoxElement = By.id("currentSearch");
	private By searchBtnElement = By.xpath("//form[@name='searchForm']/div/button[1]");	
	private By resetSearchBtnElement = By.xpath("//form[@name='searchForm']/div/button[2]");
	private String noRowFoundMessage;
	
	private static By byFieldsError = By.xpath("//*[@jhitranslate=\"entity.validation.required\"]");
	private static By byAlertDialog = By.xpath("//div[@role='alertdialog']");
	private static By noRowElement = By.xpath("//div[contains(@class,'alert-warning')]");
	private static By gridElement = By.xpath("//tbody");
	private static String responseMessage = null;
	private static WebElement grid;
	private static List<WebElement> rows;
	private static String[] fieldErrorText = null;

	// After doing action, message save in a variable
	public static void setResponseMessage() {

		if (isElementPresent(byAlertDialog)) {

			responseMessage = driver.findElement(byAlertDialog).getText();

		} else if (isElementPresent(By.xpath("//*[@id='toast-container']/div/div[1]"))) {

			responseMessage = driver.findElement(By.xpath("//*[@id='toast-container']/div/div[1]")).getText();

		} else if (isElementPresent(By.xpath("//mat-dialog-container"))) {

			responseMessage = driver
					.findElement(By.xpath(
							"//*[contains(@id,'mat-dialog')]/jhi-common-im-response/mat-card/mat-card-content/div"))
					.getText();

			// close show message box
			driver.findElement(By.xpath("//*[contains(@id,'mat-dialog')]//button/span[1]/mat-icon")).click();

		} else {

			responseMessage = "Could't find response message.";

		}

	}

	public static void selectByVisibleText(WebElement selectElement, String text) {

		try {
			selectElement.click();
			Select list = new Select(selectElement);
			list.selectByVisibleText(text);
		} catch (NoSuchElementException n) {
			
			System.out.println("\""+ text + "\" not founded.");

		}

	}

	// Click on close icon in selected page
	public void closeTab() {

		driver.findElement(By.xpath("//mat-icon[text()='close']")).click();
	}

	// Click on clear button and cancel save
	public void clear() {

		driver.findElement(By.id("cancel-save")).click();

	}

	// Click on Save button
	public boolean save() {

		try {

			driver.findElement(bySaveBtn).click();
			Thread.sleep(1000);
			setResponseMessage();

		} catch (Exception e) {

		}
		return isButtonDisabled(bySaveBtn);

	}

	// Check save button is disabled or not. if this button disabled that means save
	// action was successful.
	public static boolean isButtonDisabled(By by) {

		String status = driver.findElement(by).getAttribute("disabled");

		try {

			if (status.equals("true")) {

				return true;
			}

		} catch (NullPointerException e) {

			return false;
		}

		return false;

	}

	// Check save button is disabled or not. if this button disabled that means save
	// action was successful.
	public static boolean isButtonDisabled(WebElement webelement) {

		String status = null;
		try {
			
			status =  webelement.getAttribute("disabled");
			if (status.equals("true")) {
				System.out.println("in if");

				return true;
			}

		} catch (NullPointerException e) {
			
			System.out.println("catch");

			return false;
		}
		
		System.out.println("in end");
		return false;

	}

	public static void setFieldsError() {

		try {

			if (isElementPresent(byFieldsError)) {

				List<WebElement> list = driver.findElements(byFieldsError);
				fieldErrorText = new String[list.size()];

				for (int i = 0; i < list.size(); i++) {

					fieldErrorText[i] = list.get(i).getText();
				}

			}

		} catch (Exception e) {

		}
	}

	public String[] getFieldsError() {

		return fieldErrorText;
	}

	// After doing action, message save in a variable and return value of message
	public String getResponseMessage() {

		return responseMessage;
	}

	// Check presence of webElement
	public static boolean isElementPresent(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		try {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (TimeoutException e) {
			return false;

		}
	}

	// click on viewList button
	public void viewList() {

		WebElement viewListBtn;

		if (isElementPresent(byListEntityBtn)) {

			viewListBtn = driver.findElement(byListEntityBtn);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", viewListBtn);
			viewListBtn.click();

		} else if (isElementPresent(byViewEntityBtn)) {

			viewListBtn = driver.findElement(byViewEntityBtn);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", viewListBtn);
			viewListBtn.click();

		} else if (isElementPresent(byViewListBtn)) {

			viewListBtn = driver.findElement(byViewListBtn);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", viewListBtn);
			viewListBtn.click();
		}

		// after succesfully click on viewlist then grid and rows initialized.
		initializeGrid();
	}

	public String getTabName() {

		if (isElementPresent(By.xpath("//div[@class='mat-tab-label-container']//span"))) {

			return driver.findElement(By.xpath("//div[@class='mat-tab-label-container']//span")).getText();
		}

		return null;
	}

	public String getPageName() {

		if (isElementPresent(By.xpath("//mat-card-title/span"))) {

			return driver.findElement(By.xpath("//mat-card-title/span")).getText();
		}

		return null;
	}

	public boolean searchValue(String item) {

		return searchItem(item);
	}
	
	// return value of cell in the grid
	public String getTableCellValue(int row, int cell) {

		try {

			if (rows.size() > 0) {

				List<WebElement> cells = rows.get(row).findElements(By.tagName("td"));
				if (cells.size() > 0) {

					return cells.get(cell).getText();
				}
			}

		} catch (NullPointerException e) {

			// if grid has not row then message of no data found will get return.
			return driver.findElement(noRowElement).getText();

		} catch (IndexOutOfBoundsException i) {

			return "Index out of bounds";
		}

		return "Could not found value";
	}

	
	// searching for an item and return true
	public boolean searchItem(String item) {

		if (isElementPresent(searchBoxElement)) {

			WebElement searchBox = driver.findElement(searchBoxElement);

			searchBox.clear();
			searchBox.sendKeys(item);

			driver.findElement(searchBtnElement).click();

			if (hasRow()) {

				updateGridRows();
				return true;
			}
		}

		return false;
	}
	
	// Initial grid and rows after viewList button get click
	private void initializeGrid() {

		if (isElementPresent(gridElement)) {

			grid = driver.findElement(gridElement);
		}

		if (hasRow()) {

			rows = grid.findElements(By.tagName("tr"));
		}

	}
	
	// check there is row in grid or not
	public static boolean hasRow() {

		if (isElementPresent(noRowElement)) {

			return false;
		}

		return true;
	}
	
	// After change on data of grid, update rows is needed.
	public static void updateGridRows() {

		if (hasRow()) {

			if (isElementPresent(gridElement)) {

				grid = driver.findElement(gridElement);
			}
			rows = grid.findElements(By.tagName("tr"));
		}
	}
	
	// delete search box and reset grid
	public void searchReset() {

		driver.findElement(resetSearchBtnElement).click();
		updateGridRows();

	}
	
	// finding item in rows and do action on founded row
	// button and cells start from zero
	public static boolean findRowAndDoAction(String item, int actionCell, int botton) {

		// searchItem(item);

		if (rows.size() > 0) {

			for (int i = 0; i < rows.size(); i++) {

				// Insert cells of row in to list
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				for (int j = 0; j < cells.size(); j++) {

					// Finding cell
					if (item.equalsIgnoreCase(cells.get(j).getText())) {

						// Finding button in Action cell
						List<WebElement> btn = cells.get(actionCell).findElements(By.tagName("button"));
						btn.get(botton).click();
						return true;
					}

				}
			}
		}

		return false;

	}
	
	public String getEmptyGridMessage() {

		return noRowFoundMessage;
	}
}
