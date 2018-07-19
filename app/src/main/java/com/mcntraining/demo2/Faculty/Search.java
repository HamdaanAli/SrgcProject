package com.mcntraining.demo2.Faculty;

/**
 * Created by user on 4/7/2018.
 */

public class Search {
       public String stu_Messg,stu_Course,stu_Father,stu_Dob,stu_address,stu_Mobile,stu_Id,stu_Email,stu_Gener,stu_State,stu_City;
     public int stu_Year;
   public String imguri;



    public Search(String stu_Messg, String stu_Course, String stu_Father, String stu_Dob, String stu_address, String stu_Mobile, String stu_Id, String stu_Email, String stu_Gener, String stu_State, String stu_City, int stu_Year, String imguri) {
        this.stu_Messg = stu_Messg;
        this.stu_Course = stu_Course;
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

    public Search() {
    }

    public String getStu_Messg() {
        return stu_Messg;
    }

    public void setStu_Messg(String stu_Messg) {
        this.stu_Messg = stu_Messg;
    }

    public String getStu_Course() {
        return stu_Course;
    }

    public void setStu_Course(String stu_Course) {
        this.stu_Course = stu_Course;
    }

    public String getStu_Father() {
        return stu_Father;
    }

    public void setStu_Father(String stu_Father) {
        this.stu_Father = stu_Father;
    }

    public String getStu_Dob() {
        return stu_Dob;
    }

    public void setStu_Dob(String stu_Dob) {
        this.stu_Dob = stu_Dob;
    }

    public String getStu_address() {
        return stu_address;
    }

    public void setStu_address(String stu_address) {
        this.stu_address = stu_address;
    }

    public String getStu_Mobile() {
        return stu_Mobile;
    }

    public void setStu_Mobile(String stu_Mobile) {
        this.stu_Mobile = stu_Mobile;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    public String getStu_Email() {
        return stu_Email;
    }

    public void setStu_Email(String stu_Email) {
        this.stu_Email = stu_Email;
    }

    public String getStu_Gener() {
        return stu_Gener;
    }

    public void setStu_Gener(String stu_Gener) {
        this.stu_Gener = stu_Gener;
    }

    public String getStu_State() {
        return stu_State;
    }

    public void setStu_State(String stu_State) {
        this.stu_State = stu_State;
    }

    public String getStu_City() {
        return stu_City;
    }

    public void setStu_City(String stu_City) {
        this.stu_City = stu_City;
    }

    public int getStu_Year() {
        return stu_Year;
    }

    public void setStu_Year(int stu_Year) {
        this.stu_Year = stu_Year;
    }

    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }
}
