package com.example.lorran.contatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lorran.contatos.objetos.Usuario;

import java.util.List;

/**
 * Created by Lorran on 10/03/2017.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private LayoutInflater mInflater;
    private List<Usuario> listaItens;

    public UsuarioAdapter(Context context, int resource, List<Usuario> ItensDaLista) {
        super(context, resource, ItensDaLista);
        mInflater = LayoutInflater.from(context);
        listaItens = ItensDaLista;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Usuario user = listaItens.get(position);

        View formatoItemLista = mInflater.inflate(R.layout.celula_usuario_cadastrado, null);
        TextView nomeUsuario = (TextView) formatoItemLista.findViewById(R.id.celula_nomeUsuario);
        TextView emailUsuario = (TextView) formatoItemLista.findViewById(R.id.celula_emailUsuario);

        nomeUsuario.setText(user.getNome());
        emailUsuario.setText(user.getEmail());

        return formatoItemLista;
    }
}
