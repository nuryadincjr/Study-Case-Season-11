package com.nuryadincjr.mainlocalandroidstorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.mainlocalandroidstorage.data.Student;
import com.nuryadincjr.mainlocalandroidstorage.databinding.ListStudentBinding;

import java.util.List;

public class StudentAdapter  extends RecyclerView.Adapter<StudentViewHolder> {

    private List<Student> data;
    private Context context;

    public StudentAdapter(Context context, List<Student> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListStudentBinding binding = ListStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new StudentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = data.get(position);

        holder.setDataToView(student);
        holder.itemView.setOnClickListener(v -> {
            holder.openMenuEdit(v, data.get(position).getUid(),
                    data.get(position).getFullName(),
                    data.get(position).getEmail());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
