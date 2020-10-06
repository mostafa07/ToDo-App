package com.mx3.todo.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.mx3.todo.R;
import com.mx3.todo.databinding.ActivityMainBinding;
import com.mx3.todo.ui.adapter.ToDoItemAdapter;
import com.mx3.todo.ui.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;
    private MainViewModel mMainViewModel;
    private ToDoItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setViewModel(mMainViewModel);

        setupRecyclerView();
        setupViewModelObservations();
    }

    private void setupRecyclerView() {
        mAdapter = new ToDoItemAdapter((dataItem, position) -> {
            dataItem.toggleCompleted();
            mAdapter.updateItemAtPosition(dataItem, position);

        });
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void setupViewModelObservations() {
        mMainViewModel.getToDoItemListLiveData().observe(this, toDoItems -> {
            mAdapter.setDataList(toDoItems);
        });
    }
}
