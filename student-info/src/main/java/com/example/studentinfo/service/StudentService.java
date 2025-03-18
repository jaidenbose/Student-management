package com.example.studentinfo.service;

import com.example.studentinfo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Delete student by ID
    public boolean deleteStudentById(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

}
