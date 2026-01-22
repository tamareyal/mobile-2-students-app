package com.example.studentsapp

import com.example.studentsapp.databinding.RowStudentBinding
import com.example.studentsapp.models.Student
import androidx.recyclerview.widget.RecyclerView


class StudentRowViewHolder (
    private val binding: RowStudentBinding,
    private val listener: OnItemClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var student: Student

    init {
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        itemView.setOnClickListener {
            listener?.onStudentItemClick(student)
        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student
        binding.nameTextView.text = student.name
        binding.idTextView.text = student.id
        binding.avatarImageView.setImageResource(student.imageResId)
        binding.checkbox.isChecked = student.isChecked
    }
}