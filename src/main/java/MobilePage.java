import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobilePage extends BasePage {

    private By sortByButtons = By.cssSelector(".sort-by select");
    private By compareAllButton = By.cssSelector("button[title='Compare']");

    private By sonyPrice = By.cssSelector("#product-price-1");
    private By iphonePrice = By.cssSelector("#product-price-2");
    private By galaxyPrice = By.cssSelector("#product-price-3");

    private By sonyLink = By.cssSelector(".product-name a[title='Sony Xperia']");
    private By iphoneLink = By.cssSelector(".product-name a[title='IPhone']");
    private By galaxyLink = By.cssSelector(".product-name a[title='Samsung Galaxy']");

    private By sonyBuyButton = By.cssSelector("a[title='Xperia']+div .actions [type='button']");
    private By iphoneBuyButton = By.cssSelector("a[title='IPhone']+div .actions [type='button']");
    private By galaxyBuyButton = By.cssSelector("a[title='Samsung Galaxy']+div .actions [type='button']");

    private By sonyCompareButton = By.cssSelector("a[title='Xperia']+div .actions [type='button']+.add-to-links .link-compare");
    private By iphoneCompareButton = By.cssSelector("a[title='IPhone']+div .actions [type='button']+.add-to-links .link-compare");
    private By galaxyCompareButton = By.cssSelector("a[title='Samsung Galaxy']+div .actions [type='button']+.add-to-links .link-compare");

    public MobilePage(WebDriver driver) {
        super(driver);
    }

    public void sortByName() {
        WebElement sortableElement = driver.findElements(sortByButtons).get(0);
        Select select = new Select(sortableElement);
        select.selectByVisibleText("Name");
    }

    public String getPhonePrice(String name) throws NoSuchElementException {
        if (name.equalsIgnoreCase("sony")) {
            return driver.findElement(sonyPrice).getText();
        } else if (name.equalsIgnoreCase("iphone")) {
            return driver.findElement(iphonePrice).getText();
        } else if (name.equalsIgnoreCase("galaxy")) {
            return driver.findElement(galaxyPrice).getText();
        } else {
            throw new NoSuchElementException("Wrong phone name");
        }
    }

    public PhonePage getPhoneLink(String name) {
        if (name.equalsIgnoreCase("sony")) {
            driver.findElement(sonyLink).click();
            return new PhonePage(driver);
        } else if (name.equalsIgnoreCase("iphone")) {
            driver.findElement(iphoneLink).click();
            return new PhonePage(driver);
        } else if (name.equalsIgnoreCase("galaxy")) {
            driver.findElement(galaxyLink).click();
            return new PhonePage(driver);
        } else {
            throw new NoSuchElementException("Wrong phone name");
        }
    }

    public ShoppingCart clickBuyButton(String name) {
        if (name.equalsIgnoreCase("sony")) {
            driver.findElement(sonyBuyButton).click();
            return new ShoppingCart(driver);
        } else if (name.equalsIgnoreCase("iphone")) {
            driver.findElement(iphoneBuyButton).click();
            return new ShoppingCart(driver);
        } else if (name.equalsIgnoreCase("galaxy")) {
            driver.findElement(galaxyBuyButton).click();
            return new ShoppingCart(driver);
        } else {
            throw new NoSuchElementException("Wrong phone name");
        }
    }

    public ComparePopUp compareTwoPhones(String name1, String name2, String name3) {
        addPhoneToCompare(name1);
        addPhoneToCompare(name2);
        addPhoneToCompare(name3);
        driver.findElement(compareAllButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String currentWindow : driver.getWindowHandles()) {
            driver.switchTo().window(currentWindow);
        }
        return new ComparePopUp(driver);
    }

    private void addPhoneToCompare(String name) {
        if (name.equalsIgnoreCase("sony")) {
            driver.findElement(sonyCompareButton).click();
        } else if (name.equalsIgnoreCase("iphone")) {
            driver.findElement(iphoneCompareButton).click();
        } else if (name.equalsIgnoreCase("galaxy")) {
            driver.findElement(galaxyCompareButton).click();
        } else {
            if (!name.isEmpty()) {
                throw new NoSuchElementException("Wrong phone name");
            }
        }
    }
}