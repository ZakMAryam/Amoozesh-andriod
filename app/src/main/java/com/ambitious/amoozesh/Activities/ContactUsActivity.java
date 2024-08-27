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

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_Bck;
    private TextView tv_Mail, tv_FB, tv_Insta, tv_Twiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        finds();
    }

    private void finds() {

        iv_Bck = findViewById(R.id.iv_Bck);
        tv_Mail = findViewById(R.id.tv_Mail);
        tv_FB = findViewById(R.id.tv_FB);
        tv_Insta = findViewById(R.id.tv_Insta);
        tv_Twiter = findViewById(R.id.tv_Twiter);

        iv_Bck.setOnClickListener(this);
        tv_Mail.setOnClickListener(this);
        tv_FB.setOnClickListener(this);
        tv_Insta.setOnClickListener(this);
        tv_Twiter.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_Bck) {
            onBackPressed();
        } else if (view.getId() == R.id.tv_Mail) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "contact@amoozeshapp.com", null));
            startActivity(Intent.createChooser(emailIntent, null));
        } else if (view.getId() == R.id.tv_FB) {
            String url2 = "https://www.facebook.com/AmoozeshApp";
            Intent i2 = new Intent(Intent.ACTION_VIEW);
            i2.setData(Uri.parse(url2));
            startActivity(i2);
        } else if (view.getId() == R.id.tv_Insta) {
            String url1 = "https://www.instagram.com/amoozeshapplication/";
            Intent i1 = new Intent(Intent.ACTION_VIEW);
            i1.setData(Uri.parse(url1));
            startActivity(i1);
        } else if (view.getId() == R.id.tv_Twiter) {
            String url = "https://twitter.com/AmoozeshApp";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
//        switch (view.getId()) {
//
//            case R.id.iv_Bck:
//                onBackPressed();
//                break;
//
//            case R.id.tv_Mail:
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto", "contact@amoozeshapp.com", null));
//                startActivity(Intent.createChooser(emailIntent, null));
//                break;
//
//            case R.id.tv_FB:
//                String url2 = "https://www.facebook.com/AmoozeshApp";
//                Intent i2 = new Intent(Intent.ACTION_VIEW);
//                i2.setData(Uri.parse(url2));
//                startActivity(i2);
//                break;
//
//            case R.id.tv_Insta:
//                String url1 = "https://www.instagram.com/amoozeshapplication/";
//                Intent i1 = new Intent(Intent.ACTION_VIEW);
//                i1.setData(Uri.parse(url1));
//                startActivity(i1);
//                break;
//
//            case R.id.tv_Twiter:
//                String url = "https://twitter.com/AmoozeshApp";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//                break;
//
//        }

    }
}