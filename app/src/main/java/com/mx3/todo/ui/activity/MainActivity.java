package com.mx3.todo.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mx3.todo.R;
import com.mx3.todo.databinding.ActivityMainBinding;
import com.mx3.todo.databinding.DialogAddToDoBinding;
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
            mMainViewModel.updateToDoItem(dataItem);
        });
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void setupViewModelObservations() {
        mMainViewModel.getToDoItemListLiveData().observe(this, toDoItems -> {
            mAdapter.setDataList(toDoItems);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_add) {
            openAddToDoDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAddToDoDialog() {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle(R.string.add_to_do);

        final DialogAddToDoBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.dialog_add_to_do, null, false);
        builder.setView(binding.getRoot());
        binding.setViewModel(mMainViewModel);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mMainViewModel.insertToDoItem();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
