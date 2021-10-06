package com.nuryadincjr.mainlocalandroidstorage;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.data.StudentDatabases;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ActivityMainBinding;
import com.nuryadincjr.mainlocalandroidstorage.util.AppExecutors;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private StudentDatabases studentDatabases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDatabases = StudentDatabases.getInstance(this);

        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });

        binding.srLayout.setOnRefreshListener(() -> {
            getData();
            binding.srLayout.setRefreshing(false);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private  void getData() {
        AppExecutors.getsIntance().diskID().execute(() -> {
            List<Student> student = studentDatabases.studentDao().getAllStudent();
            runOnUiThread(() -> {
                if (student.size() !=0) {
                    StudentAdapter studentAdapter = new StudentAdapter(this, student);
                    binding.rvStudent.setLayoutManager(new LinearLayoutManager(this));
                    binding.rvStudent.setAdapter(studentAdapter);
                }
            });
        });
    }
}