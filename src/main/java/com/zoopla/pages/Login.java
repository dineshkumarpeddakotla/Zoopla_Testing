package com.zoopla.pages;

import com.zoopla.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BaseClass {

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/div/div/div/header/div/div/div[1]/div[2]/ul/li[5]/a")
    WebElement signIn;

    @FindBy(id = "input-email-address")
    WebElement email;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    WebElement signIn_Button;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String login_with_valid_credentials() throws InterruptedException {
        signIn.click();
        Thread.sleep(1000);
        email.sendKeys("dinesh15127019@gmail.com");
        Thread.sleep(2000);
        password.sendKeys("Dinnu@247");
        Thread.sleep(2000);
        signIn_Button.click();
        Thread.sleep(3000);
        return driver.getTitle();
    }
}
