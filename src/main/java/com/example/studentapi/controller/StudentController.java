package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Register a new student
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Collect all error messages
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : errors) {
                errorMessages.append(error.getDefaultMessage()).append(", ");
            }
            // Remove trailing comma
            String errorMessage = errorMessages.toString().endsWith(", ") ?
                    errorMessages.substring(0, errorMessages.length() - 2) :
                    errorMessages.toString();

            // Return a 400 Bad Request with the validation errors
            return ResponseEntity.badRequest().body(null);
        }

        Student registeredStudent = studentService.registerStudent(student);
        return ResponseEntity.status(201).body(registeredStudent);
    }

    // Update student details
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Collect all error messages
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : errors) {
                errorMessages.append(error.getDefaultMessage()).append(", ");
            }
            // Remove trailing comma
            String errorMessage = errorMessages.toString().endsWith(", ") ?
                    errorMessages.substring(0, errorMessages.length() - 2) :
                    errorMessages.toString();

            // Return a 400 Bad Request with the validation errors
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Student> updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
