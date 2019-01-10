package com.example.lambda_school_loaner_47.whowroteit;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

public class NetworkUtils {
    
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    
    static String getBookInfo(String queryString){
        
        HttpURLConnection urlConnection  = null;
        BufferedReader    reader         = null;
        String            bookJsonString = null;
        
        try {
            // TODO: 1/10/19  
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            // TODO: 1/10/19  
        }
        return bookJsonString;
    }
}
