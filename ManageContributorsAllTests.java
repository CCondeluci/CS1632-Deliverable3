//Carmen Condeluci and Kofi Osei
//CS1632 - Prof. Bill Laboon
//10/27/15
//Reddit.com Black Box Testing via Selenium WebDriver
//
//All test are labled by "feature file # - scenario #",
//which can be found in our Deliverable-3.docx writeup.

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;
import javax.swing.JOptionPane; 

@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManageContributorsAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.reddit.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		
		//Log in for all tests
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Log in or sign up");
		Thread.sleep(2000);
		selenium.click("id=user_login");
		selenium.type("id=user_login", "cs1632_carmenkofimod");
		selenium.type("id=passwd_login", "laboon");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.waitForPageToLoad("30000");
	}

	//Test 4 - b
	@Test
	public void A_testDeleteContributer() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=edit approved submitters");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=remove");
		selenium.click("xpath=(//a[contains(text(),'yes')])[2]");
		Thread.sleep(2000);
		assertFalse(selenium.isTextPresent("cs1632_addremove"));
		selenium.click("link=edit approved submitters");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=name");
		selenium.type("id=name", "cs1632_carmenkofimod");
		selenium.click("css=button.btn");
	}
	
	//Test 4 - a
	@Test
	public void B_testAddContributer() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=edit approved submitters");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=name");
		selenium.type("id=name", "cs1632_addremove");
		selenium.click("css=button.btn");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("cs1632_addremove"));
	}
	
	//Test 4 - c
	@Test
	public void C_testMessageContributer() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=edit approved submitters");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=send message");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("send a private message"));
	}
	
	//Test 4 - d
	@Test
	public void D_testSearchContributor() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=edit approved submitters");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=user");
		selenium.type("id=user", "cs1632_addremove");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=user");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent(""));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}