package com.example.firstapp.student;

import com.example.firstapp.auth.Auth;
import com.example.firstapp.dto.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents() {
       return studentService.getStudents();
    }


    @PostMapping

    public ResponseEntity<CustomResponse> registerNewStudent(@Valid @RequestBody Student student) throws URISyntaxException {

        Student result = studentService.addNewStudent(student);
        CustomResponse response = new CustomResponse(result,"Registration Successful!");
        URI location = new URI("/api/v1/students/" + result.getId());
        return ResponseEntity.created(location).body(response);
    }






    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId ) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String email
    ) {
        studentService.updateStudent(studentId,name,email);
    }
}
