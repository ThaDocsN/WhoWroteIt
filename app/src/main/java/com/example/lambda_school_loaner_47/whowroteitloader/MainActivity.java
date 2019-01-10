package com.example.lambda_school_loaner_47.whowroteitloader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

// TODO: 1/10/19 start on 2.7

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput  = findViewById(R.id.bookInput);
        mTitleText  = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        //Get the search string from the input field
        String queryString = mBookInput.getText().toString();
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0){
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText(getString(R.string.loading));

        }else {
            if (queryString.length() == 0){
                mAuthorText.setText("");
                mTitleText.setText(getString(R.string.no_search_term));
            }else {
                mAuthorText.setText("");
                mTitleText.setText(getString(R.string.no_network));
            }
        }
    }
}
