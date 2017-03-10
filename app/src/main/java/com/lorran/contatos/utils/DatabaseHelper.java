package com.lorran.contatos.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lorran on 03/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, Constantes.BANCO_DADOS, null, Constantes.VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (   _id INTEGER PRIMARY KEY" +
                                          ", nome TEXT" +
                                          ", email TEXT" +
                                          ", endereco TEXT" +
                                          ", senha TEXT" +
                                          ", foto BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
