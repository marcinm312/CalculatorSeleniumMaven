package pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private final String fileSeparator = FileSystems.getDefault().getSeparator();


	public void setUp() {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("Used OS: " + os);
		if (os.startsWith("windows")) {
			System.setProperty("webdriver.chrome.driver", "drivers" + fileSeparator + "windows" + fileSeparator + "chromedriver.exe");
		} else if (os.startsWith("linux")) {
			System.setProperty("webdriver.chrome.driver", "drivers" + fileSeparator + "linux" + fileSeparator + "chromedriver");
		} else {
			System.exit(-1);
		}
		driver = new ChromeDriver();
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
		return driver.findElement(By.id("input")).getAttribute("value");
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
		driver.findElement(By.id("trigodeg")).click();
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
			System.out.println("Test zakończony niepowodzeniem. Utworzono zrzut ekranu: " + fileName);
		} catch (IOException e) {
			System.out.println("Błąd podczas tworzenia zrzutu");
			throw e;
		}
	}

	public void clickAcceptCookiesButton() {
		WebElement acceptCookiesButton = driver.findElement(By.xpath ("//*[contains(text(),'Consent')]"));
		wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
		acceptCookiesButton.click();

		WebElement degreeButton = driver.findElement(By.id("trigodeg"));
		wait.until(ExpectedConditions.elementToBeClickable(degreeButton));
	}
}
