package com.mx3.todo.ui.viewmodel;

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
    private MutableLiveData<ToDoItem> mToDoItemToBeAddedMutableLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mToDoItemsRepository = ToDoItemsRepository.getInstance(application);
        mToDoItemListMutableLiveData = new MutableLiveData<>();
        mToDoItemToBeAddedMutableLiveData = new MutableLiveData<>(new ToDoItem());

        mToDoItemListMutableLiveData = mToDoItemsRepository.getToDoItemsLiveData();
    }


    public void insertToDoItem() {
        final ToDoItem toDoItemToBeAdded = mToDoItemToBeAddedMutableLiveData.getValue();
        toDoItemToBeAdded.setCompleted(false);
        mToDoItemsRepository.insertToDoItem(toDoItemToBeAdded);
        mToDoItemToBeAddedMutableLiveData.setValue(new ToDoItem());
    }

    public void updateToDoItem(final ToDoItem toDoItemToBeUpdated) {
        mToDoItemsRepository.updateToDoItem(toDoItemToBeUpdated);
    }

    // Getters and Setters

    public LiveData<List<ToDoItem>> getToDoItemListLiveData() {
        return mToDoItemListMutableLiveData;
    }

    public LiveData<ToDoItem> getToDoItemToBeAddedLiveData() {
        return mToDoItemToBeAddedMutableLiveData;
    }
}
