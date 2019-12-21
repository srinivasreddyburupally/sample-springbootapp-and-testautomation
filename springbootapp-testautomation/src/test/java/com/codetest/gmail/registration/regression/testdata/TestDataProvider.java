package com.codetest.gmail.registration.regression.testdata;

import net.bytebuddy.utility.RandomString;

import java.util.HashMap;
import java.util.Map;

public class TestDataProvider {

    private Map<String,String> testData=new HashMap<>();

    public Map<String,String> getSuccessfulRegistrationTestData()
    {
        testData.put("firstName","firstName");
        testData.put("lastName","lastName");
        RandomString random=new RandomString(10);
        String userName=random.nextString();
        testData.put("userName",userName);
        testData.put("password","password#123");
        testData.put("confirmPassword","password#123");
        return testData;
    }

    public Map<String,String> getSuccessfulRegistrationBoundaryTest()
    {
        testData.put("firstName","f");
        testData.put("lastName","l");
        RandomString random=new RandomString(1);
        String userName=random.nextString();
        testData.put("userName",userName);
        testData.put("password","pass.123");
        testData.put("confirmPassword","pass.123");
        return testData;
    }

    public Map<String,String> getEmptyTestData()
    {
        testData.put("firstName","");
        testData.put("lastName","");
        testData.put("userName","");
        testData.put("password","");
        testData.put("confirmPassword","");
        return testData;
    }

    public Map<String,String> getInvalidFirstNameTestData()
    {
        testData.put("firstName","firstName123");
        testData.put("lastName","lastName");
        RandomString random=new RandomString(10);
        String userName=random.nextString();
        testData.put("userName",userName);
        testData.put("password","password#123");
        testData.put("confirmPassword","password#123");
        return testData;
    }

    public Map<String,String> getInvalidLastNameTestData()
    {
        testData.put("firstName","firstName");
        testData.put("lastName","lastName123");
        RandomString random=new RandomString(10);
        String userName=random.nextString();
        testData.put("userName",userName);
        testData.put("password","password#123");
        testData.put("confirmPassword","password#123");
        return testData;
    }

    public Map<String,String> getInvalidUserNameTestData()
    {
        testData.put("firstName","firstName");
        testData.put("lastName","lastName");
        RandomString random=new RandomString(10);
        String userName=random.nextString();
        testData.put("userName","userName.&");
        testData.put("password","password#123");
        testData.put("confirmPassword","password#123");
        return testData;
    }

    public Map<String,String> getPasswordMismatchTestData()
    {
        testData.put("firstName","firstName");
        testData.put("lastName","lastName123");
        RandomString random=new RandomString(10);
        String userName=random.nextString();
        testData.put("userName",userName);
        testData.put("password","password#123");
        testData.put("confirmPassword","password");
        return testData;
    }
}
