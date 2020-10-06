package com.mx3.todo.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mx3.todo.data.database.dao.ToDoItemDao;
import com.mx3.todo.data.model.ToDoItem;
import com.mx3.todo.webservice.ToDoItemsWebService;
import com.mx3.todo.webservice.builder.RetrofitServiceBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

@Database(entities = {ToDoItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final String TODO_APP_DATABASE_NAME = "todo_database";

    private static AppDatabase sAppDatabase;

    protected AppDatabase() {
    }

    public static AppDatabase getInstance(final Context context) {
        synchronized (AppDatabase.class) {
            if (sAppDatabase == null) {
                sAppDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, TODO_APP_DATABASE_NAME)
                        .addCallback(populateCallback)
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return sAppDatabase;
        }
    }

    public abstract ToDoItemDao toDoItemDao();


    private static RoomDatabase.Callback populateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            ToDoItemsWebService toDoItemsWebService = RetrofitServiceBuilder.buildService(ToDoItemsWebService.class);
            toDoItemsWebService.getRemoteSampleTodoItems().enqueue(new retrofit2.Callback<List<ToDoItem>>() {
                @Override
                public void onResponse(@NotNull Call<List<ToDoItem>> call, @NotNull Response<List<ToDoItem>> response) {
                    if (response.isSuccessful()) {
                        new InsertToDoItemsAsyncTask().execute(response.body());
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<ToDoItem>> call, @NotNull Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    };

    // I know this is "very" old and not used anymore, but it is just for the demo
    private static class InsertToDoItemsAsyncTask extends AsyncTask<List<ToDoItem>, Void, Void> {

        public InsertToDoItemsAsyncTask() {
        }

        @Override
        protected Void doInBackground(final List<ToDoItem>... lists) {
            sAppDatabase.toDoItemDao().insertAll(lists[0]);
            return null;
        }
    }
}
