package com.example.meugatosemnome.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.activities.TelaOpcaoGato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterGatos extends RecyclerView.Adapter<RecyclerViewAdapterGatos.ViewHolder> {

    View viewOnCreate;
    ViewHolder viewHolderLocal;
    Context context;
    List<String> ids = new ArrayList<String>();
    List<String> sexos = new ArrayList<String>();
    List<String> doencas = new ArrayList<String>();
    List<String> descricoes = new ArrayList<String>();
    List<String> castrados = new ArrayList<String>();
    List<String> filhotes = new ArrayList<String>();
    List<String> idades = new ArrayList<String>();
    List<String> fotos = new ArrayList<String>();


    public RecyclerViewAdapterGatos(Context contextRecebido,
                                    String[] idsRecebidos,
                                    String[] sexosRecebidos,
                                    String[] doencasRecebidas,
                                    String[] descricoesRecebidas,
                                    String[] castradosRecebidos,
                                    String[] filhotesRecebidos,
                                    String[] idadesRecebidas,
                                    String[] fotosRecebidas) {
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        sexos.addAll(Arrays.asList(sexosRecebidos));
        doencas.addAll(Arrays.asList(doencasRecebidas));
        descricoes.addAll(Arrays.asList(descricoesRecebidas));
        castrados.addAll(Arrays.asList(castradosRecebidos));
        filhotes.addAll(Arrays.asList(filhotesRecebidos));
        idades.addAll(Arrays.asList(idadesRecebidas));
        fotos.addAll(Arrays.asList(fotosRecebidas));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtId;
        public TextView txtSexo;
        public TextView txtdoencas;
        public TextView txtDescricao;
        public TextView txtCastrado;
        public TextView txtFilhotes;
        public TextView txtIdade;
        public CircleImageView imgGato;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.id);
            txtSexo = itemView.findViewById(R.id.sexo);
            txtdoencas = itemView.findViewById(R.id.doenca);
            txtDescricao = itemView.findViewById(R.id.descricao);
            txtCastrado = itemView.findViewById(R.id.castrado);
            txtFilhotes = itemView.findViewById(R.id.filhotes);
            txtIdade = itemView.findViewById(R.id.idade);
            imgGato = itemView.findViewById(R.id.imgGato);
        }

        @Override
        public void onClick(View v) {

        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapterGatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(parent.getContext()).inflate(R.layout.gato_linhas, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterGatos.ViewHolder holder, int position) {
        holder.txtId.setText(ids.get(position));
        holder.txtSexo.setText(sexos.get(position));
        holder.txtdoencas.setText(doencas.get(position));
        holder.txtDescricao.setText(descricoes.get(position));
        holder.txtCastrado.setText(castrados.get(position));
        holder.txtFilhotes.setText(filhotes.get(position));
        holder.txtIdade.setText(idades.get(position));
        if (fotos.get(position) != null) {
            if (!fotos.get(position).equals("")) {
                byte[] imagemEmBytes;
                imagemEmBytes = Base64.decode(fotos.get(holder.getAdapterPosition()), Base64.DEFAULT);
                Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imagemEmBytes, 0, imagemEmBytes.length);
                holder.imgGato.setImageBitmap(imagemDecodificada);
            } else {
                holder.imgGato.setImageResource(R.drawable.iconegato);
            }
        }

        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();

                Intent intent = new Intent(context, TelaOpcaoGato.class);
                intent.putExtra("id", ids.get(adapterPosition));
                intent.putExtra("sexos", sexos.get(adapterPosition));
                intent.putExtra("doencas", doencas.get(adapterPosition));
                intent.putExtra("descricao", descricoes.get(adapterPosition));
                intent.putExtra("castrado", castrados.get(adapterPosition));
                intent.putExtra("filhotes", filhotes.get(adapterPosition));
                intent.putExtra("idade", idades.get(adapterPosition));
                intent.putExtra("foto", fotos.get(adapterPosition));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
}
