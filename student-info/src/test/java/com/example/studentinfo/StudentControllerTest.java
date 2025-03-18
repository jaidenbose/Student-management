package com.example.studentinfo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.studentinfo.controller.StudentController;
import com.example.studentinfo.model.Student;
import com.example.studentinfo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetStudents_ReturnsStatus200() throws Exception {
        // Mock behavior of studentService
        Mockito.when(studentService.getAllStudents())
                .thenReturn(List.of(new Student("Jaiden Bose", "Jaiden@gmail.com",  20)));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students")); // Ensure "students" list is in model
    }

    @Test
    public void testPostStudentWithInvalidData_ShouldReturnFormWithErrors() throws Exception {
        mockMvc.perform(post("/students/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "") // Invalid name
                        .param("email", "invalid-email") // Invalid email
                        .param("age", "15")) // Invalid age
                .andExpect(status().isOk())
                .andExpect(view().name("new-student"))
                .andExpect(model().attributeHasErrors("student"));
    }
}