package com.ambitious.amoozesh.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ambitious.amoozesh.Models.MyData;
import com.ambitious.amoozesh.R;
import com.ambitious.amoozesh.Utils.Utility;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private ImageView iv_Bck, iv_Refresh;
    private String typ = "";
    LinearProgressIndicator indicator_Progress;
    private TextView tv_Head, tv_Score, tv_Ehint, tv_Phint, tv_Question, tv_Optionone, tv_Optiontwo, tv_Optionthree;

    private ArrayList<MyData> vocabularies, grammers, communications;

    private int vattempt = 0, gattempt = 0, cattempt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        finds();

        typ = getIntent().getStringExtra("typ");

        if (typ.equalsIgnoreCase("Vocabulary")) {

            iv_Refresh.setImageResource(R.drawable.vrefresh);
            iv_Bck.setImageResource(R.drawable.vback);
            indicator_Progress.setIndicatorColor(Color.parseColor("#5CE1E6"));
            indicator_Progress.setTrackColor(Color.parseColor("#DCD8D8"));

            try {
                JSONArray m_jArry = new JSONArray(loadJSONFromAsset("vocabulary.json"));
                vocabularies = new ArrayList<>();
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject obj = m_jArry.getJSONObject(i);
                    JSONArray arrJson = obj.getJSONArray("answers");

                    ArrayList<String> answersList = new ArrayList<>();
                    for (int x = 0; x < arrJson.length(); x++) {
                        answersList.add(arrJson.getString(x));
                    }

                    // Shuffle the answers randomly
                    Collections.shuffle(answersList);

                    String ans1 = answersList.get(0);
                    String ans2 = answersList.get(1);
                    String ans3 = answersList.get(2);

                    vocabularies.add(new MyData(obj.optString("question"), ans1, ans2, ans3, obj.optString("correctAnswer")));
                }
                tv_Head.setText(getString(R.string.vocabulary));
                tv_Ehint.setText("Translate:");
                tv_Phint.setText("ترجمه کنید:");
                if (Utility.getSharedPreferences(mContext, "vattempt").equalsIgnoreCase("")) {
                    tv_Score.setText("Score 0/" + vocabularies.size());
                    tv_Question.setText(vocabularies.get(0).getQuestion());
                    tv_Optionone.setText(vocabularies.get(0).getAnswer1());
                    tv_Optiontwo.setText(vocabularies.get(0).getAnswer2());
                    tv_Optionthree.setText(vocabularies.get(0).getAnswer3());
                    indicator_Progress.setProgress(0);
                } else {
                    vattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "vattempt"));
                    tv_Score.setText("Score " + vattempt + "/" + vocabularies.size());
                    tv_Question.setText(vocabularies.get(vattempt).getQuestion());
                    tv_Optionone.setText(vocabularies.get(vattempt).getAnswer1());
                    tv_Optiontwo.setText(vocabularies.get(vattempt).getAnswer2());
                    tv_Optionthree.setText(vocabularies.get(vattempt).getAnswer3());
                    double d = (vattempt * 100) / vocabularies.size();
                    int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
                    indicator_Progress.setProgress(progress);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (typ.equalsIgnoreCase("Grammer")) {

            iv_Refresh.setImageResource(R.drawable.grefresh);
            iv_Bck.setImageResource(R.drawable.gback);
            indicator_Progress.setIndicatorColor(Color.parseColor("#F3C700"));
            indicator_Progress.setTrackColor(Color.parseColor("#DCD8D8"));

            try {
                JSONArray m_jArry = new JSONArray(loadJSONFromAsset("grammar.json"));
                grammers = new ArrayList<>();
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject obj = m_jArry.getJSONObject(i);
                    JSONArray arrJson = obj.getJSONArray("answers");
                    ArrayList<String> answersList = new ArrayList<>();
                    for (int x = 0; x < arrJson.length(); x++) {
                        answersList.add(arrJson.getString(x));
                    }

                    // Shuffle the answers randomly
                    Collections.shuffle(answersList);

                    String ans1 = answersList.get(0);
                    String ans2 = answersList.get(1);
                    String ans3 = answersList.get(2);
                    grammers.add(new MyData(obj.optString("question"), ans1, ans2, ans3, obj.optString("correctAnswer")));
                }
                tv_Head.setText(getString(R.string.grammer));
                tv_Ehint.setText("Answer Accordingly:");
                tv_Phint.setText("بر اساس سوال پاسخ دهید:");
                if (Utility.getSharedPreferences(mContext, "gattempt").equalsIgnoreCase("")) {
                    tv_Score.setText("Score 0/" + grammers.size());
                    tv_Question.setText(grammers.get(0).getQuestion());
                    tv_Optionone.setText(grammers.get(0).getAnswer1());
                    tv_Optiontwo.setText(grammers.get(0).getAnswer2());
                    tv_Optionthree.setText(grammers.get(0).getAnswer3());
                    indicator_Progress.setProgress(0);
                } else {
                    gattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "gattempt"));
                    tv_Score.setText("Score " + gattempt + "/" + grammers.size());
                    tv_Question.setText(grammers.get(gattempt).getQuestion());
                    tv_Optionone.setText(grammers.get(gattempt).getAnswer1());
                    tv_Optiontwo.setText(grammers.get(gattempt).getAnswer2());
                    tv_Optionthree.setText(grammers.get(gattempt).getAnswer3());
                    double d = (gattempt * 100) / grammers.size();
                    int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
                    indicator_Progress.setProgress(progress);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (typ.equalsIgnoreCase("Communication")) {

            iv_Refresh.setImageResource(R.drawable.crefresh);
            iv_Bck.setImageResource(R.drawable.cback);
            indicator_Progress.setIndicatorColor(Color.parseColor("#4CAF50"));
            indicator_Progress.setTrackColor(Color.parseColor("#DCD8D8"));

            try {
                JSONArray m_jArry = new JSONArray(loadJSONFromAsset("communication.json"));
                communications = new ArrayList<>();
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject obj = m_jArry.getJSONObject(i);
                    JSONArray arrJson = obj.getJSONArray("answers");
                    ArrayList<String> answersList = new ArrayList<>();
                    for (int x = 0; x < arrJson.length(); x++) {
                        answersList.add(arrJson.getString(x));
                    }

                    // Shuffle the answers randomly
                    Collections.shuffle(answersList);

                    String ans1 = answersList.get(0);
                    String ans2 = answersList.get(1);
                    String ans3 = answersList.get(2);
                    communications.add(new MyData(obj.optString("question"), ans1, ans2, ans3, obj.optString("correctAnswer")));
                }
                tv_Head.setText(getString(R.string.communication));
                tv_Ehint.setText("Answer Relevently:");
                tv_Phint.setText("نزدیک ترین پاسخ را به سوال بدهید:");
                if (Utility.getSharedPreferences(mContext, "cattempt").equalsIgnoreCase("")) {
                    tv_Score.setText("Score 0/" + communications.size());
                    tv_Question.setText(communications.get(0).getQuestion());
                    tv_Optionone.setText(communications.get(0).getAnswer1());
                    tv_Optiontwo.setText(communications.get(0).getAnswer2());
                    tv_Optionthree.setText(communications.get(0).getAnswer3());
                    indicator_Progress.setProgress(0);
                } else {
                    cattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "cattempt"));
                    tv_Score.setText("Score " + cattempt + "/" + communications.size());
                    tv_Question.setText(communications.get(cattempt).getQuestion());
                    tv_Optionone.setText(communications.get(cattempt).getAnswer1());
                    tv_Optiontwo.setText(communications.get(cattempt).getAnswer2());
                    tv_Optionthree.setText(communications.get(cattempt).getAnswer3());
                    double d = (cattempt * 100) / communications.size();
                    int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
                    indicator_Progress.setProgress(progress);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void finds() {

        iv_Bck = findViewById(R.id.iv_Bck);
        iv_Refresh = findViewById(R.id.iv_Refresh);
        tv_Head = findViewById(R.id.tv_Head);
        tv_Score = findViewById(R.id.tv_Score);
        tv_Ehint = findViewById(R.id.tv_Ehint);
        tv_Phint = findViewById(R.id.tv_Phint);
        tv_Question = findViewById(R.id.tv_Question);
        tv_Optionone = findViewById(R.id.tv_Optionone);
        tv_Optiontwo = findViewById(R.id.tv_Optiontwo);
        tv_Optionthree = findViewById(R.id.tv_Optionthree);
        indicator_Progress = findViewById(R.id.indicator_Progress);

        iv_Bck.setOnClickListener(this);
        iv_Refresh.setOnClickListener(this);
        tv_Optionone.setOnClickListener(this);
        tv_Optiontwo.setOnClickListener(this);
        tv_Optionthree.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.iv_Bck) {
                onBackPressed();
        }
        else if(view.getId() == R.id.tv_Optionone){
                            if (typ.equalsIgnoreCase("Vocabulary")) {
                    if (tv_Optionone.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
                        vattempt++;
                        tv_Optionone.setBackgroundColor(Color.parseColor("#20BCBD"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        vattempt++;
                        tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
                            nextVQuestion(vattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Grammer")) {
                    if (tv_Optionone.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
                        gattempt++;
                        tv_Optionone.setBackgroundColor(Color.parseColor("#F3C700"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        gattempt++;
                        tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
                            nextGQuestion(gattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Communication")) {
                                if (tv_Optionone.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
                                    cattempt++;
                                    tv_Optionone.setBackgroundColor(Color.parseColor("#4CAF50"));
                                    tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                    tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                } else {
                                    cattempt++;
                                    tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
                                    tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                    tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
                                        nextCQuestion(cattempt);
                                    }
                                }, 500);
                            }
        }  else if(view.getId() == R.id.tv_Optiontwo){
                if (typ.equalsIgnoreCase("Vocabulary")) {
                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
                        vattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#20BCBD"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        vattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
                            nextVQuestion(vattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Grammer")) {
                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
                        gattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F3C700"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        gattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
                            nextGQuestion(gattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Communication")) {
                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
                        cattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#4CAF50"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        cattempt++;
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
                            nextCQuestion(cattempt);
                        }
                    }, 500);
                }

        }
        else if(view.getId() == R.id.tv_Optionthree){
                if (typ.equalsIgnoreCase("Vocabulary")) {
                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
                        vattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#20BCBD"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        vattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
                            nextVQuestion(vattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Grammer")) {
                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
                        gattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F3C700"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        gattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
                            nextGQuestion(gattempt);
                        }
                    }, 500);
                } else if (typ.equalsIgnoreCase("Communication")) {
                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
                        cattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#4CAF50"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        cattempt++;
                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
                            nextCQuestion(cattempt);
                        }
                    }, 500);
                }

        }else{
                if (typ.equalsIgnoreCase("Vocabulary")) {
                    Utility.setSharedPreference(mContext, "vattempt", "0");
                    vattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "vattempt"));
                    nextVQuestion(vattempt);
                } else if (typ.equalsIgnoreCase("Grammer")) {
                    Utility.setSharedPreference(mContext, "gattempt", "0");
                    gattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "gattempt"));
                    nextGQuestion(gattempt);
                } else if (typ.equalsIgnoreCase("Communication")) {
                    Utility.setSharedPreference(mContext, "cattempt", "0");
                    cattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "cattempt"));
                    nextCQuestion(cattempt);
                }
        }




