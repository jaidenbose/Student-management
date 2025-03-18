package com.example.studentinfo.controller;

import com.example.studentinfo.model.Student;
import com.example.studentinfo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // View all students
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    // Show form to add a new student
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student("", "", 18));
        return "new-student";
    }

    // Save new student
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "new-student";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    // Delete student by ID
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    // Return students as JSON
    @GetMapping("/json")
    @ResponseBody
    public List<Student> getStudentsJson() {
        return studentService.getAllStudents();
    }
}
