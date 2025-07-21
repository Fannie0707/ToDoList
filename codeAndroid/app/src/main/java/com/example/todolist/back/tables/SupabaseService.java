package com.example.todolist.back.tables;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseService {

    private static final String SUPABASE_URL = "https://clibtdmmtppmefgurknv.supabase.co";
    private static final String API_KEY = "sb_publishable_UQe7gN2ojGRyxFfFuRv1sQ_0oL730XD";
    private static final OkHttpClient client = new OkHttpClient();

    public void fetchTasks() {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/tache")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Accept", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    System.out.println("Tasks: " + json);
                } else {
                    System.out.println("Error: " + response.code());
                }
            }
        });
    }
    public void insertTask(String title) {
        String json = "{\"title\":\"" + title + "\",\"done\":false}";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/tasks")
                .post(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=representation") // Get the inserted row back
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Insert response: " + response.body().string());
            }
        });
    }
}
