package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.loginsqlite.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class json extends AppCompatActivity {

    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json);

        browser = (WebView) findViewById(R.id.webkit);
        try {
            URL pageURL = new URL(getString(R.string.url));
            trustAllHosts();
            HttpURLConnection con = (HttpURLConnection) pageURL.openConnection();
            String jsonString = getString(R.string.json);

            jsonString = "{\"userId\":\"SS3025N\",\"detailList\":[{\"name\":\"Blissful Trail\",\"date\":\"20/11/2022\",\"distance\":\"19km\",\"level\":\"Medium\",\"location\":\"Rickmansworth\",\"parking\":\"Available\",\"suitability\":\"Child Friendly\"}," +
                    "{\"name\":\"Jungle Trek\",\"date\":\"05/12/2022\",\"distance\":\"23km\",\"level\":\"Easy\",\"location\":\"Chesam the chess valley park\",\"parking\":\"Not Available\",\"suitability\":\"Pet Friendly\"},"+
                    "{\"name\":\"Leisure\",\"date\":\"07/12/2022\",\"distance\":\"15km\",\"level\":\"Medium\",\"location\":\"Rickmansworth\",\"parking\":\"Available\",\"suitability\":\"Pet Friendly\"},"+
                    "{\"name\":\"Night Owl\",\"date\":\"03/12/2022\",\"distance\":\"20km\",\"level\":\"Hard\",\"location\":\"Lewes\",\"parking\":\"Available\",\"suitability\":\"Child Friendly\"},"+
                    "{\"name\":\"Starry Night\",\"date\":\"01/12/2022\",\"distance\":\"9km\",\"level\":\"Easy\",\"location\":\"Chesam the chess valley park\",\"parking\":\"Available\",\"suitability\":\"Wheel Chair Friendly\"},"+
                    "{\"name\":\"Weekend Gataway\",\"date\":\"13/12/2022\",\"distance\":\"17km\",\"level\":\"Medium\",\"location\":\"Lewes\",\"parking\":\"Available\",\"suitability\":\"Pram Friendly\"}]}";


            JsonThread myTask = new JsonThread(this, con, jsonString);
            Thread t1 = new Thread(myTask, "JSON Thread");
            t1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }

                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }
                };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class JsonThread implements Runnable{
        private AppCompatActivity activity;
        private HttpURLConnection con;
        private String jsonPayLoad;
        public JsonThread(AppCompatActivity activity, HttpURLConnection con, String jsonPayload)
        {
            this.activity = activity;
            this.con = con;
            this.jsonPayLoad = jsonPayload;
        }

        public void run()
        {
            String response = "";
            if (prepareConnection()) {
                response = postJson();
            } else {
                response = "Error preparing the connection";
            }
            showResult(response);
        }

        private void showResult(String response) {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    String page = generatePage(response);
                    Log.i("jsonString", page);
                    //Log.i("log", page);
                    //Log.i("log1", page);
                    ((json)activity).browser.loadData(page,"text/html", "UTF-8");
                }
            });

        }

        private String generatePage(String content) {
            return "<html><body><p>" + content + "</p></body></html>";
        }

        private String postJson() {
            String response = "";
            try {
                String postParameters = "jsonpayload="+ URLEncoder.encode(jsonPayLoad, "UTF-8");
                con.setFixedLengthStreamingMode(postParameters.getBytes().length);
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(postParameters);
                out.close();
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    response = readStream(con.getInputStream());
                } else {
                    response = "Error contacting server: " + responseCode;
                }
            } catch (Exception e) {
                response = "Error executing code";
            }
            //Toast.makeText(json.this, "Data Added", Toast.LENGTH_SHORT).show();

            return response;
        }

        private String readStream(InputStream in) {
            StringBuilder sb = new StringBuilder();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
            {
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        private boolean prepareConnection() {
            try {
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
                return true;
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            return false;
        }


    }
}

