package rightel.ocs.scripts;


import static rightel.ocs.core.OcsCoreClass.*;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import rightel.ocs.core.BalanceInquiryPage;
import rightel.ocs.core.BalanceTypeGroupMemberPage;
import rightel.ocs.core.BalanceTypeGroupPage;
import rightel.ocs.core.BalanceTypePage;
import rightel.ocs.core.ClearCachePage;
import rightel.ocs.core.DataDictionariesPage;
import rightel.ocs.core.OcsHomePage;
import rightel.ocs.core.OcsLoginPage;
import rightel.ocs.core.OcsScreenShot;
import rightel.ocs.core.SiteMapPage;
import rightel.ocs.core.OfflineRequestsPage;
import rightel.ocs.core.PermissionsPage;
import rightel.ocs.core.RefundConfigurationPage;
import rightel.ocs.core.ServiceStatusControlPage;
import rightel.ocs.core.SystemMenuPage;
import rightel.ocs.core.SystemParameterPage;

public class ocsScripts2 {
	
	OcsLoginPage lp = null;
	OcsHomePage hp = null;
	SiteMapPage map = null;
	PermissionsPage p = null;
	OfflineRequestsPage or = null;
	BalanceInquiryPage bi = null;
	ClearCachePage cc = null;
	BalanceTypePage bt = null;
	BalanceTypeGroupMemberPage btgm = null;
	BalanceTypeGroupPage btg = null;
	DataDictionariesPage dd = null;
	SystemMenuPage sm = null;
	RefundConfigurationPage rf = null;
	SystemParameterPage sp = null;
	ServiceStatusControlPage ssc= null;
	
	

	@Test(enabled=false)
	public void btGroupMemberTC() throws InterruptedException {
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-icon-button mat-button-base']")).click();
		//btgm = hp.goto_SiteMap().goto_BtGroupMemberPage();
		//btgm.fillFields("CDR Print Fees", "Internet for Local Data");
		//btgm.save();
		//btgm.viewList();
		//btgm.deleteRow("426").approve();
		
		//btgm.deleteRow_Approve("330");
		//System.out.println(btgm.getResponseMessage());

	}
	
	@Test(enabled=false)
	public void ServiceControlTC() {
		
		ssc = hp.goto_SiteMap().goto_ServiceStatusControlPage();
		 
		//ssc.printServiceControlandStatus();
		//ssc.activeControl("allocation");
		//System.out.println(getResponseMessage());
		
		//ServiceStatusControlPage ssc2 = new ServiceStatusControlPage();
		//ssc2.deactiveControl("activateOffer");
		//ssc.clickOn_Action();
		//System.out.println(ssc.getServiceStatus("activateOffer"));
		//System.out.println(ssc.getServiceStatus("baroneWay"));
		//System.out.println("Finish.");
		//System.out.println(getResponseMessage());
		System.out.println("Closing my page.");
		//ssc.closeTab();

	}
	
	@Test(enabled=false)
	public void SystemParamTC() throws InterruptedException {
		
		sp = hp.goto_SiteMap().goto_SystemParameterPage();
		//sp.clickOn_ViewList();
		//sp.fillFields("7378","Reza","Test","Inactive","Nadarad");
		
		Thread.sleep(2000);
		
		//sp.save();		
		sp.viewList();
		sp.deleteItem("7378").approve();
		//sp.clearData();
		//System.out.println(sp.getTabName());
		//System.out.println(sp.getPageName());
		//sp.changeStatus("638", "Active");
		
		//sp.deleteRow("638").approve();		

		//System.out.println(getResponseMessage());
	}

	@Test(enabled=false)
	public void RefundConfigTC() {
		
		rf = hp.goto_SiteMap().goto_RefundConfigurationPage();
		rf.add("Vas Retention Time",300);

		

		System.out.println(rf.getResponseMessage());
	}
	
	
	
