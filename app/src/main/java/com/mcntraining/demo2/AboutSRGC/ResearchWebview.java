package com.mcntraining.demo2.AboutSRGC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mcntraining.demo2.R;

public class ResearchWebview extends AppCompatActivity {
    WebView webView;
    String url=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_webview);
        webView=(WebView) findViewById(R.id.aktuu);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

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
