package com.mx3.todo.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mx3.todo.data.model.ToDoItem;

import java.util.List;

@Dao
public interface ToDoItemDao {

    @Insert
    void insert(ToDoItem toDoItem);

    @Insert
    void insertAll(ToDoItem... toDoItems);

    @Insert
    void insertAll(List<ToDoItem> toDoItems);

    @Query("SELECT * FROM todo_item")
    LiveData<List<ToDoItem>> getAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ToDoItem toDoItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(ToDoItem... toDoItems);

    @Delete
    void delete(ToDoItem toDoItem);

    @Query("DELETE FROM todo_item")
    void deleteAll();
}
