package edu.neu.csye6200.models;

import javax.persistence.Entity;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_student")
public class Student extends Person {
    private int age;
}