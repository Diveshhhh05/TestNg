package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private final By edtUsername = By.name("username1");
	private final By edtPassword = By.name("password");
	private final By edtLogin = By.tagName("button");
	
	public boolean enterUsername(String userid) {
		enterText(edtUsername,userid);
		return true;
	}
	
	public boolean enterPassword(String pwd) {
		enterText(edtPassword,pwd);
		return true;
	}
	public boolean clickLogin() {
		click(edtLogin);
		return true;
	}
}
