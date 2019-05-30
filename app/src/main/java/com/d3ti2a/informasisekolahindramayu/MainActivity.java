package com.d3ti2a.informasisekolahindramayu;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.d3ti2a.informasisekolahindramayu.Login.TAG_ID;
import static com.d3ti2a.informasisekolahindramayu.Login.TAG_USERNAME;

public class MainActivity extends AppCompatActivity  {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList=new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        //membuat data yang akan ditampilkan dalam list
        //file .html mengambil di folder assets
        inputData("SMKN 1 INDRAMAYU","artikel_1.html");
        inputData("SMKN 2 INDRAMAYU","artikel_2.html");
        inputData("SMPN 1 INDRAMAYU","artikel_3.html");
        inputData("SMPN 2 INDRAMAYU","artikel_4.html");
        inputData("SMAN 1 INDRAMAYU","artikel_5.html");
        inputData("SMAN 2 INDRAMAYU","artikel_6.html");
        inputData("SMAN 1 SLIYEG","artikel_7.html");
        inputData("SDN 1 JATIBARANG","artikel_8.html");
        inputData("SDN 2 JATIBARANG","artikel_9.html");
        inputData("SMKN PGRI JATIBARANG","artikel_10.html");
        inputData("SMKN 1 JATIBARANG","artikel_11.html");
        inputData("SDN 3 JATIBARANG","artikel_12.html");
        inputData("SMAN 1 SUKAGUMIWANG","artikel_13.html");
        inputData("SMK 1 PELITA JATIBARANG","artikel_14.html");
        inputData("SMK PUI JATIBARANG INDRAMAYU","artikel_15.html");


        //menampilkan data ke dalam recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this,dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);

        //menambahakan header
        DataModel headerModel = new DataModel();
        headerModel.setViewType(2);
        dataModelArrayList.add(0,headerModel);

        //menambahkan footer
        DataModel footerModel = new DataModel();
        footerModel.setViewType(3);
        dataModelArrayList.add(footerModel);
    }

    //fungsi input
    public void inputData(String judul,String konten){
        DataModel dataModel = new DataModel();
        dataModel.setJudul(judul);
        dataModel.setKonten(konten);
        dataModel.setViewType(1);
        dataModelArrayList.add(dataModel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atas, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                logoutKan();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logoutKan() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_ID, null);
        editor.putString(TAG_USERNAME, null);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Login.class);
        finish();
        startActivity(intent);
    }


}
