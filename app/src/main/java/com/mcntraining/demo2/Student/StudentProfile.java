package com.mcntraining.demo2.Student;

/**
 * Created by user on 3/19/2018.
 *///studentmessage,studentcourse,studentfather,studentbirth,studentaddress,studentmobile,studentid,studentemail,studentgender,studentstate,studentcity,studnetyear,profileImageUrl

public class StudentProfile {
    public String stu_Messg,stu_Course,stu_Semester,stu_Father,stu_Dob,stu_address,stu_Mobile,stu_Id,stu_Email,stu_Gener,stu_State,stu_City;
    public  int stu_Year;
    public  String imguri;
    public StudentProfile() {
    }

    public String getStu_Messg() {
        return stu_Messg;
    }

    public String getStu_Course() {
        return stu_Course;
    }

    public String getStu_Semester() {
        return stu_Semester;
    }

    public String getStu_Father() {
        return stu_Father;
    }

    public String getStu_Dob() {
        return stu_Dob;
    }

    public String getStu_address() {
        return stu_address;
    }

    public String getStu_Mobile() {
        return stu_Mobile;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public String getStu_Email() {
        return stu_Email;
    }

    public String getStu_Gener() {
        return stu_Gener;
    }

    public String getStu_State() {
        return stu_State;
    }

    public String getStu_City() {
        return stu_City;
    }

    public int getStu_Year() {
        return stu_Year;
    }

    public String getImguri() {
        return imguri;
    }

    public StudentProfile(String stu_Messg, String stu_Course, String stu_Semester, String stu_Father, String stu_Dob, String stu_address, String stu_Mobile, String stu_Id, String stu_Email, String stu_Gener, String stu_State, String stu_City, int stu_Year, String imguri) {
        this.stu_Messg = stu_Messg;
        this.stu_Course = stu_Course;
        this.stu_Semester = stu_Semester;
        this.stu_Father = stu_Father;
        this.stu_Dob = stu_Dob;
        this.stu_address = stu_address;
        this.stu_Mobile = stu_Mobile;
        this.stu_Id = stu_Id;
        this.stu_Email = stu_Email;
        this.stu_Gener = stu_Gener;
        this.stu_State = stu_State;
        this.stu_City = stu_City;
        this.stu_Year = stu_Year;
        this.imguri = imguri;
    }





}
