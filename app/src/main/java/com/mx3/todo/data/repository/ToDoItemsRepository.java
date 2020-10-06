package com.mx3.todo.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mx3.todo.data.database.AppDatabase;
import com.mx3.todo.data.database.dao.ToDoItemDao;
import com.mx3.todo.data.model.ToDoItem;

import java.util.List;

public class ToDoItemsRepository {

    private static final String TAG = ToDoItemsRepository.class.getSimpleName();

    private static ToDoItemsRepository sToDoItemsRepository;

    private AppDatabase mAppDatabase;
    private ToDoItemDao mToDoItemDao;

    private LiveData<List<ToDoItem>> mToDoItemListLiveData;

    private ToDoItemsRepository(final Application application) {
        mAppDatabase = AppDatabase.getInstance(application);
        mToDoItemDao = mAppDatabase.toDoItemDao();

        mToDoItemListLiveData = mToDoItemDao.getAll();
    }

    public static ToDoItemsRepository getInstance(final Application application) {
        synchronized (ToDoItemsRepository.class) {
            if (sToDoItemsRepository == null) {
                sToDoItemsRepository = new ToDoItemsRepository(application);
            }
            return sToDoItemsRepository;
        }
    }

    // Just for the demo
    public LiveData<List<ToDoItem>> getToDoItemsLiveData() {
        return mToDoItemListLiveData;
    }
}
