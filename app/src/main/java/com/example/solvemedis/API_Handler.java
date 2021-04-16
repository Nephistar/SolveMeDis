package com.example.solvemedis;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class API_Handler {
    private String api_response;
    RequestQueue queue;
    String url ="http://127.0.0.1:35678/home/";

    API_Handler(Context context){
        this.queue = Volley.newRequestQueue(context);
    }

    public String get_request(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error.Response", api_response);

                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return api_response;
    }

    public String get_request(String request_id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url + request_id, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error.Response", api_response);
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return api_response;
    }

    // todo: PUT and POST are the same and should not be
    // todo: ALL URLs have a request ID -> solve this b4 doing testing!
    public String put_request(String request_id, final HashMap<String, String> param){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url + request_id, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error.Response", api_response);

                    }
                }) {

            @Override
            protected Map<String, String> getParams()
            {
                return param;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return api_response;
    }
    public String post_request(String request_id, final HashMap<String, String> param){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + request_id, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error.Response", api_response);

                    }
                }) {

            @Override
            protected Map<String, String> getParams()
            {
                return param;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return api_response;
    }
}
