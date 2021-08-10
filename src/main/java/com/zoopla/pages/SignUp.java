package com.zoopla.pages;

import com.zoopla.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUp extends BaseClass {

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/div/div/div/header/div/div/div[1]/div[2]/ul/li[5]/a")
    WebElement signIn;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register_Button;

    @FindBy(xpath = "//input[@id='input-email-address']")
    WebElement input_email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement input_pwd;

    @FindBy(xpath = "//select[@id='situation-dropdown']")
    WebElement drop_down;

    @FindBy(xpath = "//option[@value='curious_having_a_look']")
    WebElement selector;

    @FindBy(xpath = "//label[normalize-space()='No, thanks']")
    WebElement no_thanks_opt;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    WebElement register;

    public SignUp(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void sign_up() throws InterruptedException {
        signIn.click();
        Thread.sleep(2000);
        register_Button.click();
        Thread.sleep(2000);
        input_email.sendKeys("dinesh15127019@gmail.com");
        Thread.sleep(1000);
        input_pwd.sendKeys("Dinnu@247");
        Thread.sleep(1000);
        drop_down.click();
        Thread.sleep(2000);
        Select select = new Select(drop_down);
        select.selectByVisibleText("I am interested in property");
        Thread.sleep(200);
        no_thanks_opt.click();
        Thread.sleep(1000);
        register.click();
        Thread.sleep(1000);
    }
}
