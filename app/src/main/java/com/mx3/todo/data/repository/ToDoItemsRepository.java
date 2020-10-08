package com.mx3.todo.data.repository;

import android.app.Application;
import android.os.AsyncTask;

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

    public void insertToDoItem(final ToDoItem toDoItem) {
        new InsertToDoItemAsyncTask().execute(toDoItem);
    }

    // I still know.. this is "very" old and not used anymore, but it is just for the demo
    private class InsertToDoItemAsyncTask extends AsyncTask<ToDoItem, Void, Void> {

        public InsertToDoItemAsyncTask() {
        }

        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            if (toDoItems != null && toDoItems.length != 0) {
                mToDoItemDao.insert(toDoItems[0]);
            }
            return null;
        }
    }
}
