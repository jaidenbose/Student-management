package com.example.studentinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.studentinfo.model.Student;
import com.example.studentinfo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentServiceTest {

    private StudentService studentService;
    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        studentService = new StudentService() {
            @Override
            public List<Student> getAllStudents() {
                return studentList;
            }

            @Override
            public void addStudent(Student student) {
                studentList.add(student);
            }
        };
    }

    @Test
    public void testAddStudent_IncreasesListSize() {
        Student student = new Student("Jaiden Bose", "jaiden@gmail.com", 20);


        int initialSize = studentService.getAllStudents().size();
        studentService.addStudent(student);

        assertEquals(initialSize + 1, studentService.getAllStudents().size());
    }
}
