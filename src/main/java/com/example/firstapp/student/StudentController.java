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
    public ResponseEntity<CustomResponse> deleteStudent(@PathVariable("studentId") Long studentId ) throws URISyntaxException {
        studentService.deleteStudent(studentId);

        CustomResponse response = new CustomResponse("Deleted student Successful!");
        URI location = new URI("/api/v1/students/");
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<CustomResponse> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String email
    ) throws URISyntaxException {
        Student result = studentService.updateStudent(studentId,name,email);

        CustomResponse response = new CustomResponse(result,"Updated student Successfully!");
        URI location = new URI("/api/v1/students/");
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping("{studentId}")
    public ResponseEntity<CustomResponse> getStudent(@PathVariable(required = true) Long studentId) throws URISyntaxException{
       Student result = studentService.getStudent(studentId);
      CustomResponse response = new CustomResponse<>(result,"Retrieved single student successfully!");
      URI location = new URI("/api/v1/students" + result.getId());

      return ResponseEntity.created(location).body(response);
    }




}
