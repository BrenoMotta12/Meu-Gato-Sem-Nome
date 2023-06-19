package com.example.meugatosemnome.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meugatosemnome.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterDoacoes extends RecyclerView.Adapter<RecyclerViewAdapterDoacoes.ViewHolder> {

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    List<String> ids = new ArrayList<String>();
    List<String> tiposProduto = new ArrayList<>();
    List<String> categorias = new ArrayList<String>();
    List<String> nomes = new ArrayList<String>();
    List<String> quantidades = new ArrayList<String>();


    public RecyclerViewAdapterDoacoes(Context contextRecebido,
                                      String[] idsRecebidos,
                                      String[] tiposProdutoRecebidos,
                                      String[] categoriasRecebidas,
                                      String[] nomesRecebidos,
                                      String[] quantidadesRecebidas) {
        contextRecebido = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        tiposProduto.addAll(Arrays.asList(tiposProdutoRecebidos));
        categorias.addAll(Arrays.asList(categoriasRecebidas));
        nomes.addAll(Arrays.asList(nomesRecebidos));
        quantidades .addAll(Arrays.asList(quantidadesRecebidas));
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtId;
        public TextView txtCategoria;
        public TextView txtNome;
        public TextView txtQuantidade;
        public CircleImageView imgDoacao;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.id);
            txtCategoria = itemView.findViewById(R.id.categoria);
            txtNome = itemView.findViewById(R.id.nome);
            txtQuantidade = itemView.findViewById(R.id.quantidade);
            imgDoacao = itemView.findViewById(R.id.imgGato);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterDoacoes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(parent.getContext()).inflate(R.layout.doacoes_linhas, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterDoacoes.ViewHolder holder, int position) {
        holder.txtId.setText(ids.get(position));
        holder.txtCategoria.setText(categorias.get(position));
        holder.txtNome.setText(nomes.get(position));
        holder.txtQuantidade.setText(quantidades.get(position));
        if (tiposProduto.get(position) == "Areia") {
            holder.imgDoacao.setImageResource(R.drawable.iconeareia);
        } else if (tiposProduto.get(position) == "Ração") {
            holder.imgDoacao.setImageResource(R.drawable.iconeracao);
        } else if (tiposProduto.get(position) == "Medicamento") {
            holder.imgDoacao.setImageResource(R.drawable.iconemedicamento);
        }

        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
}
