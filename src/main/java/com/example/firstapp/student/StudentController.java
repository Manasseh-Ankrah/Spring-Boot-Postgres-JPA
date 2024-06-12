package com.example.firstapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void registerNewStudent( @RequestBody Student Student) {
         studentService.addNewStudent(Student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@RequestParam(required = true) @PathVariable("studentId") Long studentId ) {
        studentService.deleteStudent(studentId);
    }
//
//    @PutMapping(path = "{studentId}")
//    public void updateStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestParam(required = true) String name,
//            @RequestParam(required = true) String email
//    ) {
//        studentService.updateStudent(studentId,name,email);
//
//    }
}
