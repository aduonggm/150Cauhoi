package com.duongnb.a150lythuyetxemay.SV;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duongnb.a150lythuyetxemay.LoginActivity;
import com.duongnb.a150lythuyetxemay.R;


import java.util.HashMap;
import java.util.Map;

public class AvtivityDangKy  extends AppCompatActivity {
    EditText edtUser,edtPass1 , edtPass2 , edtFullName , edtEmail , edtPhone ;
    Button btn;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        init();
    }

    void init(){
        progressBar = findViewById(R.id.prog_signup);
        progressBar.setVisibility(View.GONE);
        edtUser =findViewById(R.id.edt_username);
        edtPass1 = findViewById(R.id.edt_password);
        edtPass2 = findViewById(R.id.edt_confirm_pass_word);
        edtPhone = findViewById(R.id.edt_phone);
        edtFullName = findViewById(R.id.edt_full_name);
        edtEmail = findViewById(R.id.edt_emal);

        btn = findViewById(R.id.btn_sign_up);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEmail.getText().toString().isEmpty()||
                        edtUser.getText().toString().isEmpty()||
                        edtPass1.getText().toString().isEmpty()||
                        edtPass2.getText().toString().isEmpty()||
                        edtPhone.getText().toString().isEmpty()||
                        edtFullName.getText().toString().isEmpty()){
                    Toast.makeText(AvtivityDangKy.this, "Vui Lòng Nhập Đầy Đủ thông Tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (edtPass1.getText().toString().equals(edtPass2.getText().toString())){
                        progressBar.setVisibility(View.VISIBLE);
                         DangKy();
                    }else Toast.makeText(AvtivityDangKy.this, "Mật Khẩu Không trùng khớp", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }


    void DangKy(){
        String url = "http://lolautochess.tk/newApp/dang_ky.php";


        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")){
                    Toast.makeText(AvtivityDangKy.this, "Tạo Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("user",edtUser.getText().toString());
                    intent.putExtra("pass",edtPass1.getText().toString());
                    setResult(121,intent);

                    finish();
                    Log.d("aaaa", "onClick: "+ edtUser.getText().toString() +"   "+ edtPass1.getText().toString());
                }else {
                    Toast.makeText(AvtivityDangKy.this, "Lỗi ! Không thể tạo  TK", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi Kết Nối", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                    String  name = edtFullName.getText().toString().trim();
                    String  pass = edtPass1.getText().toString().trim();
                    String  user = edtUser.getText().toString().trim();
                    String  mail = edtEmail.getText().toString().trim();
                    String  phone = edtPhone.getText().toString().trim();

                params.put("userName",user);
                params.put("passWord",pass);

                    params.put("fullName",name);

                params.put("email",mail);
                params.put("phone",phone);
                params.put("type","2");




                return params;
            }
        };
        requestQueue.add(stringRequest);
    }



}
