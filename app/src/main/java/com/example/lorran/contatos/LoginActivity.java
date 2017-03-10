package com.example.lorran.contatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lorran.contatos.objetos.Usuario;
import com.lorran.contatos.utils.Constantes;
import com.lorran.contatos.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.login_btnCadastrar);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivityForResult(it, Constantes.TELA_CADASTRO);

            }
        });

        //Click Listener do botão login
        Button btnLogin = (Button) findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText textEmail = (EditText) findViewById(R.id.login_emailImput);
                String email = textEmail.getText().toString();

                EditText textSenha = (EditText) findViewById(R.id.login_senhaImput);
                String senha = textSenha.getText().toString();

                validaSenha(email, senha);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED) {
            String email = data.getExtras().getString("email");

            EditText textEmail = (EditText) findViewById(R.id.login_emailImput);
            textEmail.setText(email);

            //TESTE DE INCLUSAO NO BANCO
            List<Usuario> result = new ArrayList<>();
            DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT _id, nome, email, endereco, senha FROM usuario", null);
            cursor.moveToFirst();
            Log.d("Teste", cursor.getString(1));


        }
    }

    private void validaSenha(String email, String senha){

        DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT senha" +
                     " FROM usuario" +
                     " WHERE email = '" + email + "'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String senhaBD = cursor.getString(0);

        if (senha.equals(senhaBD)){
            Log.d("Valida", "USUARIO AUTENTICADO COM SUCESSO");
        }else{
            Log.d("Valida", "SENHA INCORRETA");
        }

    }
}
