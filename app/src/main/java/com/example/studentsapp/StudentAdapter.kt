package com.example.studentsapp
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.RowStudentBinding
import com.example.studentsapp.models.Student
import android.view.ViewGroup


interface OnItemClickListener {
    fun onStudentItemClick(student: Student)
}
class StudentAdapter (
    private var students: List<Student>
): RecyclerView.Adapter<StudentRowViewHolder>() {

        var listener: OnItemClickListener? = null

        override fun getItemCount(): Int = students.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRowViewHolder {
            val inflater = android.view.LayoutInflater.from(parent.context)
            val binding = RowStudentBinding.inflate(inflater, parent, false)
            return StudentRowViewHolder(
                binding,
                listener
            )
        }

    override fun onBindViewHolder(holder: StudentRowViewHolder, position: Int) {
        holder.bind(students[position], position)

    }
    }

