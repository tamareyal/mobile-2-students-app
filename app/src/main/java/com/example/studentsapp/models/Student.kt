package com.example.studentsapp.models


data class Student(
    val id: String,
    val name: String,
    var isChecked: Boolean,
    val imageResId: Int,
    var phoneNumber: String = "",
    var address: String = ""
)