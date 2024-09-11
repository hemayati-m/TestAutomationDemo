package rightel.ocs.core;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class OcsScreenShot {
	
	private static String directory = System.getProperty("user.dir") + "\\src\\test\\java\\ScreenShots\\";
	
	
	public static String takeScreenShot(WebDriver driver) {
		
		String base64ScreenShotCode = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return base64ScreenShotCode;
	}
	
	//Take a shot from screen
	public static void takeScreenShot(WebDriver driver,String imageName) {

		try {
			
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenShot, new File(directory + imageName));
			Thread.sleep(500);
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//Take a shot totally from screen
	public static void takeFullScreenShot(WebDriver driver, String imageName) {
		

		try {

			Screenshot totalScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000))
					.takeScreenshot(driver);
			ImageIO.write(totalScreenShot.getImage(), "png", new File(directory + imageName));
			Thread.sleep(500);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	//Take a shot from screen by URL
	public static void takeScreenShotByURL(String imageName) {

		try {

			Robot robot = new Robot();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rect = new Rectangle(dim);
			BufferedImage buffImage = robot.createScreenCapture(rect);
			ImageIO.write(buffImage, "png", new File(directory + imageName));
			Thread.sleep(500);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
