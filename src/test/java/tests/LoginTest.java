package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.DataProviderUtils;
import utils.Log;

public class LoginTest extends BaseTest {
	private LoginPage login;
	
	@BeforeMethod()
	public void beforeMethod() {
		login = new LoginPage(driver);
	}
	
	@Parameters({"username","password"})
	@Test (priority=0,enabled=false)
	public void loginValidCredentials(String username, String password) throws InterruptedException {
		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLogin();
		Thread.sleep(5000);
		String dashboardUrl = driver.getCurrentUrl();
		Assert.assertTrue(dashboardUrl.contains("dashboard"));
	}
	
	@Test (priority=1,enabled=true)
	public void loginInValidCredentials() throws InterruptedException {
		Log.info("test case: loginInValidCredentials execution started" );
		login.enterUsername("Admin");
		Log.info("Entered username Admin" );
		login.enterPassword("admin1234");
		Log.info("Entered username admin1234" );
		login.clickLogin();
		Log.info("Clicked login button" );
		String dashboardUrl = driver.getCurrentUrl();
		Assert.assertFalse(dashboardUrl.contains("dashboard"));
		Log.info("test case: loginInValidCredentials execution completed" );
	}
	@Test (dataProvider = "loginDetails", dataProviderClass = DataProviderUtils.class, priority=0,enabled=false)
	public void loginMultipleCombo(String username, String password) throws InterruptedException {
		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLogin();
		Thread.sleep(5000);
		String dashboardUrl = driver.getCurrentUrl();
		Assert.assertTrue(dashboardUrl.contains("dashboard"));
	}

}
