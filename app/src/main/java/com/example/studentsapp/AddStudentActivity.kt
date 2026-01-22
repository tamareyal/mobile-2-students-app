package com.example.studentsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityAddStudentBinding
import com.example.studentsapp.models.Student
import com.example.studentsapp.models.Model

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {

        binding.cancelButton.setOnClickListener {
            finish()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val id = binding.idEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val address = binding.addressEditText.text.toString()

            if (name.isNotBlank() && id.isNotBlank()) {
                val student = Student(
                    name = name,
                    id = id,
                    imageResId = R.drawable.default_avatar,
                    isChecked = false,
                    phoneNumber = phone,
                    address = address
                )

                Model.shared.students.add(student)
                binding.statusTextView.text = "Student Saved: Name = $name, ID = $id"

                // Clear inputs
                binding.nameEditText.text.clear()
                binding.idEditText.text.clear()
                binding.phoneEditText.text.clear()
                binding.addressEditText.text.clear()
            }
            else {
                binding.statusTextView.text = "Please enter both Name and ID."
            }
        }
    }

}