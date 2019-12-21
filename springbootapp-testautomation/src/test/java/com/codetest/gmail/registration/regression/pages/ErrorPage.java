package com.codetest.gmail.registration.regression.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ErrorPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "/html/body/h1")
    private WebElement failureElement;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Register another new user")
    private WebElement registerNewUser;

    public ErrorPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String vaidateFailureCase()
    {
       return failureElement.getText();

    }

    public void registerAnotherNewUser()
    {
        registerNewUser.click();
    }
}
