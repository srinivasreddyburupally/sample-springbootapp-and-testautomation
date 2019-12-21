package com.codetest.gmail.registration.regression.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class HomePage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.ID, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.ID, using = "userName")
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(how = How.XPATH, using = "html/body/form/p[6]/input[1]")
    private WebElement submit;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void submitForm(Map<String,String> testData)
    {
        firstName.sendKeys(testData.get("firstName"));
        lastName.sendKeys(testData.get("lastName"));
        userName.sendKeys(testData.get("userName"));
        password.sendKeys(testData.get("password"));
        confirmPassword.sendKeys(testData.get("confirmPassword"));
        submit.click();
    }
}