	@Test(enabled=false)
	public void SystemMenuTC() {
		
		sm = hp.goto_SiteMap().goto_SystemMenuPage();
		
		//sm.add("Reza","Tehran","Fars","New","new icon", "Selection", "Nadarad", "site.map.ngocg.ngocg.mccmnc-ccMpg",true);
		
		//sm.viewList();
		//sm.activeMenu("133");
		sm.deleteRow("133").cancel();
		

		System.out.println(sm.getResponseMessage());
	}
	
	
	@Test(enabled=false)
	public void DataDicTC() {
		
		dd = hp.goto_SiteMap().goto_DataDictionariesPage();
		
		dd.createNew().add("7378", "Reza", "This is for test", "Inactive", "10");
		//dd.viewList("7378").edit().changeStatusReturnViewList("Inactive").back();
		//dd.viewList("7378").back();
		//dd.editRow("7378").changeStatusReturnDataDic("Inactive");
		dd.deleteRow("7378").approve();
		System.out.println(dd.getResponseMessage());
	}
	
	
	@Test(enabled=false)
	public void btGroupTC() {
		
		btg = hp.goto_SiteMap().goto_BtGroupPage();
		//btg.viewList();
		
		//btg.changeParent("304", "Farsi SMS");
		btg.add("TestReza", "7378", "Draft", 10, "This is for test.", "Internet Gift" , "2024/05/14 23:59:59", "");
		btg.changeExpireTime("338", "2030/12/25 23:59:59");
		//System.out.println(getResponseMessage());

	}

	
	@Test(priority=35, enabled=false)
	public void balanceTypeTC() {
		
		bt = hp.goto_SiteMap().goto_BalanceTypePage();
		//bt.add("BehsaNew", "0111", "Child", "Draft", "Yes", 20, "Yes", "Has", "Rial", "Has", 1, "Number", "virtual", "KB", 10, 25, "Has Not","2024/08/07","2034/08/07");
		//bt.save();
		bt.viewList();
		bt.searchValue("250");
		bt.changeBasicInfo("250","", "Behsa_Reza", "Test Reza");
		bt.save();
		//bt.changeStatus("230", "Release");
		//bt.changeClass("230", "bonus");
		//bt.changeUnitType("230", "Byte");
		System.out.println(bt.getResponseMessage());

	}
	
	@Test(priority=30, enabled=false)
	public void clearCacheTC() {
		
		cc = hp.goto_SiteMap().goto_ClearCachePage();
		//cc.clearDatadicCache();
		//cc.clearBalanceTypesCache();
		cc.clearRceCache();
		System.out.println("Response is: " + cc.getResponseMessage());
	}
	
	
	
	@Test(priority=25, enabled=false)
	public void OfflineRequests() {
		
		or = hp.goto_SiteMap().goto_OfflineRequestsPage();
		or.selectProcessDate("2024/05/14", "2024/06/14");
		or.selectUsageDate("2024/05/14", "2024/06/14");
		or.selectServiceType("Data");
		or.selectSubscriberNumber("9213111030");
		
		if (or.runQuery()) {
			
			System.out.println(or.getCellValue(2,5));
		} else {
			
			System.out.println(or.getFieldsError()[0]);
			
		}

		
		
	}
	
	
  @Test(priority=10, enabled=false)
  public void TC_AddPermission() {
	  
	  
	  p = hp.goto_SiteMap().goto_PermissionPage();
	  
	  
	  p.add("254", "TestReza5", "SuspendOnAll", "Data Test DPP"); 	  
	  
	  if(p.save()) {
		  
		  Assert.assertTrue(true,p.getResponseMessage());
		  
	  } else {		  
		  Assert.fail(p.getResponseMessage());
		  
	  }
  }
  
  @Test(priority=15, enabled=false)
  public void TC_EditPermission(){
	  
	  
	  p = hp.goto_SiteMap().goto_PermissionPage();	  
	  
	  p.viewList();
	  p.searchValue("Test");
	  if(p.editRow("254","253","","","VIP Postpaid SIM")) {
		  ;
		  
		  if(p.save()) {
			  
			  Assert.assertTrue(true,p.getResponseMessage());
			  
		  } else {		  
			  Assert.fail(p.getResponseMessage());
			  
		  }
		  
	  } else {
		  
		  Assert.fail("No Row Founded.");		  
	  }

  }
  
  @Test(priority=20, enabled=false)
  public void TC_DeletePermission() {
	  
	  
	  p = hp.goto_SiteMap().goto_PermissionPage();
	  
	  p.viewList();
	  p.searchValue("Test");
	  if(p.deleteRow("253")) {
		  Assert.assertTrue(true,p.getResponseMessage());
	  } else {
		  
		  Assert.fail("No Row Founded.");
	  }
  }
  
	@BeforeSuite
	public void beforeSuite() throws IOException {
		lp = init();

	}
	
	@BeforeClass
	public void beforeClass() throws IOException {
		
		//hp = lp.doLogin("r.roozbehani","Pass@1403");

	}
	
	
}