//        switch (view.getId()) {
//
//            case R.id.iv_Bck:
//                onBackPressed();
//                break;
//
//            case R.id.tv_Optionone:
//                if (typ.equalsIgnoreCase("Vocabulary")) {
//                    if (tv_Optionone.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
//                        vattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#20BCBD"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        vattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
//                            nextVQuestion(vattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Grammer")) {
//                    if (tv_Optionone.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
//                        gattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#F3C700"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        gattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
//                            nextGQuestion(gattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Communication")) {
//                    if (tv_Optionone.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
//                        cattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#4CAF50"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        cattempt++;
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
//                            nextCQuestion(cattempt);
//                        }
//                    }, 500);
//                }
//                break;
//
//            case R.id.tv_Optiontwo:
//                if (typ.equalsIgnoreCase("Vocabulary")) {
//                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
//                        vattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#20BCBD"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        vattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
//                            nextVQuestion(vattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Grammer")) {
//                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
//                        gattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F3C700"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        gattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
//                            nextGQuestion(gattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Communication")) {
//                    if (tv_Optiontwo.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
//                        cattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#4CAF50"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        cattempt++;
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
//                            nextCQuestion(cattempt);
//                        }
//                    }, 500);
//                }
//                break;
//
//            case R.id.tv_Optionthree:
//                if (typ.equalsIgnoreCase("Vocabulary")) {
//                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(vocabularies.get(vattempt).getCorrectAnswer())) {
//                        vattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#20BCBD"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        vattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "vattempt", "" + vattempt);
//                            nextVQuestion(vattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Grammer")) {
//                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(grammers.get(gattempt).getCorrectAnswer())) {
//                        gattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F3C700"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        gattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "gattempt", "" + gattempt);
//                            nextGQuestion(gattempt);
//                        }
//                    }, 500);
//                } else if (typ.equalsIgnoreCase("Communication")) {
//                    if (tv_Optionthree.getText().toString().equalsIgnoreCase(communications.get(cattempt).getCorrectAnswer())) {
//                        cattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#4CAF50"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        cattempt++;
//                        tv_Optionthree.setBackgroundColor(Color.parseColor("#F44336"));
//                        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utility.setSharedPreference(mContext, "cattempt", "" + cattempt);
//                            nextCQuestion(cattempt);
//                        }
//                    }, 500);
//                }
//                break;
//
//            case R.id.iv_Refresh:
//                if (typ.equalsIgnoreCase("Vocabulary")) {
//                    Utility.setSharedPreference(mContext, "vattempt", "0");
//                    vattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "vattempt"));
//                    nextVQuestion(vattempt);
//                } else if (typ.equalsIgnoreCase("Grammer")) {
//                    Utility.setSharedPreference(mContext, "gattempt", "0");
//                    gattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "gattempt"));
//                    nextGQuestion(gattempt);
//                } else if (typ.equalsIgnoreCase("Communication")) {
//                    Utility.setSharedPreference(mContext, "cattempt", "0");
//                    cattempt = Integer.parseInt(Utility.getSharedPreferences(mContext, "cattempt"));
//                    nextCQuestion(cattempt);
//                }
//                break;
//        }

    }

    private void nextVQuestion(int vattempt) {

        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Score.setText("Score " + vattempt + "/" + vocabularies.size());
        tv_Question.setText(vocabularies.get(vattempt).getQuestion());
        tv_Optionone.setText(vocabularies.get(vattempt).getAnswer1());
        tv_Optiontwo.setText(vocabularies.get(vattempt).getAnswer2());
        tv_Optionthree.setText(vocabularies.get(vattempt).getAnswer3());
        double d = (vattempt * 100) / vocabularies.size();
        int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
        indicator_Progress.setProgress(progress);

    }

    private void nextGQuestion(int gattempt) {

        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Score.setText("Score " + gattempt + "/" + grammers.size());
        tv_Question.setText(grammers.get(gattempt).getQuestion());
        tv_Optionone.setText(grammers.get(gattempt).getAnswer1());
        tv_Optiontwo.setText(grammers.get(gattempt).getAnswer2());
        tv_Optionthree.setText(grammers.get(gattempt).getAnswer3());
        double d = (gattempt * 100) / grammers.size();
        int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
        indicator_Progress.setProgress(progress);

    }

    private void nextCQuestion(int cattempt) {

        tv_Optiontwo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionone.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Optionthree.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_Score.setText("Score " + cattempt + "/" + communications.size());
        tv_Question.setText(communications.get(cattempt).getQuestion());
        tv_Optionone.setText(communications.get(cattempt).getAnswer1());
        tv_Optiontwo.setText(communications.get(cattempt).getAnswer2());
        tv_Optionthree.setText(communications.get(cattempt).getAnswer3());
        double d = (cattempt * 100) / communications.size();
        int progress = Integer.parseInt(String.valueOf(d).split("\\.")[0]);
        indicator_Progress.setProgress(progress);

    }

    public String loadJSONFromAsset(String name) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open(name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}