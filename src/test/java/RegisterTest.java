import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.api.register.GenerateUser;
import ru.practikum.api.steps.UserSteps;
import ru.practikum.pageobjects.LoginPageObject;
import ru.practikum.pageobjects.MainPageObject;
import ru.practikum.pageobjects.RegisterPageObject;
import ru.practikum.webdriverfactory.WebDriverFactory;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;
import static ru.practikum.constants.StellarBurgerStaticConstants.*;

public class RegisterTest {
    private UserSteps userSteps = new UserSteps();
    private MainPageObject mainPageObject;
    private RegisterPageObject registerPageObject;
    private LoginPageObject loginPageObject;

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
        registerPageObject = new RegisterPageObject(driver);
        loginPageObject = new LoginPageObject(driver);

    }

    @After
    public void teardown() {
        driver.quit();

        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    public void registerUserTest() {
        mainPageObject.clickLoginButton();
        loginPageObject.clickRegisterButtonLoginPage();
        registerPageObject.enterRegisterData(generateUser);
        registerPageObject.clickRegisterButtonRegisterPage();

        accessToken = userSteps
                .loginUserLogopass(generateUser.getEmail(), generateUser.getPassword())
                .statusCode(200)
                .body("success", is(true))
                .extract().body().path("accessToken");
    }

    @Test
    public void registerUserIncorrectPasswordTest() {
        GenerateUser generateUserIncorrectPassword = new GenerateUser(generateUser.getName(), "nul", generateUser.getEmail());

        mainPageObject.clickLoginButton();
        loginPageObject.clickRegisterButtonLoginPage();
        registerPageObject.enterRegisterData(generateUserIncorrectPassword);
        Assert.assertTrue(registerPageObject.checkIfPasswordErrorIsVisible());

    }
}
