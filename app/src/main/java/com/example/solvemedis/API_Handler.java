package com.example.solvemedis;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class API_Handler {
    private String api_response;
    private RequestQueue queue;
    private String url = "http://127.0.0.1:35678/home/";


    API_Handler(Context context){
        this.queue = Volley.newRequestQueue(context);
    }

    public String make_request(String flag, String request_id, final HashMap<String, String> param){
        String response = "";
        switch(flag){
            case "get":
                this.get_request(request_id);
                break;
            case "put":
                if (param.isEmpty()){ return "ERROR with handling request, parameters were empty!";}
                response = this.put_request(request_id, param);
                break;
            case "post":
                if (param.isEmpty()){ return "ERROR with handling request, parameters were empty!";}
                response = this.post_request(request_id, param);
                break;
            case "delete":
                response = this.delete_request(request_id);
                break;
            default:
                response = "ERROR with flag, wrong request type send! Request send: " + flag;
                break;
        }
        return response;
    }

    public void get_request(String request_id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String s = "ERROR";
                        // TODO: Handle error
                        //Log.d("Error.Response", api_response);
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
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
                        //Log.d("Error.Response", api_response);

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
                        //Log.d("Error.Response", api_response);

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

    public String delete_request(String request_id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, this.url + request_id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        api_response = response.toString();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        //Log.d("Error.Response", api_response);
                    }
                }
        );
        queue.add(jsonObjectRequest);
        return api_response;
    }
}
