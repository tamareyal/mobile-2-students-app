package com.example.studentsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.models.Model
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
                    finish() // student gone → close details screen
                } else {
                    loadStudent() // refresh UI with updated data
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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