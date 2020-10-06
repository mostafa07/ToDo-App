package com.mx3.todo.webservice.builder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceBuilder {

    private static final String LOG_TAG = RetrofitServiceBuilder.class.getSimpleName();

    private static final String SAMPLES_BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES);

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SAMPLES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
