/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spark.java;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author haamani
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Service service = new Service();
        port(5678);

        // TODO code application logic here
        get("/student", (req, res) -> {
            return objectToJson(service.getStudentsRecord());
        });

        get("student/:id", (req, res) -> {
            String id = req.params(":id");
            return objectToJson(service.getStudentById(id));
        });

        post("/student", (req, res) -> {
            Student student = objectFromJson(req.body());
            System.out.println(student);
            service.createStudent(student);
            return "{\"status\":\"ok\"}";
        });

        delete("student/:id", (req, res) -> {
            String id = req.params(":id");
            System.out.println(id);
            service.deleteRecord(id);
            return "{​​\"status\":\"deleted\"}​​";
        });
        
        put("student/:id", (req, res) ->{
            String id = req.params(":id");
            Student student = objectFromJson(req.body());
            String name = student.getName();
            int age = student.getAge();
            service.updateRecord(id, name, age);
            return "{\"status\":\"updated\"}";
        });

    }

    private static String objectToJson(ArrayList<Student> students) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(students);
    }

    private static String objectToJson(Student student) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(student);
    }

    private static Student objectFromJson(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Student student = null;

        System.out.println(jsonString);
        try {
            student = gson.fromJson(jsonString, Student.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getLocalizedMessage());
        }

        return student;

    }

}
