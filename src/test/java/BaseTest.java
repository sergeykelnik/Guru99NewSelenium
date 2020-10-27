import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    /*@Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equalsIgnoreCase("IE")) {
            System.setProperty("wdm.architecture", "X32");
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else throw new NoSuchElementException("Wrong browser parameter");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }*/

    @Parameters({"browser"})
    @BeforeClass
    public void initiateDriver(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        } else if (browser.equalsIgnoreCase("Opera")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.operaBlink());
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("IE")) {
            System.setProperty("wdm.architecture", "X32");
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /*String USERNAME = "pecebbevse1";
    String AUTOMATE_KEY = "RCm2s4XXRqLTEsCdbfMh";
    String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String OSAndBrowser) throws MalformedURLException {
        if (OSAndBrowser.equalsIgnoreCase("Chrome")) {
            DesiredCapabilities caps = new DesiredCapabilities();

            driver = new RemoteWebDriver(new URL(URL), caps);
        } else if (OSAndBrowser.equalsIgnoreCase("Safari")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } else if (OSAndBrowser.equalsIgnoreCase("Firefox")) {
            DesiredCapabilities caps = new DesiredCapabilities();

            driver = new RemoteWebDriver(new URL(URL), caps);
        } else if (OSAndBrowser.equalsIgnoreCase("IE11")) {
            DesiredCapabilities caps = new DesiredCapabilities();

            driver = new RemoteWebDriver(new URL(URL), caps);
        }
    }*/

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}