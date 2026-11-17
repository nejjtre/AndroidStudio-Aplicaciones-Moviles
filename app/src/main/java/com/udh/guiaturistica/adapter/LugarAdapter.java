package com.udh.guiaturistica.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udh.guiaturistica.R;
import com.udh.guiaturistica.controller.DetalleLugarActivity;
import com.udh.guiaturistica.modelo.LugarItem;

import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {

    private List<LugarItem> listaLugares;

    public LugarAdapter(List<LugarItem> listaLugares) {
        this.listaLugares = listaLugares;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lugar_turistico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LugarItem lugar = listaLugares.get(position);
        holder.tvNombre.setText(lugar.getNombre());
        holder.tvTipo.setText("Categoría: " + lugar.getTipo());
        holder.tvHorario.setText(lugar.getHorarioTexto());
        holder.tvDescripcion.setText(lugar.getDescripcion());
        holder.ivImagen.setImageResource(lugar.getImagenResId());

        View.OnClickListener listener = v -> {
            Intent intent = new Intent(v.getContext(), DetalleLugarActivity.class);
            intent.putExtra("nombre", lugar.getNombre());
            intent.putExtra("tipo", lugar.getTipo());
            intent.putExtra("horario", lugar.getHorarioTexto());
            intent.putExtra("descripcion", lugar.getDescripcion());
            intent.putExtra("imagenResId", lugar.getImagenResId());
            v.getContext().startActivity(intent);
        };

        holder.itemView.setOnClickListener(listener);
        holder.btnPublicarResena.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvTipo;
        TextView tvHorario;
        TextView tvDescripcion;
        Button btnPublicarResena;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagenLugar);
            tvNombre = itemView.findViewById(R.id.tvNombreLugar);
            tvTipo = itemView.findViewById(R.id.tvTipoLugar);
            tvHorario = itemView.findViewById(R.id.tvHorarioLugar);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionLugar);
            btnPublicarResena = itemView.findViewById(R.id.btnPublicarResena);
        }
    }
}
