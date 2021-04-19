package com.example.solvemedis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.example.solvemedis.API_Handler;

import java.util.HashMap;

class AsyncRequester extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private API_Handler handler;
    AsyncRequester(Context givenContext){
         this.c = givenContext;
         this.handler = new API_Handler(this.c);
    }


    @Override
    protected String doInBackground(String... params) {
        // empty HashMap, if no desired Value has to be send -> could also be solved by default in func
        HashMap<String, String> param = new HashMap<>();
        String url = params[0];
        return handler.make_request(url, params[1], param);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // do something with result
    }
}
