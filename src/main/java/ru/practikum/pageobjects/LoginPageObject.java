package ru.practikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.api.register.GenerateUser;

import static java.time.Duration.ofSeconds;

public class LoginPageObject {
    private final WebDriver driver;
    private final By loginPageRegisterButton = By.xpath("//a[.='Зарегистрироваться']");
    private final By loginPageEmailField = By.xpath(".//label[.='Email']/parent::div/input");
    private final By loginPagePasswordField = By.xpath(".//label[.='Пароль']/parent::div/input");
    private final By registerPageRegisterButton = By.xpath("//button[.='Зарегистрироваться']");
    private final By loginPageLoginButton = By.xpath("//button[.='Войти']");
    private final By createOrderButton = By.xpath("//button[.='Оформить заказ']");
    private final By restorePasswordLoginPage = By.xpath("//a[.='Восстановить пароль']");
    private final By logoStellarBurger = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");


    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click register button on login page")
    public void clickRegisterButtonLoginPage() {
        WebElement element = driver.findElement(loginPageRegisterButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(registerPageRegisterButton));
    }

    @Step("Enter user info on login page")
    public void loginToAccount(GenerateUser generateUser) {
        WebElement emailInput = driver.findElement(loginPageEmailField);
        emailInput.sendKeys(generateUser.getEmail());

        WebElement passwordInput = driver.findElement(loginPagePasswordField);
        passwordInput.sendKeys(generateUser.getPassword());

        WebElement loginButton = driver.findElement(loginPageLoginButton);
        loginButton.click();

        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(createOrderButton));
    }

    @Step("Click restore password button on login page")
    public void clickRestorePasswordButton() {
        WebElement element = driver.findElement(restorePasswordLoginPage);
        element.click();
    }

    @Step("Click stellar burger logo on login page")
    public void mainLogoLoginPagelick() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(logoStellarBurger));
        WebElement element = driver.findElement(logoStellarBurger);
        element.click();
    }


}
