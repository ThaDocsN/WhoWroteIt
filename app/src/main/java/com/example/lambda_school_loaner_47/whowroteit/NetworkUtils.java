package com.example.lambda_school_loaner_47.whowroteit;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL = "https://googleapis.com/books/v1/volumes?";
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

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            // TODO: 1/10/19
        }
        return bookJsonString;
    }
}
