package model;

import java.io.Serializable;

public class Student implements Serializable {
    public int id;
    public String name;
    public String branch;
    public double marks;

    public Student(int id, String name, String branch, double marks) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Branch: " + branch + " | Marks: " + marks;
    }
}
