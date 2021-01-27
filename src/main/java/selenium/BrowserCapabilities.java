package selenium;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.BrowserType.*;

public class BrowserCapabilities {

    public static Capabilities getCapabilities(Browser browser) {
        switch (browser.getName()) {
            case CHROME:
                return getChromeCapabilities();
            case IE:
                return getIECapabilities();
            case FIREFOX:
            default:
                return getFirefoxCapabilities();
        }
    }

    public static DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors", "--window-size=1920,1080");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    public static DesiredCapabilities getFirefoxCapabilities() {
        return DesiredCapabilities.firefox();
    }

    public static DesiredCapabilities getIECapabilities() {
        return DesiredCapabilities.internetExplorer();
    }
}
