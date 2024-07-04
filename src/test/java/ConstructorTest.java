import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.pageobjects.MainPageObject;
import ru.practikum.webdriverfactory.WebDriverFactory;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static ru.practikum.constants.StellarBurgerStaticConstants.STELLAR_BURGER_WEBSITE_URL;

public class ConstructorTest {
    private MainPageObject mainPageObject;
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.get(STELLAR_BURGER_WEBSITE_URL);
        mainPageObject = new MainPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

 /*
   @Test
    public void sauceConstructorSubdivisionTest(){
        mainPageObject.sauceSubdivisionButtonClick();

        Assert.assertTrue(mainPageObject.checkIfCurrentTabIsSauce());
    }

    @Test
    public void fillingConstructorSubdivisionTest()  {
        mainPageObject.fillingsSubdivisionButtonClick();

        Assert.assertTrue(mainPageObject.checkIfCurrentTabIsFilling());
    }

    @Test
    public void bunsConstructorSubdivisionTest(){
        mainPageObject.sauceSubdivisionButtonClick();
        mainPageObject.bunsSubdivisionButtonClick();

        Assert.assertTrue(mainPageObject.checkIfCurrentTabIsBuns());
    }
    */

@Test
public void sauceConstructorSubdivisionTest(){
    mainPageObject.sauceSubdivisionButtonClick();

    String expectedText = "Соусы";

    Assert.assertTrue(mainPageObject.checkIfCurrentTabHasExpectedText(expectedText));
}

    @Test
    public void fillingConstructorSubdivisionTest()  {
        mainPageObject.fillingsSubdivisionButtonClick();

        String expectedText = "Начинки";

        Assert.assertTrue(mainPageObject.checkIfCurrentTabHasExpectedText(expectedText));
    }

    @Test
    public void bunsConstructorSubdivisionTest(){
        mainPageObject.sauceSubdivisionButtonClick();
        mainPageObject.bunsSubdivisionButtonClick();

        String expectedText = "Булки";

        Assert.assertTrue(mainPageObject.checkIfCurrentTabHasExpectedText(expectedText));
    }
}
