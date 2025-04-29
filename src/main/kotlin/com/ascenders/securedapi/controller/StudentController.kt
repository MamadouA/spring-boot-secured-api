package com.ascenders.securedapi.controller

import com.ascenders.securedapi.model.Student
import com.ascenders.securedapi.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController {
    // -
    @Autowired
    lateinit var studentService: StudentService

    @GetMapping
    fun getAllStudents(): List<Student> = studentService.getAllStudents()

    @GetMapping("{id}")
    fun getStudentById(@PathVariable("id") id: Int): ResponseEntity<Student?> {
        if (id > 0 && id < studentService.getAllStudents().size) {
            return ResponseEntity.ok(studentService.getStudentById(id))
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}