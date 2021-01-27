import listener.MyListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners({MyListener.class})
public class BasketTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        login();
    }

    @Test(description = "Check main basket functionality")
    @Parameters({"model", "capacity", "color"})
    public void checkBasketFunctionality(String model, String capacity, String color) {
        mainPage.getHeaderElement()
                .searchItem(String.format("%s %s %s", model, capacity, color))
                .chooseCheapestItem(model, capacity, color)
                .clickOnAddItemButton()
                .clickOnNoCoverageButton()
                .clickOnBasketButton()
                .clickOnDeleteButton()
                .getHeaderElement()
                .clickOnSignOutButton();
    }

}
