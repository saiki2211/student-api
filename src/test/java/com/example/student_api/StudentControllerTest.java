package com.example.student_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testGetAllStudents_ShouldReturn200OK() throws Exception {
        // Mock the repository response
        List<Student> students = Arrays.asList(
            new Student(1, "John Doe", "Computer Science", "john@example.com"),
            new Student(2, "Jane Smith", "Mathematics", "jane@example.com")
        );
        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }
} 