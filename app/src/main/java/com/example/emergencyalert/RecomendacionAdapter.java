package com.example.emergencyalert;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import Entidad.Recomendacion;

public class RecomendacionAdapter extends RecyclerView.Adapter<RecomendacionAdapter.ViewHolder> {
    private List<Recomendacion> recomendaciones;

    // Constructor del adaptador
    public RecomendacionAdapter(List<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    // Método para inflar el diseño del elemento y crear el ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomendaciones, parent, false);
        return new ViewHolder(itemView);
    }

    // Método para vincular los datos del objeto Recomendacion con las vistas en el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recomendacion recomendacion = recomendaciones.get(position);
        holder.textViewRecomendado.setText(recomendacion.getRecomendado());
        holder.textViewLugar.setText(recomendacion.getDireccion());
    }

    // Método para obtener la cantidad total de elementos en el conjunto de datos
    @Override
    public int getItemCount() {
        return recomendaciones.size();
    }

    // ViewHolder que representa cada elemento en la lista
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRecomendado;
        public TextView textViewLugar;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRecomendado = itemView.findViewById(R.id.textViewRecomendado);
            textViewLugar = itemView.findViewById(R.id.textViewLugar);
        }
    }
}
