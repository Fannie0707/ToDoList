package com.example.todolist.back.bdd;



import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    okhttp3.ResponseBody body = response.body();
                    if (body == null){
                        callback.onError(new Exception("Body null"));
                        return;
                    }
                    String json = body.string();
                    if (json.equals("[]")){
                        callback.onError(new Exception("Empty body"));
                        return;
                    }
                    callback.onSuccess(json);
                } else {
                    callback.onError(new Exception("HTTP error " + response.code()));
                }
            }
        });
    }

    public void rechercheDonne(String nomTable, Parametre[] parametres, SupabaseCallback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SUPABASE_URL + "/rest/v1/"+nomTable).newBuilder();

        if (parametres != null){
            for (Parametre p : parametres) {
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

    public void supprimerDonne(String nomTable, Parametre[] parametres, SupabaseCallback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SUPABASE_URL + "/rest/v1/"+nomTable).newBuilder();

        if (parametres != null){
            for (Parametre p : parametres) {
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

    public SupabaseCallback rechercheUtilisateurs(TextView v){
        return new SupabaseCallback() {
            @Override
                public void onSuccess(String json) {
                v.setOnClickListener(a->{
                    Toast.makeText(
                            v.getContext(),
                            "Utilisateurs trouvé",
                            Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }
}

