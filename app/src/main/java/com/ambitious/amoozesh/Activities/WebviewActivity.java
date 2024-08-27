package com.ambitious.amoozesh.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ambitious.amoozesh.R;

import java.io.File;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_Bck;
    private TextView tv_Head;
    private WebView view_Web;
    private RelativeLayout rl_Loader;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        finds();

        if (getIntent().getStringExtra("typ").equalsIgnoreCase("About")) {
            tv_Head.setText(getString(R.string.aboutus));
            url = "https://www.amoozeshapp.com/about-us";
        } else {
            tv_Head.setText(getString(R.string.privacypolicy));
            url = "https://www.freeprivacypolicy.com/live/8bf9ef9f-237b-422b-8f26-7a1406e1251e";
        }

        rl_Loader.setVisibility(View.VISIBLE);
        view_Web.setWebViewClient(new AppWebViewClients());
        view_Web.getSettings().setJavaScriptEnabled(true);

        // Clear all the Application Cache, Web SQL Database and the HTML5 Web Storage
        WebStorage.getInstance().deleteAllData();

        view_Web.clearCache(true);
        view_Web.clearFormData();
        view_Web.clearHistory();
        view_Web.clearSslPreferences();
        if (Build.VERSION.SDK_INT >= 19) {
            view_Web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        //myWeb.loadUrl(url);
        if (url.contains("pdf") || url.contains("doc") || url.contains("xlsx")) {
            File file = new File(url);
            view_Web.getSettings().setAllowFileAccess(true);
            view_Web.getSettings().setAllowContentAccess(true);
            view_Web.loadUrl(file.getAbsolutePath());
        } else {
            view_Web.postUrl(url, null);
        }

    }

    private void finds() {

        iv_Bck = findViewById(R.id.iv_Bck);
        tv_Head = findViewById(R.id.tv_Head);
        view_Web = findViewById(R.id.view_Web);
        rl_Loader = findViewById(R.id.rl_Laoder);

        iv_Bck.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.iv_Bck) {
                onBackPressed();
        }



//        switch (view.getId()) {
//
//            case R.id.iv_Bck:
//                onBackPressed();
//                break;
//
//        }

    }

    @Override
    public void onBackPressed() {
        if (view_Web != null) {
            if (view_Web.canGoBack()) {
                view_Web.goBack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            if (url.contains("mailto:")) {
                Log.e("url", "" + url);
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {url.split(":")[1]};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
                url = getIntent().getExtras().getString("url");
                view.postUrl(url, null);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            rl_Loader.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            rl_Loader.setVisibility(View.GONE);
        }
    }
}