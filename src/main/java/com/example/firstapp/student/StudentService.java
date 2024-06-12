package com.example.firstapp.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    private StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
             throw new IllegalStateException("Email already exists!!!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }


//    @Transactional
//    public void updateStudent(Long studentId,String name, String email) {
////        retrieving student data and throwing an error when data does not exist
//       Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + "does not exist"));
//
////       Making sure name is not null, its length is not 0 and the current name is not the same as the one in the database
//       if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)) {
//           student.setName(name);
//       }
//
//        //       Making sure email is not null, its length is not 0 and the current email is not the same as the one in the database
//        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)) {
//            //   checking if the provided email already belongs to a user
//            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
//
//            if (studentOptional.isPresent()) {
//                throw new IllegalStateException("Email already exists!!");
//            }
//            student.setEmail(email);
//        }
//    }
}
