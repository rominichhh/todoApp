package com.rominichhh.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rominichhh.todoapp.database.LocalDatabase;
import com.rominichhh.todoapp.database.TasksDao;
import com.rominichhh.todoapp.models.Task;

public class CreateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateTask;
    private EditText etTitle;
    private EditText etResponsible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setUpToolbar();

        btnCreateTask = findViewById(R.id.btn_create_task);
        btnCreateTask.setOnClickListener(this);

        etTitle = findViewById(R.id.et_task_title);
        etResponsible = findViewById(R.id.et_task_assigned_to);
    }

    private void setUpToolbar() {
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.new_task);

        // Hacemos visible la flecha para volver atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCreateTask){
            createTask();
        }
    }

    private void createTask() {
        String title = etTitle.getText().toString();
        String responsible = etResponsible.getText().toString();

        Task task = new Task(title, responsible, false);
        LocalDatabase localDatabase = LocalDatabase.getInstance(this);
        TasksDao tasksDao = localDatabase.getTaskDao();
        tasksDao.insert(task);

        Toast.makeText(this, "Tarea Creada", Toast.LENGTH_SHORT).show();

    }
}