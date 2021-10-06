package com.nuryadincjr.mainlocalandroidstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.data.StudentDatabases;
import com.nuryadincjr.mainlocalandroidstorage.util.AppExecutors;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ActivityAddStudentBinding;

public class AddStudentActivity extends AppCompatActivity {

    private StudentDatabases studentDatabases;
    private ActivityAddStudentBinding binding;
    private final String TITLE = "Tambah Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDatabases = StudentDatabases.getInstance(this);

        Intent intent = getIntent();


        if(intent.getStringExtra("action") != null) {
            getSupportActionBar().setTitle("Edit Data");

            binding.etName.setText(intent.getStringExtra("name"));
            binding.etEmail.setText(intent.getStringExtra("email"));
        } else
            getSupportActionBar().setTitle(TITLE);


        binding.btnSave.setOnClickListener(v -> {

            Student newStudent = new Student();
            newStudent.setFullName(binding.etName.getText().toString());
            newStudent.setEmail(binding.etEmail.getText().toString());

            if(getSupportActionBar().getTitle().equals(TITLE)) {
                AppExecutors.getsIntance().diskID().execute(() -> {
                    Long result = studentDatabases.studentDao().insert(newStudent);
                    runOnUiThread(() -> {
                        if (result !=0) {
                            Toast.makeText(this, "Sukses menambahkan " +
                                    newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, "Gagal menambahkan " +
                                    newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                    });
                });
            } else {
                newStudent.setUid(intent.getIntExtra("uid", 0));
                AppExecutors.getsIntance().diskID().execute(() -> {
                    int result = studentDatabases.studentDao().update(newStudent);
                    runOnUiThread(() -> {
                        if (result !=0) {
                            Toast.makeText(this, "Sukses mengubah " +
                                    newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, "Gagal mengubah " +
                                    newStudent.getFullName(), Toast.LENGTH_SHORT).show();
                    });
                });
            }
        });
    }
}