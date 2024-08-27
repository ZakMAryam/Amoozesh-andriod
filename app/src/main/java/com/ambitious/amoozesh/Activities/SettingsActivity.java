package com.ambitious.amoozesh.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambitious.amoozesh.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_Bck;
    private TextView tv_Aboutus, tv_Contactus, tv_Privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        finds();
    }

    private void finds() {

        iv_Bck = findViewById(R.id.iv_Bck);
        tv_Aboutus = findViewById(R.id.tv_Aboutus);
        tv_Contactus = findViewById(R.id.tv_Contactus);
        tv_Privacy = findViewById(R.id.tv_Privacy);

        iv_Bck.setOnClickListener(this);
        tv_Aboutus.setOnClickListener(this);
        tv_Contactus.setOnClickListener(this);
        tv_Privacy.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.iv_Bck) {
            onBackPressed();
        } else if (view.getId() == R.id.tv_Aboutus) {
            String url = "https://www.amoozeshapp.com/about-us";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (view.getId() == R.id.tv_Contactus) {
            startActivity(new Intent(mContext, ContactUsActivity.class));
        } else if (view.getId() == R.id.tv_Privacy) {
            String url1 = "https://www.freeprivacypolicy.com/live/8bf9ef9f-237b-422b-8f26-7a1406e1251e";
            Intent i1 = new Intent(Intent.ACTION_VIEW);
            i1.setData(Uri.parse(url1));
            startActivity(i1);
        }

//        switch (view.getId()) {
//
//            case R.id.iv_Bck:
//                onBackPressed();
//                break;
//
//            case R.id.tv_Aboutus:
//                String url = "https://www.amoozeshapp.com/home";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//                break;
//
//            case R.id.tv_Contactus:
//                startActivity(new Intent(mContext, ContactUsActivity.class));
//                break;
//
//            case R.id.tv_Privacy:
//                String url1 = "https://www.freeprivacypolicy.com/live/8bf9ef9f-237b-422b-8f26-7a1406e1251e";
//                Intent i1 = new Intent(Intent.ACTION_VIEW);
//                i1.setData(Uri.parse(url1));
//                startActivity(i1);
//                break;
//
//        }

    }
}