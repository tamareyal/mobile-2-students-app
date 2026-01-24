package com.example.studentsapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.models.Model
import com.example.studentsapp.models.Student

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var student: Student? = null

    private val editLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val deleted = result.data?.getBooleanExtra("DELETED", false) ?: false
                if (deleted) {
                    finish()
                } else {
                    loadStudent() // refresh UI with updated data
                }
            }
        }

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

        loadStudent()

        binding.editButton.setOnClickListener {
            // Once implemented, this will open EditStudentActivity
            student?.let { student ->
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("STUDENT_ID", student.id)
                editLauncher.launch(intent)
            }
        }
    }

    private fun loadStudent() {
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
    }
}