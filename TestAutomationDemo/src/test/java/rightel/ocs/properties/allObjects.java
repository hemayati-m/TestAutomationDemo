package rightel.ocs.properties;

public class allObjects {
	
	
	
	//IOT DataBase Configuration Details
	public static String dbDriver = "oracle.jdbc.driver.OracleDriver";
	//"jdbc:oracle:thin:@10.115.64.111:1526:testora";
	public static final String dbIOTConnectionURL = "jdbc:oracle:thin:@10.201.123.2:1521:billdbiot";
	public static String dbusername = "bhu_abedini";
	public static String dbPassword = "ASDasd12#$";	
	
	//Basic WebService Configuration Details
	public static final String basicUri = "http://10.201.123.1:8881/";
	
	//WebService Configuration Details
	public static final String baseURI = "http://10.201.123.3:5555/";
	public static final String ContentType = "application/json";
	public static final String RequestID = "123456";
	public static final String Username = "OCS_TEST";
	public static final String Password = "ocs_test_2022";
	
	
	//Method Name
	public static final String queryPostPaidBilNEW = "queryPostPaidBilNEW";
	public static final String barOneWay = "barOneWay";
	public static final String packageInquiry = "packageInquiry";
	public static final String offlineRecharge = "offlineRecharge";
	
	
	//Login Page
	public static final String login_username_xpath = "//input[@formcontrolname='username']";
	public static final String login_password_xpath = "//input[@formcontrolname='password']";
	public static final String login_loginBtn_xpath = "//button[@type='submit']";
	
	
	//Home Page
	public static final String home_sitemapBtn_xpath = "//span[@jhitranslate='global.menu.site-map']";
	
	
	
	//SiteMap Page
	public static final String sitemap_Parent1Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[1]";
	public static final String sitemap_Parent2Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[2]";
	public static final String sitemap_Parent3Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[3]";
	public static final String sitemap_Parent4Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[4]";
	public static final String sitemap_Parent5Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[5]";
	public static final String sitemap_Parent6Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[6]";
	public static final String sitemap_Parent7Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[7]";
	public static final String sitemap_Parent8Btn_xpath =   "(//mat-expansion-panel-header[@role='button'])[8]";	
	public static final String sitemap_Child111Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[1]/div/span[1]";
	public static final String sitemap_Child112Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[1]/div/span[2]";
	public static final String sitemap_Child121Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[1]";
	public static final String sitemap_Child122Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[2]";
	public static final String sitemap_Child123Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[3]";
	public static final String sitemap_Child124Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[4]";
	public static final String sitemap_Child125Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[5]";
	public static final String sitemap_Child126Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[6]";
	public static final String sitemap_Child127Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[7]";
	public static final String sitemap_Child128Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[8]";
	public static final String sitemap_Child129Btn_xpath =  "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[9]";
	public static final String sitemap_Child1210Btn_xpath = "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[10]";
	public static final String sitemap_Child1211Btn_xpath = "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[11]";
	public static final String sitemap_Child1212Btn_xpath = "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[12]";
	public static final String sitemap_Child1213Btn_xpath = "(//mat-expansion-panel-header[@role='button'])[1]//ancestor::mat-accordion//div/div//div/div[2]/div/span[13]";
	public static final String sitemap_Child2173Btn_xpath = "(//mat-expansion-panel-header[@role='button'])[2]//ancestor::mat-accordion//div/div//div/div[1]/div/span[7]";
	
		
	
	//PermissionsPage
	
	public static final String Permissions_Code_id = "field_permissionCode";
	public static final String Permissions_Name_id = "field_permissionName";
	public static final String Permissions_Status_id = "field_fkPermissionStatus";
	public static final String Permissions_Product_id = "field_productId";
	
	
	//OfflineRequestsPage
	public static final String OfflineRequest_serviceType_id 		= "field_serviceType";
	public static final String OfflineRequest_subsNumber_id 		= "field_subscriberNumber";
	public static final String OfflineRequest_query_id 				= "list-entity";
	public static final String OfflineRequest_processStartDate_id	= "field_processSartDate";
	public static final String OfflineRequest_processEndDate_id 	= "field_processEndDate";
	public static final String OfflineRequest_usageStartDate_id		= "field_usageStartDate";
	public static final String OfflineRequest_usageEndDate_id 		= "field_usageEndDate";
	
	
	//BalanceInqueryPage
	public static final String BalanceInquery_SubsNumber_id = "subscriberNumber";
	public static final String BalanceInquery_AccountCode_id = "field_accountCode";
	public static final String BalanceInquery_CustomerCode_id = "field_customerCode";
	public static final String BalanceInquery_SearchBtn_xpath = "//button[@type='submit']";
	public static final String BalanceInquery_ClearBtn_xpath = "//jhi-common-search-criteria//div[4]/button[1]";
	public static final String BalanceInquery_InquiryBtn_xpath = "//tbody/tr/td[14]//button";
	public static final String BalanceInquery_InquiryGrid_xpath = "//tbody[@role='rowgroup']";
	
