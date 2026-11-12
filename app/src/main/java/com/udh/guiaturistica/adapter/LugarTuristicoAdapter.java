package com.udh.guiaturistica.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udh.guiaturistica.R;
import com.udh.guiaturistica.modelo.LugarTuristico;

import java.util.List;

public class LugarTuristicoAdapter extends RecyclerView.Adapter<LugarTuristicoAdapter.ViewHolder> {

    private final List<LugarTuristico> listaLugares;

    public LugarTuristicoAdapter(List<LugarTuristico> listaLugares) {
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
        LugarTuristico lugar = listaLugares.get(position);
        holder.tvNombre.setText(lugar.getNombre());
        holder.tvDescripcion.setText(lugar.getDescripcion());
        holder.ivImagen.setImageResource(lugar.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagenLugar);
            tvNombre = itemView.findViewById(R.id.tvNombreLugar);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionLugar);
        }
    }
}
