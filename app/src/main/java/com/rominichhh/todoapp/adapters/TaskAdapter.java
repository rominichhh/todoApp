package com.rominichhh.todoapp.adapters;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rominichhh.todoapp.R;
import com.rominichhh.todoapp.models.Task;

import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface OnTaskClickListener{
        void OnTaskClick(Task task);
    }

    private List<Task> tasks;
    private OnTaskClickListener clickListener;
    private ListItemClickListener<Task> itemClickListener;

    public void setOnTaskClickListener(OnTaskClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemClickListener(ListItemClickListener<Task> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvAssignedTo;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_task_title);
            tvAssignedTo = itemView.findViewById(R.id.tv_task_assigned_to);
        }

        public void bind(Task task) {
            tvTitle.setText(task.getTitle());
            tvAssignedTo.setText(task.getAssignedTo());

            if (task.isFinished()){
                tvAssignedTo.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
            } else {
                tvAssignedTo.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.purple_200));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        clickListener.OnTaskClick(task);
                    }
                    if(itemClickListener != null){
                        itemClickListener.OnItemClick(task);
                    }
                }
            });
        }
    }
}
