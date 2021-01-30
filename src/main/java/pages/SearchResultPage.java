package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SearchResultPage extends BasePage {
    private final static Logger LOGGER = Logger.getLogger(SearchResultPage.class);
    Header headerElement;

    @FindBy(xpath = "//*[@data-component-type='s-search-result']")
    private List<WebElement> searchResultItems;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerElement = new Header(driver);
    }

    public ItemPage chooseCheapestItem(String... searchParameters) {
        LOGGER.info("Choose cheapest item");
        Optional<WebElement> cheapestItem = searchResultItems.stream()
                .filter(e -> checkParameters(e, searchParameters) &&        // filter by search parameters
                        !e.findElements(By.className("a-price")).isEmpty()) // and by empty price
                .min(Comparator.comparingInt(e -> Integer.parseInt(         // find minimum price. I have to use replaceAll and parsInt for correct comparing
                        e.findElement(By.className("a-price")).getText().replaceAll("\\D+", ""))));
        if (!cheapestItem.isPresent()) Assert.fail("There is no searched item!");
        cheapestItem.get().findElement(By.xpath(".//a[contains(@class, 'text-normal')]")).click();
        return new ItemPage(driver);
    }

    public Header getHeaderElement() {
        return headerElement;
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
