package com.codetest.gmail.registration.service;

import com.codetest.gmail.registration.dto.Registration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl {

    List<String> list;

    public RegistrationServiceImpl()
    {
        list=new ArrayList<>();
    }

    public String validation(Registration registration)
    {
        if((registration.getFirstName().isEmpty())||(registration.getLastName().isEmpty())||(registration.getPassword().isEmpty())||(registration.getUserName().isEmpty())||(registration.getConfirmPassword().isEmpty()))
        {
            registration.setUserName("First name and/or Last name and/or User name and/or Password and/or Confirm password is empty");
            return "error";
        }

        else if((!registration.getFirstName().matches ("[a-zA-Z]+\\.?"))||(!registration.getLastName().matches ("[a-zA-Z]+\\.?")))
        {
            registration.setUserName("First name/Last Name must be letters");
            return "error";
        }

        else if((!registration.getUserName().matches ("[a-zA-Z0-9]+\\.?")))
        {
            registration.setUserName("User name must be letters & numbers");
            return "error";
        }

        else if((!registration.getPassword().matches ("[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{8,64}"))||(!registration.getConfirmPassword().matches ("[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{8,64}")))
        {
            registration.setUserName("Password / confirm password must be 8 or more characters with a mix of letters, numbers & symbols");
            return "error";
        }

        else if(!(registration.getPassword().equals(registration.getConfirmPassword())))
        {
            registration.setUserName("Password / confirm password is not matched");
            return "error";
        }

        else if(list.contains(registration.getUserName().trim().toLowerCase()))
        {
            registration.setUserName("User name already exists");
            return "error";
        }

        list.add(registration.getUserName().trim().toLowerCase());
            return "success";
    }
}
