package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.models.Model
import android.widget.Toast

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = "Edit Student"

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
            binding.nameEditText.setText(it.name)
            binding.idEditText.setText(it.id)
            binding.phoneEditText.setText(it.phoneNumber)
            binding.addressEditText.setText(it.address)
            binding.avatarImageView.setImageResource(it.imageResId)
            binding.checkBox.isChecked = it.isChecked
        }

        setupButtons()
    }

    private fun setupButtons() {
        binding.cancelButton.setOnClickListener {
            finish()
        }

        binding.deleteButton.setOnClickListener {
            student?.let {
                Model.shared.students.remove(it)
                Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.putExtra("DELETED", true)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        binding.saveButton.setOnClickListener {
            for (student in Model.shared.students) {
                if (student.id == binding.idEditText.text.toString() &&
                    student != this.student) {
                    binding.statusTextView.text = "Student ID already exists."
                    return@setOnClickListener
                }
            }

            val name = binding.nameEditText.text.toString()
            val id = binding.idEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val address = binding.addressEditText.text.toString()
            val isChecked = binding.checkBox.isChecked

            if (name.isBlank() || id.isBlank()) {
                Toast.makeText(this, "Name and ID cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            student?.let {
                it.name = name
                it.id = id
                it.phoneNumber = phone
                it.address = address
                it.isChecked = isChecked

                Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }
        }

    }
}