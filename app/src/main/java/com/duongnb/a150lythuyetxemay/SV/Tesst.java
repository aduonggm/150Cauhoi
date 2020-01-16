package com.duongnb.a150lythuyetxemay.SV;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.duongnb.a150lythuyetxemay.Base.KeepLogin;
import com.duongnb.a150lythuyetxemay.LoginActivity;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.R;

import java.util.ArrayList;

public class Tesst extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesst);
        final ArrayList<Questions> arrayList = new ArrayList<>();

        textView = findViewById(R.id.tv_tess);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tesst.this, TLCauHoi.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_out,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnu_logout){
            LogOut();
        }
        return super.onOptionsItemSelected(item);
    }
    void LogOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Tesst.this);
        builder.setMessage("Bạn Có Muốn Đăng Xuất");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                KeepLogin.setKeepLogin(false,getApplicationContext());
                KeepLogin.setUser(0,getApplicationContext());
                Intent intent = new Intent(Tesst.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }

}
