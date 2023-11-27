package com.example.emergencyalert;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import Entidad.Recomendacion;

public class Machine extends AppCompatActivity {

    private static final String SERVER_URL = "http://10.0.2.2:5000/obtener_recomendaciones";
    private static final String TAG = "MachineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        // Ejemplo de comentario del lugar
        String comentarioLugar = "Comentarios sobre el lugar";

        // Realizar la solicitud POST en un hilo separado (AsyncTask)
        new HttpPostTask().execute(comentarioLugar);
    }

    // AsyncTask para manejar la solicitud POST en segundo plano
    private class HttpPostTask extends AsyncTask<String, Void, List<Recomendacion>> {

        @Override
        protected List<Recomendacion> doInBackground(String... params) {
            String comentarioLugar = params[0];
            HttpURLConnection connection = null;
            List<Recomendacion> recomendaciones = null;

            try {
                // Crear la URL
                URL url = new URL(SERVER_URL);

                // Abrir la conexión HTTP
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Crear el cuerpo de la solicitud JSON
                String jsonInputString = "{\"comentario\": \"" + comentarioLugar + "\"}";

                // Enviar el cuerpo de la solicitud
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Verificar si la respuesta es exitosa
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Leer la respuesta del servidor
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                        StringBuilder responseStringBuilder = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            responseStringBuilder.append(responseLine.trim());
                        }
                        String response = responseStringBuilder.toString();

                        // Parsear la respuesta JSON a una lista de objetos Recomendacion
                        recomendaciones = parsearRespuesta(response);
                    }
                } else {
                    Log.e(TAG, "Error en la solicitud HTTP, código de respuesta: " + responseCode);
                }
            } catch (IOException e) {
                Log.e(TAG, "Error en la solicitud HTTP: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return recomendaciones;
        }

        @Override
        protected void onPostExecute(List<Recomendacion> result) {
            if (result != null && !result.isEmpty()) {
                // Configurar el RecyclerView con el adaptador
                //RecyclerView recyclerView = findViewById(R.id.recyclerViewRecomendaciones);
                RecomendacionAdapter adapter = new RecomendacionAdapter(result);
                //recyclerView.setAdapter(adapter);
               // recyclerView.setLayoutManager(new LinearLayoutManager(Machine.this));
            } else {
                Log.i(TAG, "No se recibieron recomendaciones o la lista está vacía.");
            }
        }

        // Método para parsear la respuesta JSON a una lista de objetos Recomendacion
        private List<Recomendacion> parsearRespuesta(String jsonResponse) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Recomendacion>>() {}.getType();
            return gson.fromJson(jsonResponse, tipoLista);
        }
    }
}