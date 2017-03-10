package com.example.lorran.contatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lorran.contatos.objetos.Usuario;
import com.lorran.contatos.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        List<Usuario> listaUsuariosPesquisados = new ArrayList<Usuario>();

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT  nome " +
                           ", email " +
                     "FROM usuario";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        for (int i=0; i < cursor.getCount(); i++){
            Usuario user = new Usuario();
            user.setNome(cursor.getString(0));
            user.setEmail(cursor.getString(1));

            listaUsuariosPesquisados.add(user);

            cursor.moveToNext();
        }


        ListView lista = (ListView) findViewById(R.id.listaUsuarios_lista);
        UsuarioAdapter adaptador = new UsuarioAdapter(this, 0, listaUsuariosPesquisados);
        lista.setAdapter(adaptador);
    }
}
