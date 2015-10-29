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
public class CommentManagementAllTests {
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
		selenium.type("id=user_login", "cs1632_carmenkofi");
		selenium.type("id=passwd_login", "laboon");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.waitForPageToLoad("30000");
	}

	//Test 3 - a
	@Test
	public void A_testPostComment() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("link=Comment Test");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=text");
		selenium.type("name=text", "test comment for creating a comment");
		selenium.click("css=button.save");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("test comment for creating a comment"));
	}
	
	//Test 3 - b
	@Test
	public void B_testDeleteComment() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("xpath=(//a[contains(text(),'Comment Test')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=delete");
		selenium.click("xpath=(//a[contains(text(),'yes')])[3]");
		selenium.click("xpath=(//a[contains(text(),'Comment Test')])[2]");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(2000);
		assertEquals("there doesn't seem to be anything here", selenium.getText("id=noresults"));
	}
	
	//Test 3 - c
	@Test
	public void C_testEditComment() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//div[@id='thing_t3_3qpao3']/div[2]/p/a");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=a.edit-usertext");
		selenium.type("dom=document.activeElement", "now this is edited");
		selenium.click("//div[2]/div[2]/div/button");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("now this is edited"));
		selenium.click("css=a.edit-usertext");
		selenium.type("dom=document.activeElement", "this comment needs to be edited");
		selenium.click("//div[2]/div[2]/div/button");
		Thread.sleep(2000);
	}
	
	//Test 3 - d
	@Test
	public void D_testReplyComment() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("xpath=(//a[contains(text(),'Edit Test')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=reply");
		selenium.type("dom=document.activeElement", "this is a new reply");
		selenium.click("//div[3]/form/div/div[2]/div/button");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("this is a new reply"));
		selenium.click("xpath=(//a[contains(text(),'delete')])[2]");
		selenium.click("xpath=(//a[contains(text(),'yes')])[5]");
	}
	
	//Test 3 - e
	@Test
	public void E_testSaveComment() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//div[@id='thing_t3_3qaytn']/div[2]/p/a");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=li.comment-save-button.save-button > a");
		selenium.click("link=cs1632_carmenkofi");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=saved");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Cool comment"));
		selenium.click("link=unsave");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}