	//ClearCachePage
	public static final String ClearCache_ClearDataDic_xpath = "//jhi-sys-data-clear-cache/div/div[1]//button";
	public static final String ClearCache_ClearBalanceTypes_xpath = "//jhi-sys-data-clear-cache/div/div[2]//button";
	public static final String ClearCache_ClearRce_xpath = "//jhi-sys-data-clear-cache/div/div[3]//button";
	
	
	//BalanceTypePage
	public static final String BalanceType_name_id = "field_balanceTypeName";
	public static final String BalanceType_code_id = "field_balanceTypeCode";
	public static final String BalanceType_description_id = "field_description";
	public static final String BalanceType_Status_id = "field_balanceTypeStatus";
	public static final String BalanceType_allowAdjustment_id = "field_allowAdjustment";
	public static final String BalanceType_maxPrepaiment_id = "field_maxPrepaiment";
	public static final String BalanceType_taxExemption_id = "field_taxExemption";
	public static final String BalanceType_allowAmountTransfer_id = "field_allowAmountTransfer";
	public static final String BalanceType_currencyType_id = "field_fkCurrencyType";
	public static final String BalanceType_feeRestriction_id = "field_feeRestriction";
	public static final String BalanceType_usagePriority_id = "field_usagePriority";
	public static final String BalanceType_creditType_id = "field_creditType";
	public static final String BalanceType_Class_id = "field_balanceTypeClass";
	public static final String BalanceType_unitType_id = "field_unitType";
	public static final String BalanceType_unitPercision_id = "field_unitPercision";
	public static final String BalanceType_chargeCode_id = "field_fkChargeCode";
	public static final String BalanceType_isRechargeable_id = "field_isRechargeable";
	public static final String BalanceType_effectiveTime_id = "field_effectiveTime";
	public static final String BalanceType_expirationTime_id = "field_expirationTime";
	
	
	//BalanceTypeGroupMemeber
	public static final String BtGroupMember_balanceTypeGroup_id = "field_balanceTypeGroup";
	public static final String BtGroupMember_balanceType_id = "field_balanceType";
	public static final String BtGroupMember_viewList_id = "view-entity";
	
	
	//BalanceTypeGroup
	public static final String BtGroup_groupName_id = "field_balanceTypeGroupName";
	public static final String BtGroup_groupCode_id = "field_balanceTypeGroupCode";
	public static final String BtGroup_groupStatus_id = "field_balanceTypeGroupStatus";
	public static final String BtGroup_usagePriority_id = "field_usagePriority";
	public static final String BtGroup_description_id = "field_description";
	public static final String BtGroup_parent_id = "field_parent";
	public static final String BtGroup_effectiveTime_id = "field_effectiveTime";
	public static final String BtGroup_expirationTime_id = "field_expirationTime";
	
	
	//DataDic
	public static final String DataDic_createNew_id  = "jh-create-entity";	
	public static final String DataDic_dataCode_id  = "field_dataCode";
	public static final String DataDic_dataTitle_id  = "field_dataTitle";
	public static final String DataDic_description_id  = "field_dataDescription";
	public static final String DataDic_status_id  = "field_status";
	public static final String DataDic_parentDataCode_id  = "field_parentDataCode";
	public static final String DataDic_back_xpath  = "//jhi-data-dictionary-detail//button[1]";
	public static final String DataDic_edit_xpath  = "//jhi-data-dictionary-detail//button[2]";
	
	
	//DeleteMessagePage
	public static final String Del_delete_xpath = "//mat-dialog-container//button[1]";
	public static final String Del_cancel_xpath = "//mat-dialog-container//button[2]";
	
	
	//SystemMenuPage
	public static final String Systemmenu_name_id = "field_name";
	public static final String Systemmenu_state_id = "field_state";
	public static final String Systemmenu_state2_id = "field_state2";
	public static final String Systemmenu_type_id = "field_type";
	public static final String Systemmenu_icon_id = "field_icon";
	public static final String Systemmenu_selector_id = "field_selector";
	public static final String Systemmenu_description_id = "field_description";
	public static final String Systemmenu_parent_id = "field_parent";
	public static final String Systemmenu_isActive_id = "active";
	
	
	//Refund Configuration	
	public static final String RefundConfig_key_id = "field_parameterKey";
	public static final String RefundConfig_value_id = "field_parameterValue";
	public static final String RefundConfig_cancel_id  = "cancel";
	public static final String RefundConfig_save_id  = "save";
	
	//System Parameter
	public static final String SystemParam_key_id  = "field_parameterKey";
	public static final String SystemParam_value_id  = "field_parameterValue";
	public static final String SystemParam_defaultValue_id  = "field_parameterDefaultValue";
	public static final String SystemParam_status_id  = "field_parameterStatus";
	public static final String SystemParam_description_id  = "field_parameterDescription";
	
	
	//Ocs Grid
	public static final String Grid_body_xpath = "//tbody";
	public static final String Grid_first_xpath = "//ngb-pagination[@role='navigation']//a[@aria-label='First']";
	public static final String Grid_previus_xpath = "//ngb-pagination[@role='navigation']//a[@aria-label='Previous']";
	public static final String Grid_next_xpath = "//ngb-pagination[@role='navigation']//a[@aria-label='Next']";
	public static final String Grid_last_xpath = "//ngb-pagination[@role='navigation']//a[@aria-label='Last']";
	
	//ViewListSection
	public static final String ViewList_searchBox_id = "currentSearch";
	public static final String ViewList_searchBtn_xpath = "//form[@name='searchForm']/div/button[1]";
	public static final String ViewList_resetBtn_xpath = "//form[@name='searchForm']/div/button[2]";
	public static final String ViewList_gridBody_xpath = "//tbody";
	
	
	
	
	
	
	
	
	
	
	
	
};