package com.mx3.todo.webservice;

import com.mx3.todo.data.model.ToDoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ToDoItemsWebService {

    String TODOS_ENDPOINT = "todos";

    @GET(TODOS_ENDPOINT)
    Call<List<ToDoItem>> getRemoteSampleTodoItems();
}
