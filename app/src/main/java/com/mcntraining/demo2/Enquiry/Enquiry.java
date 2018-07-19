package com.mcntraining.demo2.Enquiry;

/**
 * Created by user on 3/16/2018.
 */

public class Enquiry {
    String Name;
    String Phone;
    String Email;
    String Message;

    public Enquiry(String name, String phone, String email, String message) {
        Name = name;
        Phone = phone;
        Email = email;
        Message = message;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getMessage() {
        return Message;
    }
}
