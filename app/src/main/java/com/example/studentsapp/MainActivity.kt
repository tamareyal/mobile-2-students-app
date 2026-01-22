package com.example.studentsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityMainBinding
import com.example.studentsapp.StudentsAdapter
import com.example.studentsapp.models.Model
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = StudentsAdapter(Model.shared.students)
        binding.studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentsRecyclerView.setHasFixedSize(true)
        binding.studentsRecyclerView.adapter = adapter

        adapter.listener = object : OnItemClickListener {
            override fun onStudentItemClick(student: com.example.studentsapp.models.Student) {
                // TODO: Handle item click - show student details
            }
        }
    }
}