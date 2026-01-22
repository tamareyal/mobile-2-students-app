package com.example.studentsapp.models

class Model private constructor() {

    val students = mutableListOf<Student>()

    companion object {
        val shared = Model()
    }
}