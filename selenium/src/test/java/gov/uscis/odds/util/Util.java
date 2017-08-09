package gov.uscis.odds.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Util {
	public static void waitFor(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sendKeys(WebDriver driver, String id, String value) {
		((JavascriptExecutor)driver).executeScript ("document.getElementById('"+id+"').setAttribute('value', '"+value+"')");
	}
}
