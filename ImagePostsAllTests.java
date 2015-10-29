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
public class ImagePostsAllTests {
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

	//Test 2 - a
	@Test
	public void A_testSuccessfulImagePost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "Old Bold and Brash");
		selenium.click("id=url");
		selenium.type("id=url", "http://i.imgur.com/fMTFzkM.jpg");
		selenium.click("name=captcha");
		String userEntry = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntry);
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("Old Bold and Brash", selenium.getText("link=Old Bold and Brash"));
		assertEquals("cs1632seleniumtestbed", selenium.getText("link=cs1632seleniumtestbed"));
		selenium.click("link=Old Bold and Brash");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://i.imgur.com/fMTFzkM.jpg", selenium.getLocation());
		selenium.goBack();
		selenium.waitForPageToLoad("30000");
		selenium.click("link=delete");
		selenium.click("xpath=(//a[contains(text(),'yes')])[2]");
	}
	
	//Test 2 - b
	@Test
	public void B_testInvalidTitlePost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "TbNaLUg71oCtX76MBGJOLQr6mkvqM4RFpAj6Nm0u4ZHYMWf56Hxe1IwEtL02qPSqRkxtn8WKbbDOm2C38ln8Z9EBHQ1NU5VVCTY1ZevyFDZ5P9LUtGutkksQefmbLU6cwwt6W1i3a6UU3TRTIKD2pnPKUi0Qg3F2BVtMl8jqt2l4O1TiZhkHDbgD8pthIpw0Mts7s4rmf8u29SkFP4pVPwsBEYoQ6oPXZuzTivc7bcDY40KenrSuW3byUnrbHVXrsVHnwBFyos9lqb4zLRZp24qpB9tI9rCzc5yqqCHx0JHq1xHEU2yjw3BChIQnR7jb");
		selenium.click("id=url");
		selenium.type("id=url", "http://i.imgur.com/fMTFzkM.jpg");
		selenium.click("name=captcha");
		String userEntry = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntry);
		selenium.click("name=submit");
		Thread.sleep(2000);
		assertEquals("this is too long (max: 300)", selenium.getText("//div[@id='title-field']/div/div[2]"));
	}
	
	//Test 2 - c
	@Test
	public void C_testInvalidCAPTCHA() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "Old Bold and Brash");
		selenium.click("id=url");
		selenium.type("id=url", "http://i.imgur.com/fMTFzkM.jpg");
		selenium.click("name=captcha");
		selenium.type("name=captcha", "1122341234");
		selenium.click("name=submit");
		Thread.sleep(2000);
		assertTrue(selenium.getText("//form[@id='newlink']/div/div[8]/div/span").matches("^care to try these again[\\s\\S]$"));
	}
	
	//Test 2 - d
	@Test
	public void D_testDuplicateImagePost() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "Old Bold and Brash");
		selenium.click("id=url");
		selenium.type("id=url", "http://i.imgur.com/fMTFzkM.jpg");
		selenium.click("name=captcha");
		String userEntry = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntry);
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("Old Bold and Brash", selenium.getText("link=Old Bold and Brash"));
		assertEquals("cs1632seleniumtestbed", selenium.getText("link=cs1632seleniumtestbed"));
		selenium.click("link=Old Bold and Brash");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://i.imgur.com/fMTFzkM.jpg", selenium.getLocation());
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=title");
		selenium.type("name=title", "Old Bold and Brash");
		selenium.click("id=url");
		selenium.type("id=url", "http://i.imgur.com/fMTFzkM.jpg");
		selenium.click("name=captcha");
		String userEntryTwo = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntryTwo);
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("Old Bold and Brash", selenium.getText("link=Old Bold and Brash"));
		assertEquals("cs1632seleniumtestbed", selenium.getText("link=cs1632seleniumtestbed"));
		selenium.click("link=Old Bold and Brash");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://i.imgur.com/fMTFzkM.jpg", selenium.getLocation());
		selenium.goBack();
		selenium.waitForPageToLoad("30000");
		assertEquals("that link has already been submitted, but you can try to submit it again.", selenium.getText("css=div.reddit-infobar.md-container-small  > div.md > p"));
		selenium.click("link=delete");
		selenium.click("xpath=(//a[contains(text(),'yes')])[2]");
	}
	
	//Test 2 - e
	@Test
	public void E_testSuggestImageTitle() throws Exception {
		selenium.open("/r/cs1632seleniumtestbed/");
		selenium.click("//body");
		selenium.click("link=Submit a new link");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=url");
		selenium.type("id=url", "http://imgur.com/gallery/ZfxHKxW");
		selenium.click("name=captcha");
		String userEntry = JOptionPane.showInputDialog("Please enter captcha: ");
		selenium.type("name=captcha", userEntry);
		selenium.click("css=button[type=\"button\"]");
		Thread.sleep(5000);
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("Somebody fixed the Panther at the University of Pittsburgh", selenium.getText("link=Somebody fixed the Panther at the University of Pittsburgh"));
		assertEquals("cs1632seleniumtestbed", selenium.getText("link=cs1632seleniumtestbed"));
		selenium.click("link=Somebody fixed the Panther at the University of Pittsburgh");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://imgur.com/gallery/ZfxHKxW", selenium.getLocation());
		selenium.goBack();
		selenium.waitForPageToLoad("30000");
		selenium.click("link=delete");
		selenium.click("xpath=(//a[contains(text(),'yes')])[2]");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}