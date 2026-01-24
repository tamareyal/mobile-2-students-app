package com.example.studentsapp.models


data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean,
    val imageResId: Int,
    var phoneNumber: String = "",
    var address: String = ""
)