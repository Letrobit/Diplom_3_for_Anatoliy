package ru.practikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class ProfilePageObject {
    private final WebDriver driver;

    private final By lichniyKabinetText = By.xpath("//p[@class='Account_text__fZAIn text text_type_main-default']");
    private final By lichniyKabinetInputFields = By.xpath("//ul[@class='Profile_profileList__3vTor']");
    private final By lichniyKabinetLogoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By lichniyKabinetConstructorButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By lichniyKabinetLogoutButton = By.xpath("//button[.='Выход']");

    public ProfilePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Checks if text on profile page is visible to see if page loaded")
    public boolean profileTextIsVisible() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(lichniyKabinetText));
        WebElement element = driver.findElement(lichniyKabinetText);
        return element.isDisplayed();
    }

    @Step("Checks if personal info fields on profile page are visible to see if page loaded")
    public boolean profileInputFieldIsVisible() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(lichniyKabinetInputFields));
        WebElement element = driver.findElement(lichniyKabinetInputFields);
        return element.isDisplayed();
    }

    @Step("Click stellar burgers logo on profile page")
    public void lichniyKabinetLogoClick() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(lichniyKabinetLogoButton));
        WebElement element = driver.findElement(lichniyKabinetLogoButton);
        element.click();
    }

    @Step("Click constructor button on profile page")
    public void lichniyKabinetConstructorButtonClick() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(lichniyKabinetConstructorButton));
        WebElement element = driver.findElement(lichniyKabinetConstructorButton);
        element.click();
    }

    @Step("Click logout button on profile page")
    public void lichniyKabinetLogoutButtonButtonClick() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(lichniyKabinetLogoutButton));
        WebElement element = driver.findElement(lichniyKabinetLogoutButton);
        element.click();
    }
}
