package com.example.lambda_school_loaner_47.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String queryString){
        
        HttpURLConnection urlConnection  = null;
        BufferedReader    reader         = null;
        String            bookJsonString = null;
        
        try {
            // TODO: 1/10/19 use a uri builder to build uri
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            //convert uri to a url
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get input stream
            InputStream inputStream = urlConnection.getInputStream();

            //create buffer reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // use a string builder to hold response
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0){
                return null;
            }

            bookJsonString = builder.toString();

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            // TODO: 1/10/19
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, "Charles " + bookJsonString);
        return bookJsonString;
    }
}
