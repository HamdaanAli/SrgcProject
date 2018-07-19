package com.mcntraining.demo2.Faculty;

/**
 * Created by HAMDAN on 16-Apr-18.
 */

public class FacultyProfile {
    public String f_Messg,f_Course,designation,f_Father,f_Dob,f_address,f_Mobile,f_Id,f_Email,f_Gener,f_State,f_City;
    public  int f_Year;
    public  String imguri;

    public FacultyProfile() {
    }

    public String getF_Messg() {
        return f_Messg;
    }

    public String getF_Course() {
        return f_Course;
    }

    public String getDesignation() {
        return designation;
    }

    public String getF_Father() {
        return f_Father;
    }

    public String getF_Dob() {
        return f_Dob;
    }

    public String getF_address() {
        return f_address;
    }

    public String getF_Mobile() {
        return f_Mobile;
    }

    public String getF_Id() {
        return f_Id;
    }

    public String getF_Email() {
        return f_Email;
    }

    public String getF_Gener() {
        return f_Gener;
    }

    public String getF_State() {
        return f_State;
    }

    public String getF_City() {
        return f_City;
    }

    public int getF_Year() {
        return f_Year;
    }

    public String getImguri() {
        return imguri;
    }

    public FacultyProfile(String f_Messg, String f_Course, String designation, String f_Father, String f_Dob, String f_address, String f_Mobile, String f_Id, String f_Email, String f_Gener, String f_State, String f_City, int f_Year, String imguri) {
        this.f_Messg = f_Messg;
        this.f_Course = f_Course;
        this.designation = designation;
        this.f_Father = f_Father;
        this.f_Dob = f_Dob;
        this.f_address = f_address;
        this.f_Mobile = f_Mobile;
        this.f_Id = f_Id;
        this.f_Email = f_Email;
        this.f_Gener = f_Gener;
        this.f_State = f_State;
        this.f_City = f_City;
        this.f_Year = f_Year;
        this.imguri = imguri;
    }
}
