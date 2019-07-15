package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        mWebView=(WebView)findViewById(R.id.about_alc);

       WebSettings webSettings=mWebView.getSettings();
       webSettings.setJavaScriptEnabled(true);

       mWebView.setWebViewClient(new MyWebViewClient());

       mWebView.loadUrl("https://andela.com/alc/");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
           // super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           // return super.shouldOverrideUrlLoading(view, url);
            if(Uri.parse(url).getHost().equals("https://andela.com/alc/")){
                return false;}
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}



