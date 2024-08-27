package com.ambitious.amoozesh.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ambitious.amoozesh.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_More;
    private LinearLayout ll_Vocabulary, ll_Grammer, ll_Communication, ll_Books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        finds();
    }

    private void finds() {

        iv_More = findViewById(R.id.iv_More);
        ll_Vocabulary = findViewById(R.id.ll_Vocabulary);
        ll_Grammer = findViewById(R.id.ll_Grammer);
        ll_Communication = findViewById(R.id.ll_Communication);
        ll_Books = findViewById(R.id.ll_Books);

        iv_More.setOnClickListener(this);
        ll_Vocabulary.setOnClickListener(this);
        ll_Grammer.setOnClickListener(this);
        ll_Communication.setOnClickListener(this);
        ll_Books.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();

        if (viewId == R.id.ll_Vocabulary) {
            startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Vocabulary"));
        } else if (viewId == R.id.ll_Grammer) {
            startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Grammer"));
        } else if (viewId == R.id.ll_Communication) {
            startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Communication"));
        } else if (viewId == R.id.ll_Books) {
            startActivity(new Intent(mContext, BooksActivity.class));
        } else if (viewId == R.id.iv_More) {
            startActivity(new Intent(mContext, SettingsActivity.class));
        }

//        switch (view.getId()) {
//
//            case R.id.ll_Vocabulary:
//                startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Vocabulary"));
//                break;
//
//            case R.id.ll_Grammer:
//                startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Grammer"));
//                break;
//
//            case R.id.ll_Communication:
//                startActivity(new Intent(mContext, QuizActivity.class).putExtra("typ", "Communication"));
//                break;
//
//            case R.id.ll_Books:
//                startActivity(new Intent(mContext, BooksActivity.class));
//                break;
//
//            case R.id.iv_More:
//                startActivity(new Intent(mContext, SettingsActivity.class));
//                break;
//
//        }

    }
}