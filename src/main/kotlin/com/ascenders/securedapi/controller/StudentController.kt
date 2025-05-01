package com.ascenders.securedapi.controller

import com.ascenders.securedapi.model.Student
import com.ascenders.securedapi.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController {
    // -
    @Autowired
    private lateinit var studentService: StudentService

    @PostMapping
    fun create(@RequestBody student: Student): Student {
        studentService.create(student)

        return student
    }

    @GetMapping
    fun getAll(): List<Student> = studentService.readAll()

    @GetMapping("{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<Student?> {
        if (id > 0 && id < studentService.readAll().size) {
            return ResponseEntity.ok(studentService.findById(id))
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PutMapping
    fun update(@RequestBody student: Student): ResponseEntity<Student?> {
        val oldStudent = studentService.findById(student.id)

        if(oldStudent != null) {
            studentService.update(student)

            return ResponseEntity.ok(student)
        }

        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<Student?> {
        val student = studentService.findById(id)

        if(student != null) {
            ResponseEntity.ok(student)
            studentService.deleteById(student.id)

            return ResponseEntity.ok(student)
        }

        return ResponseEntity.notFound().build()
    }
}