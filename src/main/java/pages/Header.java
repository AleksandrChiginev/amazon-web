package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(Header.class);
    private Actions actions;

    @FindBy(id = "nav-link-accountList")
    private WebElement accountButton;

    @FindBy(id = "nav-item-signout")
    private WebElement signOutButton;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBoxInput;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public LoginPage clickOnAccountButton() {
        LOGGER.info("Click account button");
        accountButton.click();
        return new LoginPage(driver);
    }

    public SearchResultPage searchItem(String searchText) {
        LOGGER.info("Search: " + searchText);
        searchBoxInput.sendKeys(searchText);
        searchButton.click();
        return new SearchResultPage(driver);
    }

    public LoginPage clickOnSignOutButton() {
        LOGGER.info("Click sign out");
        actions.moveToElement(accountButton)
                .click(signOutButton)
                .build().perform();
        return new LoginPage(driver);
    }

}
