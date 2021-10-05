package com.nuryadincjr.mainlocalandroidstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.data.StudentDatabases;
import com.nuryadincjr.mainlocalandroidstorage.util.AppExecutors;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ActivityAddStudentBinding;

public class AddStudentActivity extends AppCompatActivity {

    private StudentDatabases studentDatabases;
    private ActivityAddStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDatabases = StudentDatabases.getInstance(this);

        binding.btnSave.setOnClickListener(v -> {
            Student newStudent = new Student();
            newStudent.setFullName(binding.etName.getText().toString());
            newStudent.setEmail(binding.etEmail.getText().toString());

            AppExecutors.getsIntance().diskID().execute(() -> {
                Long result = studentDatabases.studentDao().insert(newStudent);
                runOnUiThread(() -> {
                    if (result !=0) {
                        Toast.makeText(AddStudentActivity.this, "Sukses menambahkan " +
                                newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(AddStudentActivity.this, "Gagal menambahkan " +
                                newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                });
            });
        });
    }
}