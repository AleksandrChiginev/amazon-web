package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(BasketPage.class);
    Header headerElement;

    @FindBy(xpath = "//*[@id='sc-active-cart']//h1")
    private WebElement basketTitle;

    @FindBy(xpath = "//*[@data-itemtype='active']")
    private List<WebElement> basketItems;

    @FindBy(xpath = "//input[@data-action='delete']")
    private WebElement deleteButton;

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
    }

    public BasketPage clickOnDeleteButton() {
        LOGGER.info("Delete item");
        deleteButton.click();
        return this;
    }

    public int getBasketSize() {
        return basketItems.size();
    }

    public String getFirstItemName() {
        return basketItems.get(0).getText();
    }

    public boolean waitBasketTitle(String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(basketTitle, text));
    }
}
