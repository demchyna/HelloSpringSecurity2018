package com.softserve.academy.model;

public class Group {
    private int id;
    private String name;
    private int course;

    private static int counter = 1;

    public Group() {
        this.id = ++counter;
    }

    public Group(String name, int course) {
        this.id = ++counter;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

//    @Override
//    public String toString() {
//        return "Group { " +
//                "id = " + id +
//                ", name = '" + name + '\'' +
//                ", course = " + course +
//                " }";
//    }
}
