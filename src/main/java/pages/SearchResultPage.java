package pages;

import model.enums.TypesOfSort;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class SearchResultPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(SearchResultPage.class);
    Header headerElement;

    @FindBy(xpath = "//*[@data-component-type='s-search-result']")
    private List<WebElement> searchResultItems;

    @FindBy(id = "s-result-sort-select")
    private WebElement chooseSortButton;

    @FindBy(xpath = "//li[@class='a-selected']//following-sibling::li")
    private WebElement nextPageButton;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public ItemPage chooseCheapestItem(String... searchParameters) {
        LOGGER.info("Choose cheapest item");
        sortByLowPrice();
        findNeededItem(searchParameters).findElement(By.xpath(".//a[contains(@class, 'text')]")).click();
        return new ItemPage(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
    }

    public SearchResultPage sortByLowPrice() {
        LOGGER.info("Sort items by price");
        Select select = new Select(chooseSortButton);
        select.selectByVisibleText(TypesOfSort.LOW_PRICE.value);
        return this;
    }

    private WebElement findNeededItem(String... searchParameters) {
        LOGGER.info("Try to find needed item");
        WebElement el = wait.until(ExpectedConditions.visibilityOfAllElements(searchResultItems)).stream()
                .filter(e -> checkParameters(e, searchParameters))
                .findFirst()
                .orElse(null);
        if (el == null) {
            if (!nextPageButton.getAttribute("class").contains("disabled")) {
                LOGGER.info("Go to the page " + nextPageButton.getText());
                nextPageButton.click();
                el = findNeededItem(searchParameters);
            } else {
                Assert.fail("There is no searched item!");
            }
        }
        return el;
    }

    private boolean checkParameters(WebElement el, String... searchParameters) {
        if (searchParameters.length != 0) {
            for (String searchParameter : searchParameters) {
                if (!el.getText().toLowerCase().contains(searchParameter.toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }
}
