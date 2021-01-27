package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(BasketPage.class);
    Header headerElement;

    @FindBy(xpath = "//input[@data-action='delete']")
    private WebElement deleteButton;

    public BasketPage clickOnDeleteButton() {
        LOGGER.info("Delete item");
        deleteButton.click();
        return this;
    }

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
    }
}
