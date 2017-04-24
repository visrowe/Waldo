package com.ara.walli;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by r4kia on 4/20/2017.
 */

public class DatabaseInsert extends AsyncTask<String,Void,String> {
    Context ctx;
    AlertDialog alertDialog;

    public DatabaseInsert(Context ctx) {
        this.ctx = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String add_string = "http://192.168.1.130/add.php";
        if(type.equals("Post")){
            try{
                String ename = params[1];
                String jname = params[2];
                String jdes = params[3];
                String jpay = params[4];
                String jlocation = params[5];
                URL url = new URL(add_string);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("employername","UTF-8")+"="+URLEncoder.encode(ename,"UTF-8")+"&"
                        + URLEncoder.encode("jobname","UTF-8")+"="+URLEncoder.encode(jname,"UTF-8")+"&"
                        + URLEncoder.encode("jobdescription","UTF-8")+"="+URLEncoder.encode(jdes,"UTF-8")+"&"
                        + URLEncoder.encode("jobpay","UTF-8")+"="+URLEncoder.encode(jpay,"UTF-8")+"&"
                        + URLEncoder.encode("joblocation","UTF-8")+"="+URLEncoder.encode(jlocation,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line= "";
                while((line=bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Job Post Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
