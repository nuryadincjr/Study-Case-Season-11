package com.nuryadincjr.mainlocalandroidstorage.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;


@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    @Insert(onConflict = REPLACE)
    Long insert(Student student);

    @Update
    int update(Student student);

    @Delete
    int delete(Student student);
}
