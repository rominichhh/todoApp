package com.rominichhh.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rominichhh.todoapp.adapters.ListItemClickListener;
import com.rominichhh.todoapp.adapters.TaskAdapter;
import com.rominichhh.todoapp.database.LocalDatabase;
import com.rominichhh.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener, ListItemClickListener<Task> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setUpTaskList();
    }

    private void setUpTaskList() {
        RecyclerView rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = createTaskList();
        TaskAdapter adapter = new TaskAdapter(tasks);
        adapter.setOnTaskClickListener(this);
        adapter.setOnItemClickListener(this);

        rvTasks.setAdapter(adapter);
    }

    private void setUpToolbar() {
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.my_tasks);

    }

    private List<Task> createTaskList() {

        return LocalDatabase.getInstance(this).getTaskDao().getAllTask();
    }

    @Override
    public void OnTaskClick(Task task) {
        //Toast.makeText(this,task.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemClick(Task item) {
        Toast.makeText(this,item.getAssignedTo(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_create_new_task){

            startNewTaskActivity();
        }

        if (item.getItemId() == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SP_CREDENTIALS, MODE_PRIVATE);

        if(sharedPreferences.contains(Constants.EMAIL_EXTRA) || sharedPreferences.contains(Constants.PASSWORD_EXTRA)){
            sharedPreferences.edit()
                    .clear()
                    .apply();
        }

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }

    private void startNewTaskActivity() {
        startActivity(new Intent(this, CreateTaskActivity.class));
    }
}