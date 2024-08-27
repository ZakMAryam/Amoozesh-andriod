package com.ambitious.amoozesh.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambitious.amoozesh.R;

public class BooksActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_Bck;
    private TextView tv_Kiterunner, tv_Pride, tv_Tokill, tv_Thealchemist, tv_Nineteen, tv_Athousand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        finds();
    }

    private void finds() {

        iv_Bck = findViewById(R.id.iv_Bck);
        tv_Kiterunner = findViewById(R.id.tv_Kiterunner);
        tv_Pride = findViewById(R.id.tv_Pride);
        tv_Tokill = findViewById(R.id.tv_Tokill);
        tv_Thealchemist = findViewById(R.id.tv_Thealchemist);
        tv_Nineteen = findViewById(R.id.tv_Nineteen);
        tv_Athousand = findViewById(R.id.tv_Athousand);

        iv_Bck.setOnClickListener(this);
        tv_Kiterunner.setOnClickListener(this);
        tv_Pride.setOnClickListener(this);
        tv_Tokill.setOnClickListener(this);
        tv_Thealchemist.setOnClickListener(this);
        tv_Nineteen.setOnClickListener(this);
        tv_Athousand.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        if (viewId == R.id.iv_Bck) {
            onBackPressed();
        } else if (viewId == R.id.tv_Kiterunner) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Kiterunner.getText().toString()));
        } else if (viewId == R.id.tv_Pride) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Pride.getText().toString()));
        } else if (viewId == R.id.tv_Tokill) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Tokill.getText().toString()));
        } else if (viewId == R.id.tv_Thealchemist) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Thealchemist.getText().toString()));
        } else if (viewId == R.id.tv_Nineteen) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Nineteen.getText().toString()));
        } else if (viewId == R.id.tv_Athousand) {
            startActivity(new Intent(mContext, BookDetailActivity.class)
                    .putExtra("name", "" + tv_Athousand.getText().toString()));
        }

//        switch (view.getId()) {
//
//            case R.id.iv_Bck:
//                onBackPressed();
//                break;
//
//            case R.id.tv_Kiterunner:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Kiterunner.getText().toString()));
//                break;
//            case R.id.tv_Pride:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Pride.getText().toString()));
//                break;
//            case R.id.tv_Tokill:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Tokill.getText().toString()));
//                break;
//            case R.id.tv_Thealchemist:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Thealchemist.getText().toString()));
//                break;
//            case R.id.tv_Nineteen:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Nineteen.getText().toString()));
//                break;
//            case R.id.tv_Athousand:
//                startActivity(new Intent(mContext, BookDetailActivity.class)
//                        .putExtra("name", "" + tv_Athousand.getText().toString()));
//                break;
//
//        }

    }
}