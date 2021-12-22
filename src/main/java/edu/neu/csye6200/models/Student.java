package edu.neu.csye6200.models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_student")
public class Student extends Person {
    @Column(name = "gpa")
    private double gpa;

    public Student() {
    }

    public Student(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address, double gpa) {
        super(firstName, lastName, emailId, dateOfBirth, parentFullName, address);
        this.gpa = gpa;
        if (this.getAge() < 6)
            throw new IllegalArgumentException("Student Age should be more than or equal to 6 Months");
    }


    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
