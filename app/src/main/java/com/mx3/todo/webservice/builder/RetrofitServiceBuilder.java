package com.mx3.todo.webservice.builder;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceBuilder {

    private static final String LOG_TAG = RetrofitServiceBuilder.class.getSimpleName();
    private static final String SAMPLES_BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit;

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(LOG_TAG, message));
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder().baseUrl(SAMPLES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(okHttpClientBuilder.build())
                .build();
    }

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
