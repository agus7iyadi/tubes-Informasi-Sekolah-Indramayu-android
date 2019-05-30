package com.d3ti2a.informasisekolahindramayu;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DataModel dataModel= (DataModel) bundle.getSerializable("dataModel");
        // setting judul bar
        setTitle(dataModel.getJudul());

        //tampil data konten
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/"+dataModel.getKonten());
    }
}
