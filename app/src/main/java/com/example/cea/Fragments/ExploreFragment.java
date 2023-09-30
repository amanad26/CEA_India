package com.example.cea.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.cea.Adapters.HomeCategoryAdapter;
import com.example.cea.Adapters.SearchProductAdapter;
import com.example.cea.Adapters.ViewPager;
import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.Models.SearchModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.FragmentExploreBinding;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jaredrummler.materialspinner.MaterialSpinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreFragment extends Fragment {
    private FragmentExploreBinding binding;
    private String[] sortingFilters = {"Sort By", "Newest"};
    private ApiInterface apiInterface;
    private Activity activity;
    private Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater, container, false);
        activity = getActivity();
        session = new Session(activity);


        apiInterface = RetrofitClient.getClient(activity);

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });

        binding.sortSpinner.setItems(sortingFilters);

        binding.sortSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position == 1) {

                }
            }
        });

        getHomePagedData();

        return binding.getRoot();
    }

    @Override
    public void onResume() {

        binding.parent.postDelayed(
                () -> {
                    binding.edtSearch.requestFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(binding.edtSearch, InputMethodManager.SHOW_IMPLICIT);
                }, 100);

        super.onResume();
    }

    private void getHomePagedData() {
        binding.progresBar.setVisibility(View.VISIBLE);
        apiInterface.getHomeData(
                session.getPincode(),
                session.getUserId(),
                "0"
        ).enqueue(new Callback<HomePageDataModel>() {
            @Override
            public void onResponse(@NonNull Call<HomePageDataModel> call, @NonNull Response<HomePageDataModel> response) {
                binding.progresBar.setVisibility(View.GONE);

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                            binding.textNoData.setVisibility(View.GONE);
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            binding.recyclerView.setAdapter(new HomeCategoryAdapter(activity, response.body().getCategoryData()));
                            binding.textItemCount.setText(response.body().getData().size() + " Items found");
                        } else {
                            binding.textNoData.setVisibility(View.VISIBLE);
                            binding.recyclerView.setVisibility(View.GONE);
                            binding.textItemCount.setText("No Items found");
                        }
            }

            @Override
            public void onFailure(@NonNull Call<HomePageDataModel> call, @NonNull Throwable t) {
                binding.progresBar.setVisibility(View.GONE);
                binding.textNoData.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
                binding.textItemCount.setText("No Items found");
            }
        });

    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private void search(String keyWord) {
        binding.progresBar.setVisibility(View.VISIBLE);
        apiInterface.searchProduct(
                keyWord,
                "0"
        ).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(@NonNull Call<SearchModel> call, @NonNull Response<SearchModel> response) {
                binding.progresBar.setVisibility(View.GONE);

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            binding.textNoData.setVisibility(View.GONE);
                            binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                            binding.textItemCount.setText(response.body().getData().size() + " Items found");
                            binding.recyclerView.setAdapter(new SearchProductAdapter(activity, response.body().getData()));
                        } else {
                            binding.textNoData.setVisibility(View.VISIBLE);
                            binding.recyclerView.setVisibility(View.GONE);
                            binding.textItemCount.setText("No Items found");
                        }
            }

            @Override
            public void onFailure(@NonNull Call<SearchModel> call, @NonNull Throwable t) {
                binding.textNoData.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
                binding.progresBar.setVisibility(View.GONE);
                binding.textItemCount.setText("No Items found");
            }
        });

    }

}