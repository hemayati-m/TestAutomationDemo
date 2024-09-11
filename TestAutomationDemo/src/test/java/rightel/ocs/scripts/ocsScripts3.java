package rightel.ocs.scripts;


import static rightel.ocs.core.OcsCoreClass.*;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import rightel.ocs.core.OcsHomePage;
import rightel.ocs.core.OcsLoginPage;
import rightel.ocs.listener.ApplicationLogger;




public class ocsScripts3 {
	
	OcsLoginPage lp = null;
	OcsHomePage hp = null;
	ApplicationLogger logger = null;
	


	
	@Test
	public void tc1() {
		
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void tc2() {
		
		logger.fail("This scenario was failed.");
	
		
		
	}
	
	@BeforeSuite
	public void beforeSuite() {
		
		lp = init();
		}
	
	@BeforeClass
	public void beforeClass() throws IOException {
		
		hp = (OcsHomePage) lp.doLogin("r.roozbehani","Pass@1403");

	}
	
	
	@AfterSuite
	public void afterSuite() {
		
		quitDriver();		
	}
	
	
}
