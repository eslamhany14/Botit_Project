package org.example.pagess;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(name="email")
    private WebElement usernameInput;

    @FindBy(name="password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@class='button-input newd']")
    private WebElement loginButton;


    // Constructor to initialize the WebDriver

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void Login(String Name , String Pass) throws InterruptedException {
        usernameInput.sendKeys(Name);
        passwordInput.sendKeys(Pass);
        loginButton.click();
        Thread.sleep(3000);
    }



}

