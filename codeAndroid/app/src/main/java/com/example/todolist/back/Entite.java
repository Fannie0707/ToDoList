package com.example.todolist.back;

public abstract class Entite {
    public String nomTable;
    public String[] valeurs;
    public String[] colonne;
    public String afficherValeur(){
        if (valeurs != null && valeurs.length > 0){
            StringBuilder msg = new StringBuilder();
            for (String v : valeurs){
                msg.append(v);
            }
            return msg.toString();
        }
        return "";
    }

    public String afficherColonnes(){
        if (colonne != null && colonne.length > 0){
            StringBuilder msg = new StringBuilder();
            for (String c : colonne){
                msg.append(c);
                if (!colonne[colonne.length - 1].equals(c)){
                    msg.append(",");
                }
            }
            return msg.toString();
        }
        return "";
    }

}
