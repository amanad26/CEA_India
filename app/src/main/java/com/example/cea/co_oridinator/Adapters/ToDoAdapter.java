package com.example.cea.co_oridinator.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Apis.TodoInterface;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Models.TodoListModel;
import com.example.cea.databinding.ToDoLayoutBinding;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {


    Context context;
    List<TodoListModel.Datum> models;
    Session session;
    TodoInterface todoInterface;

    public ToDoAdapter(Context context, List<TodoListModel.Datum> models, TodoInterface todoInterface) {
        this.context = context;
        this.models = models;
        this.todoInterface = todoInterface;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.taskName.setText(models.get(position).getTodoName());
        holder.binding.currentStatus.setText(models.get(position).getTodoAssignStatus());
        holder.binding.assignDate.setText(formatDate(models.get(position).getAssignDate()));
        holder.binding.description.setText(Html.fromHtml(models.get(position).getTodoDescription()));

        if (models.get(position).getTodoAssignStatus().equalsIgnoreCase("2")) {
            holder.binding.actionCard.setCardBackgroundColor(context.getResources().getColor(R.color.pink_light));
        }
        holder.binding.actionCard.setOnClickListener(view -> showWarningDialog(models.get(position).getTodoId()));

    }

    private void updateTodo(String todoId) {

        RetrofitClient.getClient(context).completeTodo(
                session.getUserId(),
                todoId
        ).enqueue(new Callback<TodoListModel>() {
            @Override
            public void onResponse(@NonNull Call<TodoListModel> call, @NonNull Response<TodoListModel> response) {
                showDialog();
                Log.e("TAG", "onResponse() called with: call = [" + call + "], response = [" + response.code() + "]");
            }

            @Override
            public void onFailure(@NonNull Call<TodoListModel> call, @NonNull Throwable t) {
                Log.e("TAG", "onFailure() called with: call = [" + call + "], t = [" + t.getLocalizedMessage() + "]");
            }
        });


    }

    private void showWarningDialog(String todoId) {
        new KAlertDialog(context, KAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("you want to Complete this Todo ?")
                .setCancelClickListener("No!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmClickListener("Yes,Complete it!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        updateTodo(todoId);
                    }
                }).show();
    }

    private void showDialog() {

        new KAlertDialog(context, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Good job!")
                .setContentText("Your Todo Completed!")
                .setConfirmText("Okay")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        todoInterface.onTodoComplete();
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .show();

    }

    public static String formatDate(String s) {
        //  s = "2022-10-21 08:08:18";

        String[] dateTime = s.split(" ");

        String dateS = dateTime[0];
//        String timeS = dateTime[1];
//
//        timeS = timeS.substring(0, 5);

        String[] datess = dateS.split("-");

        String year = datess[0];
        String month = datess[1];
        String day = datess[2];

        return day + " " + getMonthName(Integer.parseInt(month)) + " " + year + " ";
    }

    public static String getMonthName(int month) {

        String monthString;
        switch (month) {
            case 1:
                monthString = "Jan";
                break;
            case 2:
                monthString = "Feb";
                break;
            case 3:
                monthString = "Mar";
                break;
            case 4:
                monthString = "Apr";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "Jun";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "Aug";
                break;
            case 9:
                monthString = "Sep";
                break;
            case 10:
                monthString = "Oct";
                break;
            case 11:
                monthString = "Nov";
                break;
            case 12:
                monthString = "Dec";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        System.out.println(monthString);

        return monthString;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ToDoLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ToDoLayoutBinding.bind(itemView);
        }
    }


}
