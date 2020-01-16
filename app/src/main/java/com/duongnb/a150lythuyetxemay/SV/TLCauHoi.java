package com.duongnb.a150lythuyetxemay.SV;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.duongnb.a150lythuyetxemay.Base.Adapterss;
import com.duongnb.a150lythuyetxemay.GV.ListQuestion;
import com.duongnb.a150lythuyetxemay.Model.QuestionResult;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.Model.Selected;
import com.duongnb.a150lythuyetxemay.Base.ViewPagerAdapter;
import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.Network.API;
import com.duongnb.a150lythuyetxemay.Network.RetrofitClient;
import com.duongnb.a150lythuyetxemay.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TLCauHoi extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView tv , tvTimer;
    CountDownTimer countDownTimer;
    boolean stop = false;
    public static Boolean Checked = false;
   static long time;
   static long endTime =  900000;
    long min = 0, sec = 0;
    public static Map<Integer, Selected> map = new HashMap();
    public static HashMap hashMapDapAn = new HashMap();
    ArrayList<Questions> arrData = new ArrayList<>();
    private int dung = 0;
    private int sai = 0;
    NumberFormat f = new DecimalFormat("00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlcau_hoi);
        init();
        ReadJson("http://lolautochess.tk/newApp/getList.php");
        init();

    }

    void init() {
//
//        adapter = new AdapterQuestion(arrData,getApplicationContext());
//        listView.setAdapter(adapter);

       tv = findViewById(R.id.tvv);
       tv.setText("Nộp Bài");
       tvTimer = findViewById(R.id.tv_count_time);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);
           countDownTimer = new CountDownTimer(endTime, 1000) {
            public void onTick(long millisUntilFinished) {

                if (stop != false){
                    countDownTimer.cancel();
                }else {
                    //     long hour = (millisUntilFinished / 3600000) % 24;
                    time =millisUntilFinished;
                    min = (millisUntilFinished / 60000) % 60;
                    sec = (millisUntilFinished / 1000) % 60;
                    tvTimer.setText( f.format(min) + ":" + f.format(sec));
                }
            }

            public void onFinish() {
                tvTimer.setText("00:00");
                KiemTraKQ();
                Dialog();
            }
        };
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBack();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void ReadJson(String url) {

        RetrofitClient.getCilent().create(API.class).getLisst().enqueue(new Callback<QuestionResult>() {
            @Override
            public void onResponse(Call<QuestionResult> call, Response<QuestionResult> response) {
                arrData.clear();
                arrData.addAll(response.body().getResult());
                setFragment();
                countDownTimer.start();

            }

            @Override
            public void onFailure(Call<QuestionResult> call, Throwable t) {
                Toast.makeText(TLCauHoi.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                arrData.clear();
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject object = response.getJSONObject(i);
//                        arrData.add(new Question(
//                                object.getInt("Id"),
//                                object.getString("cauHoi"),
//                                object.getString("cauTL1"),
//                                object.getString("cauTL2"),
//                                object.getString("cauTL3"),
//                                object.getString("cauTL4"),
//                                object.getString("chuThich"),
//                                object.getString("dapAn"),
//                                object.getString("Image")));
//                    } catch (JSONException e) {
//
//                    }
//                }
//
//
//                 setFragment();
//                countDownTimer.start();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Lỗi :" + error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);

    }


    void setFragment() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < arrData.size(); i++) {


            viewPagerAdapter.addFragmnet(FragmentQuestion.newInstace(arrData.get(i),i),"Câu"+(i+1));


        }


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    void Dialog() {

        Adapterss adapterss = new Adapterss(arrData, getApplicationContext());

        final Dialog dialog = new Dialog(TLCauHoi.this, R.style.Theme_AppCompat_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_dap_an);
        TextView tvTrue = dialog.findViewById(R.id.tv_dung);
        TextView tvPass = dialog.findViewById(R.id.tv_pass);
        TextView tvFalse = dialog.findViewById(R.id.tv_sai);
        TextView tvOut = dialog.findViewById(R.id.tv_out);
        TextView tvTime = dialog.findViewById(R.id.tv_time);
        GridView gridView = dialog.findViewById(R.id.gv);

        dialog.setCancelable(false);

        NumberFormat ss = new DecimalFormat("00");
        long m = 0, s = 0;
        m = ((endTime - time)/ 60000) % 60;
        s = ((endTime - time) / 1000) % 60;
        tvTime.setText( ss.format(m) + ":" + ss.format(s));
        gridView.setAdapter(adapterss);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogShowKQ(position);
            }
        });

        tvOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                TLCauHoi.this.finish();
            }
        });


        tvTrue.setText("Đúng: " + (arrData.size() - sai));
        tvFalse.setText("Sai: " + sai);
        if (sai > 4) tvPass.setText("Trượt");
        else tvPass.setText("Đỗ");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    void KiemTraKQ() {

        for (int i = 0; i < arrData.size(); i++) {
            Questions question = arrData.get(i);
            Selected selected = map.get(Integer.parseInt(arrData.get(i).getId()));
            if (selected == null) {
                hashMapDapAn.put(i, "1");
                sai++;
            } else {
                boolean Checkec = selected.kiemTraKetQua(question.getDapAn());
                if (!Checkec) {
                    sai++;
                    hashMapDapAn.put(i, "1");
                } else hashMapDapAn.put(i, "2");

            }
        }
    }

    void DialogShowKQ(Integer position) {
        Questions question = arrData.get(position);
        final Dialog dialog = new Dialog(TLCauHoi.this, R.style.Theme_AppCompat_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_anser);
        TextView tvCauHoi = dialog.findViewById(R.id.tv_dialog_question);
        TextView tvTL1 = dialog.findViewById(R.id.tv_dialog_cauhoi1);
        TextView tvTL2 = dialog.findViewById(R.id.tv_dia_cauhoi2);
        TextView tvTL3 = dialog.findViewById(R.id.tv_dia_cauhoi3);
        TextView tvTL4 = dialog.findViewById(R.id.tv_dia_cauhoi4);
        LinearLayout ln3 = dialog.findViewById(R.id.ln_dl_3);
        LinearLayout ln4 = dialog.findViewById(R.id.ln_dl_4);
        ImageView img = dialog.findViewById(R.id.img_dialog);
        TextView tvChuThich = dialog.findViewById(R.id.tv_dia_chuthich);


        CheckBox cb1 = dialog.findViewById(R.id.cb_dia_1);
        CheckBox cb2 = dialog.findViewById(R.id.cb_dia_2);
        CheckBox cb3 = dialog.findViewById(R.id.cb_dia_3);
        CheckBox cb4 = dialog.findViewById(R.id.cb_dia_4);

        tvCauHoi.setText("Câu " +(position+1) + ": "+question.getCauHoi());
        if (question.getImage().isEmpty()){
            img.setVisibility(View.GONE);
        }else  Picasso.with(getApplicationContext()).load(question.getImage()).error(R.drawable.ic_camera).into(img);
        tvTL1.setText(question.getCauTL1());
        tvTL2.setText(question.getCauTL2());
        if (question.getCauTL3().isEmpty()){
            ln3.setVisibility(View.GONE);
        }else tvTL3.setText(question.getCauTL3());
        if (question.getCauTL4().isEmpty()){
            ln4.setVisibility(View.GONE);
        }else tvTL4.setText(question.getCauTL4());
        if (question.getChuThich().isEmpty()){
            tvChuThich.setVisibility(View.GONE);
        }else  tvChuThich.setText("Chú Thích: "+question.getChuThich() + "");

        Selected selected = map.get(Integer.parseInt(question.getId()));

        if (selected != null) {
            if (selected.isTraloi1()) {
                cb1.setChecked(true);
            }
            if (selected.isTraloi2()) {
                cb2.setChecked(true);
            }
            if (selected.isTraloi3()) {
                cb3.setChecked(true);
            }
            if (selected.isTraloi4()) {
                cb4.setChecked(true);
            }


            if (selected.isTraloi1() && !question.getDapAn().contains("1")) {
                tvTL1.setTextColor(Color.RED);
            } else if (selected.isTraloi1() && question.getDapAn().contains("1")) {
                tvTL1.setTextColor(Color.GREEN);
            } else if (!selected.isTraloi1() && question.getDapAn().contains("1")) {
                tvTL1.setTextColor(Color.YELLOW);
            }
            if (selected.isTraloi2() && !question.getDapAn().contains("2")) {
                tvTL2.setTextColor(Color.RED);
            } else if (selected.isTraloi2() && question.getDapAn().contains("2")) {
                tvTL2.setTextColor(Color.GREEN);
            } else if (!selected.isTraloi2() && question.getDapAn().contains("2")) {
                tvTL2.setTextColor(Color.YELLOW);
            }
            if (selected.isTraloi3() && !question.getDapAn().contains("3")) {
                tvTL3.setTextColor(Color.RED);
            } else if (selected.isTraloi3() && question.getDapAn().contains("3")) {
                tvTL3.setTextColor(Color.GREEN);
            } else if (!selected.isTraloi3() && question.getDapAn().contains("3")) {
                tvTL3.setTextColor(Color.YELLOW);
            }
            if (selected.isTraloi4() && !question.getDapAn().contains("4")) {
                tvTL4.setTextColor(Color.RED);
            } else if (selected.isTraloi4() && question.getDapAn().contains("4")) {
                tvTL4.setTextColor(Color.GREEN);
            } else if (!selected.isTraloi4() && question.getDapAn().contains("4")) {
                tvTL4.setTextColor(Color.YELLOW);
            }
        } else {
            String string = question.getDapAn();
           if (string.isEmpty()){}else {
               String[] s = string.split("\\.");
               for (int i = 0; i < s.length; i++) {
                   switch (Integer.parseInt(s[i])) {
                       case 1:
                           tvTL1.setTextColor(Color.YELLOW);
                           cb1.setChecked(true);
                           break;
                       case 2:
                           tvTL2.setTextColor(Color.YELLOW);
                           cb2.setChecked(true);
                           break;
                       case 3:
                           tvTL3.setTextColor(Color.YELLOW);
                           cb3.setChecked(true);
                           break;
                       case 4:
                           tvTL4.setTextColor(Color.YELLOW);
                           cb4.setChecked(true);
                           break;
                   }

               }
           }
        }

        dialog.show();


    }
    void onBack(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TLCauHoi.this);
        builder.setMessage("Bạn Có Muốn Kết thúc Bài Thi?");
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stop= true;
                KiemTraKQ();
                Dialog();

            }
        });
        builder.show();
    }


    @Override
    public void onBackPressed() {

    onBack();
    }


}
