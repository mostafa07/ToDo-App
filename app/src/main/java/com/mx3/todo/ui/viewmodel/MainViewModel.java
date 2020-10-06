package com.mx3.todo.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mx3.todo.data.model.ToDoItem;
import com.mx3.todo.data.repository.ToDoItemsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private ToDoItemsRepository mToDoItemsRepository;
    private LiveData<List<ToDoItem>> mToDoItemListMutableLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mToDoItemsRepository = ToDoItemsRepository.getInstance(application);
        mToDoItemListMutableLiveData = new MutableLiveData<>();

        // retrieve to do items once
        getToDoItems();
    }

    public void getToDoItems() {
        mToDoItemListMutableLiveData = mToDoItemsRepository.getToDoItemsLiveData();
    }

    // Getters and Setters

    public LiveData<List<ToDoItem>> getToDoItemListLiveData() {
        return mToDoItemListMutableLiveData;
    }
}
