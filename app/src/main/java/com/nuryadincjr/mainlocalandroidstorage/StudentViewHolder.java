package com.nuryadincjr.mainlocalandroidstorage;

import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ListStudentBinding;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    ListStudentBinding binding;

    public StudentViewHolder(ListStudentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setDataToView(Student student) {
        binding.tvStidentName.setText(student.getFullName());
        binding.tvStudentEmail.setText(student.getEmail());
    }
}
