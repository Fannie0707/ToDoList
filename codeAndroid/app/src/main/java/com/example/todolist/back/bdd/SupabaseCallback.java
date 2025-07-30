package com.example.todolist.back.bdd;
/** Interface permettant la mise de définir les actions a effectué après la récupération des informations via l'API REST. <br>
 * Les deux fonctions à gérer les cas de réussite ou d'échecs lors de la récupération des informations**/
public interface SupabaseCallback {
    void onSuccess(String json);
    void onError(Exception e);
}
