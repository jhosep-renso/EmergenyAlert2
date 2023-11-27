package com.example.emergencyalert;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Gpt3Api {

    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions";

    private final RequestQueue queue;

    public Gpt3Api(Config.PreguntasChat context) {
        queue = Volley.newRequestQueue(context);
    }

    public void generateText(String prompt, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("temperature", 0.5);

            JSONArray jsonArrayMessages=new JSONArray();
            JSONObject jsonObjectMessages=new JSONObject();
            jsonObjectMessages.put("role", "user");
            jsonObjectMessages.put("content", prompt);
            jsonArrayMessages.put(jsonObjectMessages);

            jsonBody.put("messages", jsonArrayMessages);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonBody,
                    response -> {
                        try {
                            JSONObject message = (JSONObject) response.getJSONArray("choices")
                                    .getJSONObject(0)
                                    .get("message");
                            String text = message.getString("content");
                            listener.onResponse(text);
                        } catch (JSONException e) {
                            Log.e("Gpt3Api", "Error de respuesta", e);
                            errorListener.onErrorResponse(new VolleyError("Error de respuesta"));
                        }
                    },
                    errorListener) {

                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + Config.api_key);
                    return headers;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(request);
        } catch (JSONException e) {
            Log.e("Gpt3Api", "\n" + "error creating request request body", e);
            errorListener.onErrorResponse(new VolleyError("\n" + "error creating request"));
        }
    }

}