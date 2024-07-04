package ru.practikum.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class MainPageObject {
    private final WebDriver driver;
    private final By mainPageLoginInAccountButton = By.xpath("//button[.='Войти в аккаунт']");
    private final By loginPageRegisterButton = By.xpath("//a[.='Зарегистрироваться']");
    private final By lichniyKabinetButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX'][.='Личный Кабинет']");
    @Getter
    private final By lichniyKabinetText = By.xpath("//p[@class='Account_text__fZAIn text text_type_main-default']");
    private final By createOrderButton = By.xpath("//button[.='Оформить заказ']");

    //Блок конструктор бургера
    private final By constructorBlock = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']");
    private final By sauceSubdivisionButton = By.xpath("//div[.='Соусы']");
    private final By fillingsSubdivisionButton = By.xpath("//div[.='Начинки']");
    private final By bunsSubdivisionButton = By.xpath("//div[.='Булки']");
    private final By currentlyOpenConstructorTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");
    private final By bunsTabOpenLocator = By.xpath("//div[@style='display: flex;']/div[1]");
    private final By sauceTabOpenLocator = By.xpath("//div[@style='display: flex;']/div[2]");
    private final By fillingTabOpenLocator = By.xpath("//div[@style='display: flex;']/div[3]");

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click login button on main page")
    public void clickLoginButton() {
        WebElement element = driver.findElement(mainPageLoginInAccountButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(loginPageRegisterButton));
    }

    @Step("Check if login button on main page is visible (returns true/false)")
    public boolean LoginButtonIsVisible() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPageLoginInAccountButton));
        WebElement element = driver.findElement(mainPageLoginInAccountButton);
        return element.isDisplayed();
    }

    @Step("Click profile button on main page")
    public void lichniyKabinetClick() {
        WebElement element = driver.findElement(lichniyKabinetButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(2));
    }

    @Step("Checks if create order button is visible on main page")
    public boolean createOrderButtonIsVisible() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(createOrderButton));
        WebElement element = driver.findElement(createOrderButton);
        return element.isDisplayed();
    }

    @Step("Checks if order constructor is visible on main page")
    public boolean constructorBlockIsVisible() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(constructorBlock));
        WebElement element = driver.findElement(constructorBlock);
        return element.isDisplayed();
    }

    @Step("Click on fillings button in constructor on main page")
    public void fillingsSubdivisionButtonClick() {
        WebElement element = driver.findElement(fillingsSubdivisionButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(fillingTabOpenLocator));
    }

    @Step("Click on sauce button in constructor on main page")
    public void sauceSubdivisionButtonClick() {
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(sauceTabOpenLocator));
        WebElement element = driver.findElement(sauceSubdivisionButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(sauceTabOpenLocator));
    }

    @Step("Click on buns button in constructor on main page")
    public void bunsSubdivisionButtonClick() {
        WebElement element = driver.findElement(bunsSubdivisionButton);
        element.click();
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bunsTabOpenLocator));
    }
//Неоптимальный код
    /*
    @Step("Checks if current open tab in constructor is buns and is visible on main page")
    public boolean checkIfCurrentTabIsBuns()  {
        //Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(bunsTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        WebElement element = driver.findElement(currentlyOpenConstructorTab);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.attributeContains(bunsTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        return element.getText().equals("Булки");
    }

    @Step("Checks if current open tab in constructor is sauce and is visible on main page")
    public boolean checkIfCurrentTabIsSauce()  {
        //Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(sauceTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        WebElement element = driver.findElement(currentlyOpenConstructorTab);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.attributeContains(sauceTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        return element.getText().equals("Соусы");
    }

    @Step("Checks if current open tab in constructor is filling and is visible on main page")
    public boolean checkIfCurrentTabIsFilling() {
        //Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(fillingTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        WebElement element = driver.findElement(currentlyOpenConstructorTab);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(fillingTabOpenLocator, "class", "tab_tab_type_current__2BEPc"));
        return element.getText().equals("Начинки");
    }
    */

    @Step("Checks if current open tab in constructor is visible on main page")
    public boolean checkIfCurrentTabHasExpectedText(String expectedIngredient) {
        WebElement element = driver.findElement(currentlyOpenConstructorTab);
        System.out.println(element.getText() + " - сравниваем с - " + expectedIngredient);
        return element.getText().equals(expectedIngredient);
    }
}
