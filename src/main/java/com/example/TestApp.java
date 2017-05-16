package com.example;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApp {
    public static final String API_URL = "https://api.github.com";

    public static void download() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        SimpleService.GitHub github = retrofit.create(SimpleService.GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<SimpleService.Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<SimpleService.Contributor> contributors = call.execute().body();
        for (SimpleService.Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }
}
