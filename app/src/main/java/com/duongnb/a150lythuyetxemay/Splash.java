package com.duongnb.a150lythuyetxemay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.duongnb.a150lythuyetxemay.Base.KeepLogin;
import com.duongnb.a150lythuyetxemay.GV.ListQuestion;
import com.duongnb.a150lythuyetxemay.SV.Tesst;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (KeepLogin.getKeepLogin(getApplicationContext())){
            if (KeepLogin.getUser(this) ==1){
                Intent intent = new Intent(getApplicationContext(), ListQuestion.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(getApplicationContext(), Tesst.class);
                startActivity(intent);
                finish();
            }
        }else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
