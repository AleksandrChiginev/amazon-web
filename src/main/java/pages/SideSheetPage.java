package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideSheetPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(SideSheetPage.class);

    @FindBy(id = "attachSiNoCoverage-announce")
    private WebElement noCoverageButton;

    @FindBy(id = "attach-sidesheet-view-cart-button")
    private WebElement basketButton;

    public SideSheetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SideSheetPage clickOnNoCoverageButton() {
        LOGGER.info("Click no coverage");
        noCoverageButton.click();
        return new SideSheetPage(driver);
    }

    public BasketPage clickOnBasketButton() {
        LOGGER.info("Click basket button");
        basketButton.click();
        return new BasketPage(driver);
    }
}
