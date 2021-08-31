package pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalculatorPage {
	public WebDriver driver;

	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void openPage() {
		driver.get("https://web2.0calc.com/");
	}

	public void fillCalculatorField(String text) {
		driver.findElement(By.id("input")).sendKeys(text);
	}

	public String getTextFromCalculatorField() {
		return driver.findElement(By.id("input")).getAttribute("value");
	}

	public void clickEqualsButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("BtnCalc")).click();
		Thread.sleep(2000);
	}

	public void clickPlusButton() {
		driver.findElement(By.id("BtnPlus")).click();
	}

	public void clickMultiplyButton() {
		driver.findElement(By.id("BtnMult")).click();
	}

	public void clickDivideButton() {
		driver.findElement(By.id("BtnDiv")).click();
	}

	public void clickOpenBracketButton() {
		driver.findElement(By.id("BtnParanL")).click();
	}

	public void clickCloseBracketButton() {
		driver.findElement(By.id("BtnParanR")).click();
	}

	public void clickSqrtButton() {
		driver.findElement(By.id("BtnSqrt")).click();
	}

	public void clickClearButton() {
		driver.findElement(By.id("BtnClear")).click();
	}

	public void setDegree() {
		driver.findElement(By.id("trigodeg")).click();
	}

	public void setRadian() {
		driver.findElement(By.id("trigorad")).click();
	}

	public void clickHistoryButton() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"hist\"]/button[2]")).click();
		Thread.sleep(1000);
	}

	public String getCellFromCalculationsHistory(int w, int k) {
		return driver.findElement(By.xpath("//*[@id=\"histframe\"]/ul/li[" + w + "]/p[" + k + "]")).getText();
	}

	public void closeBrowser() {
		driver.quit();
	}

	public void takeScreenshot(String methodName) throws IOException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(date) + methodName;
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("screenshots\\" + fileName + ".png"));
			System.out.println("Test zakonczony niepowodzeniem. Utworzono zrzut ekranu: " + fileName);
		} catch (IOException e) {
			System.out.println("Blad podczas tworzenia zrzutu");
			throw e;
		}
	}

	public void clickAcceptCookiesButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath ("//*[contains(text(),'Consent')]")).click();
		Thread.sleep(2000);
	}
}
