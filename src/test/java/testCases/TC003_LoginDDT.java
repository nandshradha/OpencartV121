package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		logger.info("****starting TC003_LoginDDT****");
		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			/*
			 * Data is Valid - login success-test pass-logout login failed -test fail Data
			 * is invalid -login success- test fail-logout login failed -test pass
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("invalid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("****Finished TC003_LoginDDT****");
	}

}
