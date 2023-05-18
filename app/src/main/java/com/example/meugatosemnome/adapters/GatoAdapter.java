package com.example.meugatosemnome.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;

public class GatoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Gato> itens;

    public GatoAdapter(Context context, ArrayList<Gato> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflater = LayoutInflater.from(context).inflate(R.layout.gato_linhas, parent,
                false);
        TextView txtId = inflater.findViewById(R.id.id);
        TextView txtdoencas = inflater.findViewById(R.id.doenca);
        // ImageView imageGato = inflater.findViewById(R.id.imgGato);
        TextView txtDescricao = inflater.findViewById(R.id.descricao);
        TextView txtFilhotes = inflater.findViewById(R.id.filhotes);
        TextView txtIdade = inflater.findViewById(R.id.idade);

        txtId.setText(itens.get(position).getId());
        txtdoencas.setText(itens.get(position).isDoencas() ? "Sim" : "Não");
        // imageGato.setImageDrawable(itens.get(position).getFoto());
        txtDescricao.setText(itens.get(position).getDescricaoDoencas());
        txtFilhotes.setText(itens.get(position).isDoencas() ? "Sim" : "Não");
        txtIdade.setText(itens.get(position).getIdade());

        return inflater;
    }
}
