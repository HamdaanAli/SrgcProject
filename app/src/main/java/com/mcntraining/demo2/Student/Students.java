package com.mcntraining.demo2.Student;

/**
 * Created by user on 3/13/2018.
 */

public class Students {


    String studentName;

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    String password;
    String emailAdd;

    public Students(String studentName, String password, String emailAdd) {
        this.studentName = studentName;
        this.password = password;
        this.emailAdd = emailAdd;
    }

    public Students()
    {

    }

    public String getStudentName() {
        return studentName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAdd() {
        return emailAdd;
    }
}
