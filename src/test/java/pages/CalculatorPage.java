package pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

public class CalculatorPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());


	public void setUp() {

		WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openPage() {
		driver.get("https://web2.0calc.com/");
	}

	public void fillCalculatorField(String text) {
		driver.findElement(By.id("input")).sendKeys(text);
	}

	public String getTextFromCalculatorField() {
		return driver.findElement(By.id("input")).getDomProperty("value");
	}

	public void clickEqualsButton() throws InterruptedException {
		WebElement equalsButton = driver.findElement(By.id("BtnCalc"));
		wait.until(ExpectedConditions.elementToBeClickable(equalsButton));
		equalsButton.click();
		Thread.sleep(1000);
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
		WebElement degreeButton = driver.findElement(By.id("trigodeg"));
		wait.until(ExpectedConditions.elementToBeClickable(degreeButton));

		degreeButton.click();
	}

	public void setRadian() {
		driver.findElement(By.id("trigorad")).click();
	}

	public void clickHistoryButton() {
		driver.findElement(By.xpath("//*[@id=\"hist\"]/button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"histframe\"]/ul")));
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
			log.error("Test zakończony niepowodzeniem. Utworzono zrzut ekranu: {}", fileName);
		} catch (IOException e) {
			log.error("Błąd podczas tworzenia zrzutu ekranu");
			throw e;
		}
	}

	public void clickAcceptCookiesButton() {
		WebElement acceptManagementPlatformButton = driver.findElement
				(By.xpath ("(//*[contains(text(),'Do not consent')])[last()]"));
		wait.until(ExpectedConditions.elementToBeClickable(acceptManagementPlatformButton));
		acceptManagementPlatformButton.click();

		/*WebElement acceptCookiesButton = driver.findElement(By.xpath ("//*[contains(text(),'Consent')]"));
		wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
		acceptCookiesButton.click();*/
	}
}
