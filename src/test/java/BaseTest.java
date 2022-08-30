import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    Properties properties = new Properties();
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) throws IOException {
        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new NoSuchElementException("Wrong browser parameter");
        }

        FileInputStream file = new FileInputStream("src/main/resources/some.properties");
        properties.load(file);
        driver.manage().window().maximize();
    }

    /*@Parameters({"browser"})
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
    }*/

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