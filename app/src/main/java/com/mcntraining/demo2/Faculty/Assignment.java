package com.mcntraining.demo2.Faculty;

/**
 * Created by user on 4/8/2018.
 */

public class Assignment {
    String course,semester,title,assignment;
    int assignid;

    public Assignment() {
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignment() {
        return assignment;
    }

    public int getAssignid() {
        return assignid;
    }

    public Assignment(String course, String semester, String title, String assignment, int assignid) {
        this.course = course;
        this.semester = semester;
        this.title = title;
        this.assignment = assignment;
        this.assignid = assignid;
    }
}
