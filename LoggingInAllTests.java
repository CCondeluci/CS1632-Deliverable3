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

@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggingInAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.reddit.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void A_testSuccessfulLogin() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Log in or sign up");
		Thread.sleep(2000);
		selenium.click("id=user_login");
		selenium.type("id=user_login", "cs1632_carmenkofi");
		selenium.type("id=passwd_login", "laboon");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=cs1632_carmenkofi");
		selenium.waitForPageToLoad("30000");
		assertEquals("overview", selenium.getText("link=overview"));
		selenium.click("link=logout");
	}
	
	@Test
	public void testInvalidUsername() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Log in or sign up");
		Thread.sleep(2000);
		selenium.click("id=user_login");
		selenium.type("id=user_login", "anything");
		selenium.type("id=passwd_login", "anything");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.mouseOver("//form[@id='login-form']/div[2]/div/span[2]");
		Thread.sleep(2000);
		assertEquals("wrong password", selenium.getText("css=div.tooltip-inner"));
	}
	
	@Test
	public void testInvalidPassword() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Log in or sign up");
		Thread.sleep(2000);
		selenium.click("id=user_login");
		selenium.type("id=user_login", "cs1632_carmenkofi");
		selenium.type("id=passwd_login", "wrongpw");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.mouseOver("//form[@id='login-form']/div[2]/div/span[2]");
		Thread.sleep(2000);
		assertEquals("wrong password", selenium.getText("css=div.tooltip-inner"));
	}
	
	@Test
	public void testNoUserAccount() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Log in or sign up");
		Thread.sleep(2000);
		assertEquals("CREATE A NEW ACCOUNT", selenium.getText("css=h4.modal-title"));
		assertTrue(selenium.isElementPresent("id=user_reg"));
		assertTrue(selenium.isElementPresent("id=passwd_reg"));
		assertTrue(selenium.isElementPresent("id=passwd2_reg"));
		assertTrue(selenium.isElementPresent("id=email_reg"));
		assertTrue(selenium.isElementPresent("//button[@type='submit']"));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}