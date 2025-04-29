package com.ascenders.securedapi.service

import com.ascenders.securedapi.model.Student
import org.springframework.stereotype.Service

@Service
class StudentService {
    private var students = mutableListOf(
        Student(1, "John", "Doe", 20),
        Student(2, "Abdou", "Diop", 23),
        Student(3, "Alain", "Mendy", 25),
        Student(4, "Pierre", "Mané", 21),
        Student(5, "Mouhamed", "Salah", 24),
        Student(6, "Micheal", "Goat", 24),
        Student(7, "Jean", "Baptise", 19),
        Student(8, "Abdou", "Diop", 20),
        Student(9, "Abdou", "Diop", 22)
    )

    fun getAllStudents() = students
    fun getStudentById(id: Int): Student? = if(id >= 0 && id < students.size) students[id] else null
}