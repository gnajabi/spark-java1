/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spark.java;

import java.util.ArrayList;

/**
 *
 * @author haamani
 */
public class Database {

    static ArrayList<Student> studentList = new ArrayList<>();

    public static void createStudent(Student student) {
        studentList.add(student);
    }

    public static Student getStudentById(String id) {
        int index = Integer.parseInt(id);
        return studentList.get(index);
    }

    public static void updateRecord(String id, String name, int age){
        Student student = getStudentById(id);
        student.setName(name);
        student.setAge(age);
    }

    public static void deleteRecord(String id) {
        int index = Integer.parseInt(id);
        studentList.remove(index);
    }

    public static ArrayList<Student> getStudents() {
        return studentList;
    }
}
