package com.example.lambda_school_loaner_47.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    //Use weak References to avoid leaking context from Activity
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
