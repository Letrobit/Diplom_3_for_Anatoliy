import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.api.register.GenerateUser;
import ru.practikum.api.steps.UserSteps;
import ru.practikum.pageobjects.*;
import ru.practikum.webdriverfactory.WebDriverFactory;

import static io.restassured.RestAssured.baseURI;
import static ru.practikum.constants.StellarBurgerStaticConstants.STELLAR_BURGER_URI;
import static ru.practikum.constants.StellarBurgerStaticConstants.STELLAR_BURGER_WEBSITE_URL;

public class LogoutTest {
    private UserSteps userSteps = new UserSteps();
    private MainPageObject mainPageObject;
    private ProfilePageObject profilePageObject;
    private LoginPageObject loginPageObject;
    private RegisterPageObject registerPageObject;
    private RestorePasswordPageObject restorePasswordPageObject;
    String accessToken;
    private GenerateUser generateUser;
    private WebDriver driver;

    @Before
    public void setup() {
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        driver = WebDriverFactory.getDriver();
        driver.get(STELLAR_BURGER_WEBSITE_URL);
        baseURI = STELLAR_BURGER_URI;
        generateUser = new GenerateUser();
        mainPageObject = new MainPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        profilePageObject = new ProfilePageObject(driver);

        accessToken = userSteps
                .createUser(generateUser)
                .extract().body().path("accessToken");
    }

    @After
    public void teardown() {
        driver.quit();

        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    public void logoutUserTest() {
        mainPageObject.clickLoginButton();
        loginPageObject.loginToAccount(generateUser);
        mainPageObject.lichniyKabinetClick();
        profilePageObject.lichniyKabinetLogoutButtonButtonClick();
        loginPageObject.mainLogoLoginPagelick();
        Assert.assertTrue(mainPageObject.LoginButtonIsVisible());
    }
}
