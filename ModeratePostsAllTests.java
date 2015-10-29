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
public class ModeratePostsAllTests {
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

	@Test
	public void A_testStickiedPost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//div[@id='thing_t3_3qaytn']/div[2]/p/a");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=sticky this post");
		selenium.click("xpath=(//a[contains(text(),'yes')])[8]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=cs1632seleniumtestbed");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#thing_t3_3qaytn > div.entry.unvoted > ul.flat-list.buttons");
		Thread.sleep(2000);
		assertTrue(selenium.isElementPresent("css=span.stickied-tagline"));
	}
	
	@Test
	public void B_testUnStickyPost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//div[@id='thing_t3_3qaytn']/div[2]/p/a");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=unsticky this post");
		selenium.click("xpath=(//a[contains(text(),'yes')])[8]");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(7000); //sometimes unstick takes a long time to process
		selenium.click("link=cs1632seleniumtestbed");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(2000);
		assertFalse(selenium.isElementPresent("css=span.stickied-tagline"));
	}
	
	@Test
	public void C_testApprovePost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Submit a new text post");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "post to be approved");
		selenium.click("name=captcha");
		String userEntry = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntry);
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=approve");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("approved"));
	}
	
	@Test
	public void D_testRemovePost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=post to be approved");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=remove");
		selenium.click("xpath=(//a[contains(text(),'yes')])[5]");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("removed"));
	}
	
	@Test
	public void E_testApproveRemovedPost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=moderation log");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=link \"post to be approved\" by cs1632_carmenkofimod");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=approve");
		selenium.click("link=cs1632seleniumtestbed");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("post to be approved"));
		selenium.click("link=post to be approved");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=delete");
		selenium.click("xpath=(//a[contains(text(),'yes')])[3]");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}