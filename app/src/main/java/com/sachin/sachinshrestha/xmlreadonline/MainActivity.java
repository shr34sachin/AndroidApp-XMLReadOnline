package com.sachin.sachinshrestha.xmlreadonline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnLoad;
    TextView tvDisplayContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplayContent = (TextView)findViewById(R.id.tvDisplayContent);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (isOnline()){
                    requestData("http://services.hanselandpetal.com/feeds/flowers.xml");
                } else{
                    Toast.makeText(getApplicationContext(),"Network isn't available", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // check whether the network is availed or not
    // Note that the permissions, ACCESS_NETWORK_STATE and INTERNET should be set first in manifest file
    private boolean isOnline(){
        ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        } else{
            return false;
        }
    }

    protected void requestData(String uri) {
        String content = HttpManager.getData(uri);

        Toast.makeText(getApplicationContext(),content, Toast.LENGTH_LONG).show();
//        Log.d("MYTAG",content);
//        if (content.isEmpty())
//            Log.d("MYTAG","Empty content");
//        else
//            Log.d("MYTAG",content);

    }
}
