package com.mcntraining.demo2.Faculty;

/**
 * Created by user on 4/17/2018.
 */

public class AssignmentSearch {
   public String course,semester,title,assignment;
    public  int assignid;

    public AssignmentSearch(String course, String semester, String title, String assignment, int assignid) {
        this.course = course;
        this.semester = semester;
        this.title = title;
        this.assignment = assignment;
        this.assignid = assignid;
    }

    public AssignmentSearch() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public int getAssignid() {
        return assignid;
    }

    public void setAssignid(int assignid) {
        this.assignid = assignid;
    }
}
