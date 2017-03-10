package com.example.lorran.contatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lorran.contatos.utils.DatabaseHelper;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Click Listener do botão cancelar
        Button btnCancelar = (Button) findViewById(R.id.cadastro_btnCancelar);
        btnCancelar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        //Click Listener do botão casdastrar
        Button btnCadastrar = (Button) findViewById(R.id.cadastro_btnCadastrar);
        btnCadastrar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v){

                DatabaseHelper dbHelper = new DatabaseHelper(CadastroActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //Captura todos os campos IMPUT da tela
                String email = ((EditText) findViewById(R.id.cadastro_emailImput)).getText().toString();
                String nome = ((EditText) findViewById(R.id.cadastro_nomeImput)).getText().toString();
                String endereco = ((EditText) findViewById(R.id.cadastro_enderecoImput)).getText().toString();
                String senha = ((EditText) findViewById(R.id.cadastro_senhaImput)).getText().toString();


                ContentValues values = new ContentValues();
                values.put("nome", nome);
                values.put("email", email);
                values.put("endereco", endereco);
                values.put("senha", senha);
                long result = db.insert("usuario", null, values);

                Intent it = new Intent();


                it.putExtra("email", email);

                setResult(RESULT_OK, it);
                finish();
            }
        });


    }


}
