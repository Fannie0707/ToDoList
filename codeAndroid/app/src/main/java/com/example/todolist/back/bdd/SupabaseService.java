package com.example.todolist.back.bdd;



import com.example.todolist.back.Entite;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseService {

    private static final String SUPABASE_URL = "https://clibtdmmtppmefgurknv.supabase.co";
    private static final String API_KEY = "sb_publishable_UQe7gN2ojGRyxFfFuRv1sQ_0oL730XD";
    private static final OkHttpClient client = new OkHttpClient();

    private void envoieRequete(Request request, SupabaseCallback callback){
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

    public void rechercheDonne(String nomTable, Parameters[] parametres, SupabaseCallback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SUPABASE_URL + "/rest/v1/"+nomTable).newBuilder();

        if (parametres != null){
            for (Parameters p : parametres) {
                urlBuilder.addQueryParameter(p.column, p.operator.getValue() + p.value);
            }
        }

        HttpUrl url = urlBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Accept", "application/json")
                .build();
        envoieRequete(request, callback);

    }

    public void insererDonne(String nomTable, Entite entite, SupabaseCallback callback) {
        HttpUrl url = HttpUrl.parse(SUPABASE_URL + "/rest/v1/" + nomTable);
        if (url == null){ return ;}

        JSONObject jsonBody = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : entite.convertionMap().entrySet()) {
                jsonBody.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            callback.onError(e);
            return;
        }

        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=representation")
                .build();
        envoieRequete(request, callback);
    }

    public void supprimerDonne(String nomTable, Parameters[] parametres, SupabaseCallback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SUPABASE_URL + "/rest/v1/"+nomTable).newBuilder();

        if (parametres != null){
            for (Parameters p : parametres) {
                urlBuilder.addQueryParameter(p.column, p.operator.getValue() + p.value);
            }
        }

        HttpUrl url = urlBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Accept", "application/json")
                .build();
        envoieRequete(request, callback);
    }
}

