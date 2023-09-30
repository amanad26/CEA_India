package com.example.cea.co_oridinator.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Apis.TodoInterface;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Adapters.ToDoAdapter;
import com.example.cea.co_oridinator.Models.TodoListModel;
import com.example.cea.databinding.FragmentCoToDoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Co_To_DoFragment extends Fragment implements TodoInterface {


    FragmentCoToDoBinding binding;
    Activity activity;
    Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCoToDoBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        init();
        return binding.getRoot();
    }

    private void init() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getTodoList();
    }

    private void getTodoList() {

        RetrofitClient.getClient(activity).getTodoList(session.getUserId()).enqueue(new Callback<TodoListModel>() {
            @Override
            public void onResponse(@NonNull Call<TodoListModel> call, @NonNull Response<TodoListModel> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            List<TodoListModel.Datum> data = new ArrayList<>();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (response.body().getData().get(i).getTodoAssignStatus().equalsIgnoreCase("1")) {
                                    data.add(response.body().getData().get(i));
                                }
                            }

                            if (data.size() != 0) {
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                                linearLayoutManager.setReverseLayout(true);
                                linearLayoutManager.setStackFromEnd(true);
                                binding.homeRecycler.setLayoutManager(linearLayoutManager);
                                binding.homeRecycler.setAdapter(new ToDoAdapter(activity, data, Co_To_DoFragment.this));
                            } else {
                                binding.noData.setVisibility(View.VISIBLE);
                            }


                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<TodoListModel> call, @NonNull Throwable t) {
                binding.progress.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onTodoComplete() {
        binding.homeRecycler.setVisibility(View.GONE);
        getTodoList();
    }
}