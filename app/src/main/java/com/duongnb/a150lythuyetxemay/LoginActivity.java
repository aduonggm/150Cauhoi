package com.duongnb.a150lythuyetxemay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duongnb.a150lythuyetxemay.Base.KeepLogin;
import com.duongnb.a150lythuyetxemay.GV.ListQuestion;
import com.duongnb.a150lythuyetxemay.Network.API;
import com.duongnb.a150lythuyetxemay.Network.RetrofitClient;
import com.duongnb.a150lythuyetxemay.SV.Tesst;
import com.duongnb.a150lythuyetxemay.SV.AvtivityDangKy;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
        int RequesstCode = 123;
    Button btnLogin, btnSignUp;
    EditText edtUsserName, edtPassWord;
    ProgressBar progressBar;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Đăng Nhập");
        init();
        Login();
    }
    void  init(){
        progressBar = findViewById(R.id.prog_login);
        progressBar.setVisibility(View.GONE);
        edtPassWord  = findViewById(R.id.edt_pass_word);
        edtUsserName = findViewById(R.id.edt_user_name);
        btnLogin = findViewById(R.id.btn_login);
       btnSignUp = findViewById(R.id.btn_signup);
        checkBox = findViewById(R.id.cb_remember);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AvtivityDangKy.class);
                startActivityForResult(intent,RequesstCode);
            }
        });
    }

    void Login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPassWord.getText().toString().isEmpty()
                || edtUsserName.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Không để trống Tài Khaorn Mật Khẩu", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    Request();}
            }
        });
    }

    void Request(){
        String userName = edtUsserName.getText().toString().trim();
        String passWord = edtPassWord.getText().toString().trim();
        RetrofitClient.getCilent().create(API.class).login(userName,passWord).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {
                if (response.equals("0")){
                    Toast.makeText(LoginActivity.this, "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    if (checkBox.isChecked()){
                        KeepLogin.setKeepLogin(true,getApplicationContext());
                        KeepLogin.setUser(Integer.parseInt(response.body().toString()),getApplicationContext());
                    }else  {
                        KeepLogin.setKeepLogin(false,getApplicationContext());
                        KeepLogin.setUser(0,getApplicationContext());
                    }
                        if (Integer.parseInt(response.body().toString())==1){
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(), ListQuestion.class);
                            startActivity(intent);
                            finish();
                        }else {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(), Tesst.class);
                            startActivity(intent);
                            finish();
                        }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
//        String url = "http://lolautochess.tk/newApp/login.php";
//        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("0")){
//                    Toast.makeText(LoginActivity.this, "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }else {
//                    Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
//                    if (checkBox.isChecked()){
//                        KeepLogin.setKeepLogin(true,getApplicationContext());
//                        KeepLogin.setUser(Integer.parseInt(response),getApplicationContext());
//                    }else  {
//                        KeepLogin.setKeepLogin(false,getApplicationContext());
//                        KeepLogin.setUser(0,getApplicationContext());
//                    }
//                        if (Integer.parseInt(response)==1){
//                            progressBar.setVisibility(View.GONE);
//                            Intent intent = new Intent(getApplicationContext(), ListQuestion.class);
//                            startActivity(intent);
//                            finish();
//                        }else {
//                            progressBar.setVisibility(View.GONE);
//                            Intent intent = new Intent(getApplicationContext(), Tesst.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Lỗi Kết Nối", Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("userName",edtUsserName.getText().toString().trim());
//                params.put("passWord",edtPassWord.getText().toString().trim());
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("aaaa", "onActivityResult: " + requestCode + "  " + resultCode);
        if (requestCode==RequesstCode){
            if (resultCode==121){

                String a = data.getStringExtra("user");
                edtUsserName.setText(data.getStringExtra("user"));
                edtPassWord.setText(data.getStringExtra("pass"));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
