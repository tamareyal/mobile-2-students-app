package com.example.studentsapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.models.Model
import com.example.studentsapp.models.Student


class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = "Student Details"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.mainToolbar.setNavigationOnClickListener {
            // This handles the back button press
            onBackPressedDispatcher.onBackPressed()
        }

        // Get student ID from Intent
        val studentId = intent.getStringExtra("STUDENT_ID")
        student = Model.shared.students.find {
            it.id == studentId
        }

        student?.let {
            binding.nameTextView.text = "Name: ${it.name}"
            binding.idTextView.text = "ID: ${it.id}"
            binding.phoneTextView.text = "Phone: ${it.phoneNumber}"
            binding.addressTextView.text = "Address: ${it.address}"
            binding.avatarImageView.setImageResource(it.imageResId)
            binding.checkBox.isChecked = it.isChecked
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
        }

        binding.editButton.setOnClickListener {
            // Once implemented, this will open EditStudentActivity
//            student?.let { student ->
//                val intent = Intent(this, EditStudentActivity::class.java)
//                intent.putExtra("STUDENT_ID", student.id)
//                startActivity(intent)
//            }
        }
    }
}