import listener.MyListener;
import model.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.MainPage;
import ru.stqa.selenium.factory.WebDriverPool;
import selenium.Browser;
import selenium.BrowserCapabilities;
import selenium.PropertyLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners({MyListener.class})
public class BaseTest {
    private final static Logger LOGGER = Logger.getLogger(BaseTest.class);
    private Browser browser = new Browser();
    private WebDriver driver;
    private String url;
    protected MainPage mainPage;

    @BeforeSuite
    public void initData(ITestContext iTestContext) throws MalformedURLException {
        LOGGER.info("Set browser from properties");
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

        // get capabilities depends on browser
        Capabilities capabilities = BrowserCapabilities.getCapabilities(browser);

        url = PropertyLoader.loadProperty("url");

        LOGGER.info("Run driver via WebDriverFactory");
        // if url contains hub - run driver on remote machine for multithreading
        driver = url.contains("hub") ? WebDriverPool.DEFAULT.getDriver(new URL(url), capabilities) :
                WebDriverPool.DEFAULT.getDriver(capabilities);
        // add implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        // add driver attribute
        iTestContext.setAttribute("webdriver", driver);

        LOGGER.info("Open " + url);
        driver.get(url);
        mainPage = new MainPage(driver);
    }

    // default user login with user from properties
    public void login() {
        User user = new User(PropertyLoader.loadProperty("user.mail"),
                PropertyLoader.loadProperty("password"));
        login(user);
    }

    // user login method depends on user
    public void login(User user) {
        mainPage.getHeaderElement()
                .clickOnAccountButton()
                .enterEmail(user.getUserMail())
                .clickOnContinueButton()
                .enterPassword(user.getPassword())
                .clickOnSignInButton();
    }

    public void logout() {
        mainPage.getHeaderElement()
                .clickOnSignOutButton();
    }

    @AfterSuite
    public void stop() {
        // stop browser
        WebDriverPool.DEFAULT.dismissAll();
    }
}
