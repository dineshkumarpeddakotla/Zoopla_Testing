/*
 *Purpose : Class is implemented to execute the methods before and after test
 *               @BeforeTest is used to execute the method before execute actual test
 *               @AfterTest is used to execute the method after execute actual test
 *               @Parameter is used to provide the value for browser name
 *               @BeforeClass is used to execute the method before class
 *               @AfterSuite is to execute the method after suite
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */
package com.zoopla.base;

import com.zoopla.constants.IConstants;
import com.zoopla.utility.CrossBrowser;
import com.zoopla.utility.Log;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    //driver variable is declared
    public static WebDriver driver;

    @BeforeClass
    @Description("check the internet connection before running the class")
    public void checkConnection() {
        URL url;
        URLConnection connection;
        try {
            url = new URL("http://www.google.com");
            connection = url.openConnection();
            connection.connect();
            Log.info("Internet is connected");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("Internet is not connected");
        }
    }

    /**
     * setUp method is used to open browser and navigate to url
     * browser is opened based on parameter value
     */
    @Description("setUp method is used to open browser and navigate to url before test")
    @Parameters("browserName")
    @BeforeTest//execute before test
    public void setUp(@Optional("chrome") String browserName) {

        Log.info("select browser");
        driver = CrossBrowser.selectDriver(browserName);
        Log.info("navigate to url :" + IConstants.URL);
        driver.get(IConstants.URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    //tearDown method closes all connections
    @AfterTest//execute after test
    @Description("tearDown method closes all connections and browser")
    public void tearDown() {
        driver.quit();
    }

//    @AfterSuite
//    @Description("send reports to mail after the suite")
//    public void sendReports() {
//        MailUtil.sendMail();
//        Log.info("send reports to email");
//    }
}
