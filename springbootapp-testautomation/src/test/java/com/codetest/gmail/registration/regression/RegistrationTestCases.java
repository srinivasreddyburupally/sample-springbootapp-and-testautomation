package com.codetest.gmail.registration.regression;

import com.codetest.gmail.registration.regression.pages.ErrorPage;
import com.codetest.gmail.registration.regression.pages.HomePage;
import com.codetest.gmail.registration.regression.pages.ResultPage;
import com.codetest.gmail.registration.regression.testdata.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegistrationTestCases {

    private static Logger logger = LoggerFactory.getLogger(RegistrationTestCases.class);
    private WebDriver driver;
    private Map<String,String> testData;
    private TestDataProvider testDataProvider;
    private HomePage homePage;
    private ResultPage resultPage;
    private ErrorPage errorPage;

    @BeforeTest
    public void setUp()
    {
        logger.info("Started test setup");
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080");
        testDataProvider=new TestDataProvider();
        homePage=new HomePage(driver);
        resultPage=new ResultPage(driver);
        errorPage=new ErrorPage(driver);
    }

    //+ve test case
    @Test
    public void successful_Registration_Test()
    {
        logger.info("Validating Successful registration");
        testData=testDataProvider.getSuccessfulRegistrationTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");
    }

    //+ve test case
    @Test(dependsOnMethods = { "successful_Registration_Test" })
    public void successful_Registration_RegisterAnotherNewUser()
    {
        logger.info("Verify registration of another new user, after the successful registration of a user ");
        testData=testDataProvider.getSuccessfulRegistrationTestData();

        resultPage.registerAnotherNewUser();

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");
    }

    //+ve test case
    @Test
    public void successful_Registration_BoundaryTest()
    {
        logger.info("Validating Successful registration with boundary test values, like password and confirm password should have minimum 8 characters");
        testData=testDataProvider.getSuccessfulRegistrationBoundaryTest();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");
    }

    //-ve test case
    @Test
    public void userName_CaseSensitivity_UserAlreadyExistsTest()
    {
        logger.info("Validating user already exists scenario by passing same userName with different case");
        testData=testDataProvider.getSuccessfulRegistrationTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");

        resultPage.registerAnotherNewUser();

        testData.put("userName",testData.get("userName").toUpperCase());
        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test
    public void userName_TrimSpaces_UserAlreadyExistsTest()
    {
        logger.info("Validating user already exists scenario by passing same userName with leading and trailing spaces");

        testData=testDataProvider.getSuccessfulRegistrationTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");

        resultPage.registerAnotherNewUser();

        String userName=" "+testData.get("userName").toUpperCase()+" ";
        testData.put("userName",userName);
        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test
    public void all_EmptyFields_NegativeTest()
    {
        logger.info("Validation by passing all fields empty");
        testData=testDataProvider.getEmptyTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test(dependsOnMethods = { "all_EmptyFields_NegativeTest" })
    public void allEmptyFields_NegativeTest_RegisterAnotherNewUser()
    {
        logger.info("Verify registration of another new user, from error page");

        testData=testDataProvider.getSuccessfulRegistrationTestData();

        errorPage.registerAnotherNewUser();

        homePage.submitForm(testData);

        Assert.assertEquals(resultPage.vaidateSuccessfulCase(),"Result");
    }

    //-ve test case
    @Test
    public void Invalid_FirstName_Test()
    {
        logger.info("Validation of firstName field by passing invalid data");

        testData=testDataProvider.getInvalidFirstNameTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test
    public void Invalid_LastName_Test()
    {
        logger.info("Validation of lastName field by passing invalid data");

        testData=testDataProvider.getInvalidLastNameTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test
    public void Invalid_UserName_Test()
    {
        logger.info("Validation of userName field by passing invalid data");

        testData=testDataProvider.getInvalidUserNameTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    //-ve test case
    @Test
    public void password_Mismatch_Test()
    {
        logger.info("Validation of password and confirm password mismatch scenario");

        testData=testDataProvider.getPasswordMismatchTestData();

        driver.navigate().to("http://localhost:8080");

        homePage.submitForm(testData);

        Assert.assertEquals(errorPage.vaidateFailureCase(),"Error");
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
