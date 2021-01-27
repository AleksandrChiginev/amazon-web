package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(MainPage.class);
    Header headerElement;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
    }
}
