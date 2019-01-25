package com.jsondemo.android;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler
{
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler()
    {

    }
    public String makeServiceCall(String requrl)
    {
        String response = null;
        try
        {
            URL url = new URL(requrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        }
        catch (Exception e)
        {

        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
