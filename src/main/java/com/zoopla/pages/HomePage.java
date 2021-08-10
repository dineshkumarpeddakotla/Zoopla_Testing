package com.zoopla.pages;

import com.zoopla.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseClass {

    @FindBy(xpath = "//a[normalize-space()='To rent']")
    WebElement ToRent;

    @FindBy(xpath = "//input[@id='search-input-location']")
    WebElement search_TextBox;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//a[@data-testid = 'listing-details-link']")
    List<WebElement> propertiesList;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public String search_text() throws InterruptedException {
        ToRent.click();
        Thread.sleep(3000);
        search_TextBox.sendKeys("LONDON");
        Thread.sleep(1000);
        searchButton.click();
        Thread.sleep(2000);
        return driver.getTitle();
    }
}
