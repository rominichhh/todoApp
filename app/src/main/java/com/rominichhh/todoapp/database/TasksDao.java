package com.rominichhh.todoapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rominichhh.todoapp.models.Task;

import java.util.List;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Task> getAllTask();

    @Query("SELECT * FROM tasks WHERE id =:id")
    Task getTaskById(long id);

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}
