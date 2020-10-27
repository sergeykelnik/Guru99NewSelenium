import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tests extends BaseTest {

    HomePage homePage;
    MobilePage mobilePage;
    PhonePage phonePage;
    ShoppingCart shoppingCart;
    ComparePopUp comparePopUp;

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("http://live.demoguru99.com/index.php/");
    }

    @AfterMethod
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File("target/screenshots/screenshot_" + timestamp + ".png"));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    //@Ignoreh
    @Test(priority = 1)
    public void day1Test() {
        homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getPageTitle(), "Home page");
        mobilePage = homePage.clickMobileLink();
        softAssert.assertEquals(mobilePage.getPageTitle(), "Mobile");
        mobilePage.sortByName();
        softAssert.assertEquals(mobilePage.getPhoneList().get(0).getText(), "IPHONE");
        softAssert.assertEquals(mobilePage.getPhoneList().get(1).getText(), "SAMSUNG GALAXY");
        softAssert.assertEquals(mobilePage.getPhoneList().get(2).getText(), "SONY XPERIA");
        softAssert.assertAll();
    }

    //@Ignore
    @Test(priority = 2)
    public void day2Test() {
        homePage = new HomePage(driver);
        mobilePage = homePage.clickMobileLink();
        String priceOnMobilePage = mobilePage.getPhonePrice("iphone");
        phonePage = mobilePage.getPhoneLink("iphone");
        String priceOnPhonePage = phonePage.getPhonePrice();
        Assert.assertEquals(priceOnMobilePage, priceOnPhonePage);
        System.out.println(priceOnMobilePage + " equals to " + priceOnPhonePage);
    }

    //@Ignore
    @Test(priority = 3)
    public void day3Test() {
        homePage = new HomePage(driver);
        mobilePage = homePage.clickMobileLink();
        shoppingCart = mobilePage.clickBuyButton("sony");
        shoppingCart.updateQtyTo("1000");
        SoftAssert softAssert = new SoftAssert();
        String errorMessageTop = shoppingCart.getErrorMessageTop();
        String errorMessageItem = shoppingCart.getErrorMessageItem();
        softAssert.assertEquals(errorMessageTop, "Some of the products cannot be ordered in requested quantity.");
        softAssert.assertEquals(errorMessageItem, "* The maximum quantity allowed for purchase is 500.");
        shoppingCart.emptyCart();
        String pageHeaderMessage = shoppingCart.getPageHeaderMessage();
        softAssert.assertEquals(pageHeaderMessage, "SHOPPING CART IS EMPTY");
        softAssert.assertAll();
    }

    //@Ignore
    @Test(priority = 4)
    public void day4Test() {
        homePage = new HomePage(driver);
        mobilePage = homePage.clickMobileLink();
        comparePopUp = mobilePage.compareTwoPhones("sony", "iphone", "");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(comparePopUp.getPageHeaderMessage(), "COMPARE PRODUCTS");
        softAssert.assertEquals(comparePopUp.getPhoneList().get(0).getText(), "SONY XPERIA");
        softAssert.assertEquals(comparePopUp.getPhoneList().get(1).getText(), "IPHONE");
        softAssert.assertAll();
    }
}
