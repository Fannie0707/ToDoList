package com.example.todolist.back.bdd;



import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SupabaseService {

    private static final String SUPABASE_URL = "https://clibtdmmtppmefgurknv.supabase.co";
    private static final String API_KEY = "sb_publishable_UQe7gN2ojGRyxFfFuRv1sQ_0oL730XD";
    private static final OkHttpClient client = new OkHttpClient();

    public void fetchData(String nomTable, Parameters[] parametres, SupabaseCallback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SUPABASE_URL + "/rest/v1/"+nomTable).newBuilder();

        // Add all query parameters
        for (Parameters p : parametres) {
            urlBuilder.addQueryParameter(p.column, p.operator.getValue() + p.value);
        }

        HttpUrl url = urlBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Accept", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String json = response.body().string();
                    callback.onSuccess(json);
                } else {
                    callback.onError(new Exception("HTTP error " + response.code()));
                }
            }
        });
    }
}

