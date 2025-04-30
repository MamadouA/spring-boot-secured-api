package com.ascenders.securedapi.service

import com.ascenders.securedapi.model.Student
import org.springframework.stereotype.Service

@Service
class StudentService {

    // fake data for test only
    // for each student the index is used as the id for simplicity
    private var students = mutableListOf(
        Student(0, "John", "Doe", 20),
        Student(1, "Abdou", "Diop", 23),
        Student(2, "Alain", "Mendy", 25),
        Student(3, "Pierre", "Mané", 21),
        Student(4, "Mouhamed", "Salah", 24),
        Student(5, "Micheal", "Goat", 24),
        Student(6, "Jean", "Baptise", 19),
        Student(7, "Abdou", "Diop", 20),
        Student(8, "Abdou", "Diop", 22)
    )

    fun create(student: Student): Boolean {
        if (!students.contains(student)) {
            student.id = students.size + 1

            students.add(student)

            return true
        }

        return false
    }

    // -
    fun update(student: Student): Boolean {

        if (student.id >= 0 && student.id < students.size) {
            students[student.id] = student

            return true
        }

        return false
    }

    // -
    fun readAll() = students

    // -
    fun findById(id: Int): Student? = if(id >= 0 && id < students.size) students[id] else null

    // -
    fun deleteById(id: Int): Boolean {
        if (findById(id) != null) {
            students.removeAt(id)

            return true
        }

        return  false;
    }
}