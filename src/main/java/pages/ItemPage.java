package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(ItemPage.class);
    Header headerElement;

    @FindBy(id = "add-to-cart-button")
    private WebElement addItemButton;

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public SideSheetPage clickOnAddItemButton() {
        LOGGER.info("Click add item");
        addItemButton.click();
        return new SideSheetPage(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
    }
}
