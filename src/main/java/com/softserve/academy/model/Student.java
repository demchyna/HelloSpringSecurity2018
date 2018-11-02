package com.softserve.academy.model;

public class Student {
    private int id;
    private String name;
    private int age;
    private Group group;

    private static int counter = 0;

    public Student() {
        this.id = ++counter;
    }

    public Student(String name, int age, Group group) {
        this.id = ++counter;
        this.name = name;
        this.age = age;
        this.group = group;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

//    @Override
//    public String toString() {
//        return "Student { " +
//                "id = " + id +
//                ", name = '" + name + '\'' +
//                ", age = " + age +
//                ", group = " + group +
//                " }";
//    }
}
