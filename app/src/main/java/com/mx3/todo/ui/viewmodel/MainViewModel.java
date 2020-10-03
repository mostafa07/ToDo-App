package com.mx3.todo.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mx3.todo.data.model.ToDoItem;
import com.mx3.todo.data.repository.ToDoItemsRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private ToDoItemsRepository mToDoItemsRepository;
    private MutableLiveData<List<ToDoItem>> mToDoItemListMutableLiveData;

    public MainViewModel() {
        mToDoItemsRepository = ToDoItemsRepository.getInstance();
        mToDoItemListMutableLiveData = new MutableLiveData<>();

        // retrieve to do items once
        getToDoItems();
    }

    public void getToDoItems() {
        mToDoItemsRepository.getRemoteToDoItems().subscribe(toDoItems -> {
            mToDoItemListMutableLiveData.setValue(toDoItems);
        }, Throwable::printStackTrace);
    }

    // Getters and Setters

    public LiveData<List<ToDoItem>> getToDoItemListLiveData() {
        return mToDoItemListMutableLiveData;
    }
}
