package com.example.rxjava.Service;

import java.io.IOException;

import com.example.rxjava.Repositories.PostRepository;
import com.example.rxjava.View.MainActivity;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getclient(final PostRepository context) {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("X-Api-Key", "$2y$10$gpin5yXpMxbjWvsnnDMhLOYO7kpZSag7CKIDndWmUeJ/4WEchaAxK")
                            .header("Content-Type", "application/json")
                            .build();

                    return chain.proceed(request);
                }
            }).addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://play.emplate.it/v8/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


        }
        return retrofit;


    }


}
