package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mcntraining.demo2.R;

public class MyResultWebView extends AppCompatActivity {
    WebView webView,ccsuu;
   String url=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_result_web_view);
        webView=(WebView) findViewById(R.id.aktuu);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings=webView.getSettings();
        settings.setDomStorageEnabled(true);
        Bundle bundle=getIntent().getExtras();
        if(bundle==null)
        {
            url=null;
        }
        else
        {
            url=bundle.getString("URL");
            webView.loadUrl(url);
        }

    }
}
