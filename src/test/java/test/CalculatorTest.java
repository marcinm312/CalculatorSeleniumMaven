package test;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CalculatorPage;

public class CalculatorTest {

	private static CalculatorPage calculatorPage;
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(CalculatorTest.class);

	@BeforeMethod
	private void start() {
		calculatorPage = new CalculatorPage();
	}

	@AfterMethod
	private void finishTest(ITestResult result) throws IOException {
		if (!result.isSuccess()) {
			String methodName = result.getName().trim();
			calculatorPage.takeScreenshot(methodName);
		}
		calculatorPage.closeBrowser();
	}

	@Test
	public static void mainTest() throws InterruptedException {
		log.info("Step 1...");
		step1();
		log.info("Step 2...");
		step2();
		log.info("Step 3...");
		step3();
		log.info("Step 4...");
		step4();
		log.info("Step 5...");
		step5();
	}

	private static void step5() {
		calculatorPage.clickHistoryButton();
		ensureCalculationsAreListed();
	}

	private static void step4() throws InterruptedException {
		calculatorPage.setDegree();
		calculatorPage.clickSqrtButton();
		calculatorPage.fillCalculatorField("81");
		calculatorPage.clickCloseBracketButton();
		calculatorPage.clickEqualsButton();
		checkCalculatorResult("9");
		calculatorPage.clickClearButton();
	}

	private static void step3() throws InterruptedException {
		calculatorPage.setRadian();
		calculatorPage.fillCalculatorField("cos(pi)");
		calculatorPage.clickEqualsButton();
		checkCalculatorResult("-1");
		calculatorPage.clickClearButton();
	}

	private static void step2() throws InterruptedException {
		calculatorPage.clickAcceptCookiesButton();
		calculatorPage.setDegree();
		calculatorPage.fillCalculatorField("35");
		calculatorPage.clickMultiplyButton();
		calculatorPage.fillCalculatorField("999");
		calculatorPage.clickPlusButton();
		calculatorPage.clickOpenBracketButton();
		calculatorPage.fillCalculatorField("100");
		calculatorPage.clickDivideButton();
		calculatorPage.fillCalculatorField("4");
		calculatorPage.clickCloseBracketButton();
		calculatorPage.clickEqualsButton();
		checkCalculatorResult("34990");
		calculatorPage.clickClearButton();
	}

	private static void step1() {
		calculatorPage.setUp();
		calculatorPage.openPage();
	}

	private static void checkCalculatorResult(String expectedResult) {
		Assert.assertEquals(calculatorPage.getTextFromCalculatorField(), expectedResult);
	}

	private static void ensureCalculationsAreListed() {
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(3, 2), "35*999+(100/4)");
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(3, 1), "= 34990");
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(2, 2), "cos(pi)");
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(2, 1), "= -1");
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(1, 2), "sqrt(81)");
		Assert.assertEquals(calculatorPage.getCellFromCalculationsHistory(1, 1), "= 9");
	}
}
