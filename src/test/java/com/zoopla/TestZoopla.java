package com.zoopla;

import com.zoopla.base.BaseClass;
import com.zoopla.pages.AgentsPage;
import com.zoopla.pages.HomePage;
import com.zoopla.pages.Login;
import com.zoopla.pages.Properties;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestZoopla extends BaseClass {

    @Test
    public void loginInto_Application_And_Search_ForA_Property() throws InterruptedException {
        Login login = new Login(driver);
        login.login_with_valid_credentials();
        HomePage homePage = new HomePage(driver);
        homePage.search_text();
        Properties properties = new Properties(driver);
        properties.propertiesList();
        AgentsPage agentsPage = new AgentsPage(driver);
        boolean bool = agentsPage.checkAll_Properties_Belongs_ToThat_Agent();

        Assert.assertTrue(bool);
    }
}
