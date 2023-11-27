package com.example.emergencyalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuOpciones extends AppCompatActivity {

    Button btnSalir, btnReporte, btnLlamar, btnPreguntas, btnInforme, btnRecomendaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        btnReporte = findViewById(R.id.btnReportar);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnInforme = findViewById(R.id.btnInforme);
        btnPreguntas = findViewById(R.id.btnPreguntas);
        btnRecomendaciones = findViewById(R.id.btnRecomendaciones);
        btnSalir = findViewById(R.id.btnRegresarLogin);



        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Reporte.class);
                startActivity(intent);
                finish();
            }
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuLlamada.class);
                startActivity(intent);
                finish();
            }
        });
        btnInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Informe.class);
                startActivity(intent);
                finish();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Config.PreguntasChat.class);
                startActivity(intent);
                finish();
            }
        });
        btnRecomendaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Machine.class);
                startActivity(i);
                finish();
            }
        });
    }
}