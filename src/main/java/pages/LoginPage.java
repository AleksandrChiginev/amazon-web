package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(LoginPage.class);

    @FindBy(id = "ap_email")
    private WebElement emailInput;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterEmail(String email) {
        LOGGER.info("Enter e-mail");
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LOGGER.info("Enter password");
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickOnContinueButton() {
        LOGGER.info("Click continue");
        continueButton.click();
        return this;
    }

    public MainPage clickOnSignInButton() {
        LOGGER.info("Click sign in");
        signInButton.click();
        return new MainPage(driver);
    }
}
