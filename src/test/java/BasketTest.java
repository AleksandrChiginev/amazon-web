import listener.MyListener;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasketPage;

@Listeners({MyListener.class})
public class BasketTest extends BaseTest {
    private final static Logger LOGGER = Logger.getLogger(BasketTest.class);

    @BeforeClass
    public void setUp() {
        login();
    }

    @Test(description = "Check main basket functionality")
    @Parameters({"model", "capacity", "color"})
    public void checkBasketFunctionality(String model, String capacity, String color) {
        BasketPage basketPage = mainPage.getHeaderElement()
                .searchItem(String.format("%s %s %s", model, capacity, color))
                .chooseCheapestItem(model, capacity, color)
                .clickOnAddItemButton()
                .clickOnNoCoverageButton()
                .clickOnBasketButton();

        LOGGER.info("Check basket title");
        Assert.assertTrue(basketPage.waitBasketTitle("Shopping Basket"), "Basket title is wrong!");
        LOGGER.info("Check basket size");
        Assert.assertEquals(basketPage.getBasketSize(), 1, "Basket contains wrong number of items!");
        checkItemNameInBasket(basketPage.getFirstItemName(), model, capacity, color);

        basketPage.clickOnDeleteButton();

        LOGGER.info("Check empty basket title");
        Assert.assertTrue(basketPage.waitBasketTitle("Your Amazon Basket is empty."), "Empty basket title is wrong!");
        LOGGER.info("Check basket size");
        Assert.assertEquals(basketPage.getBasketSize(), 0, "Basket contains wrong number of items!");
    }

    private void checkItemNameInBasket(String actualName, String... expectedParameters) {
        if (expectedParameters.length != 0) {
            for (String expectedParameter : expectedParameters) {
                LOGGER.info("Check item in basket contains parameter " + expectedParameter);
                Assert.assertTrue(actualName.toLowerCase().contains(expectedParameter.toLowerCase()),
                        String.format("Basket contains wrong item! Actual name: %s. Expected parameter: %s.", actualName, expectedParameter));
            }
        }
    }

    @AfterClass
    public void signOut() {
        logout();
    }
}