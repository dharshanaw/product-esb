package org.wso2.carbon.esb.ui.test.endpoints;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.extensions.selenium.BrowserManager;
import org.wso2.esb.integration.common.utils.ESBIntegrationUITest;

public class ESBJAVA3027_HttpMethodUIValidationTestCase extends ESBIntegrationUITest {
    private Selenium selenium;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.init();
        WebDriver driver = BrowserManager.getWebDriver();
        driver.get(getLoginURL());
        String baseUrl = getLoginURL();
        selenium = new WebDriverBackedSelenium(driver, baseUrl);

    }

    @Test(groups = "wso2.esb", description = "Verify that endpoints page renders when an invalid dynamic endpoint is present.")
    public void testEndpointsList() throws Exception {
        selenium.open("/carbon/admin/login.jsp");
        selenium.type("id=txtUserName", userInfo.getUserName());
        selenium.type("id=txtPassword", userInfo.getPassword());
        selenium.click("css=input.button");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Endpoints");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Add Endpoint");
        selenium.click("link=HTTP Endpoint");
        selenium.waitForPageToLoad("30000");
        selenium.type("id=endpointName", "testendpoint1");
        selenium.type("id=uriTemplate", "http:\\\\www.google.com");
        selenium.select("name=httpMethod", "label=POST");
        selenium.click("name=save");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Endpoints");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Edit");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Switch to source view");
        selenium.waitForPageToLoad("30000");
        selenium.selectFrame("frame_xmlPay");

        selenium.click("id=textarea");
        Assert.assertTrue(selenium.isTextPresent("post"));


    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

        selenium.close();

    }
}
