package com.example.emergencyalert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import Entidad.Reportes;

public class ReportesAdapter extends ArrayAdapter<Reportes> {

    private Context context;
    private List<Reportes> reportesList;

    public ReportesAdapter(Context context, List<Reportes> reportesList) {
        super(context, R.layout.item_reporte, reportesList);
        this.context = context;
        this.reportesList = reportesList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_reporte, null);
        }

        // Obtener el informe en la posición actual
        Reportes reporte = reportesList.get(position);

        if (reporte != null) {
            // Personalizar la vista según tus necesidades
            TextView nombreTextView = view.findViewById(R.id.textViewNombre);
            TextView apellidosTextView = view.findViewById(R.id.textViewApellidos);
            TextView telefonoTextView = view.findViewById(R.id.textViewTelefono);
            TextView descipcionTextView = view.findViewById(R.id.textViewDescripcion);

            nombreTextView.setText(reporte.getNombre());
            apellidosTextView.setText(reporte.getApellidos());
            telefonoTextView.setText(reporte.getTelefono());
            descipcionTextView.setText(reporte.getDescripcion());
        }

        return view;
    }
}

