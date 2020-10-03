package com.mx3.todo.data.repository;

import com.mx3.todo.data.model.ToDoItem;
import com.mx3.todo.webservice.ToDoItemsWebService;
import com.mx3.todo.webservice.builder.RetrofitServiceBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

public class ToDoItemsRepository {

    private static final String TAG = ToDoItemsRepository.class.getSimpleName();

    private static ToDoItemsRepository sToDoItemsRepository;
    private ToDoItemsWebService mToDoItemsWebService;

    private ToDoItemsRepository() {
        mToDoItemsWebService = RetrofitServiceBuilder.buildService(ToDoItemsWebService.class);
    }

    public static ToDoItemsRepository getInstance() {
        synchronized (ToDoItemsRepository.class) {
            if (sToDoItemsRepository == null) {
                sToDoItemsRepository = new ToDoItemsRepository();
            }
            return sToDoItemsRepository;
        }
    }


    public Observable<List<ToDoItem>> getRemoteToDoItems() {
        return Observable.create(subscriber -> {
            mToDoItemsWebService.getRemoteSampleTodoItems().enqueue(new Callback<List<ToDoItem>>() {
                @Override
                public void onResponse(@NotNull Call<List<ToDoItem>> call, @NotNull Response<List<ToDoItem>> response) {
                    if (response.isSuccessful()) {
                        subscriber.onNext(response.body());
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<ToDoItem>> call, @NotNull Throwable t) {
                    subscriber.onError(t);
                }
            });
        });
    }
}
