package com.nuryadincjr.mainlocalandroidstorage;

import android.content.Intent;
import android.view.View;
import android.widget.PopupMenu;

import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.data.StudentDatabases;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ListStudentBinding;
import com.nuryadincjr.mainlocalandroidstorage.util.AppExecutors;

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

    public void openMenuEdit(View view, int uid, String name, String email) {
        PopupMenu menu = new PopupMenu(view.getContext(), view);
        menu.getMenuInflater().inflate(R.menu.menu_editor, menu.getMenu());
        menu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.miEdit:
                    Intent intent = new Intent(view.getContext(), AddStudentActivity.class);
                    intent.putExtra("uid", uid);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("action", "editor");
                    view.getContext().startActivity(intent);

                    break;
                case R.id.miDelete:
                    StudentDatabases studentDatabases = StudentDatabases.getInstance(view.getContext());

                    Student newStudent = new Student();
                    newStudent.setUid(uid);

                    AppExecutors.getsIntance().diskID().execute(() -> {
                        studentDatabases.studentDao().delete(newStudent);

                    });
                    break;
            }
            return true;
        });
        menu.show();
    }
}
