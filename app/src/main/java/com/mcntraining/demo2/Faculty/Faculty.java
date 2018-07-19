package com.mcntraining.demo2.Faculty;

/**
 * Created by user on 3/22/2018.
 */

public class Faculty {
    String facultyName;
    String password;
    String emailAdd;

    public Faculty(String facultyName, String password, String emailAdd) {
        this.facultyName = facultyName;
        this.password = password;
        this.emailAdd = emailAdd;
    }

    public Faculty() {
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAdd() {
        return emailAdd;
    }
}
