package com.duongnb.a150lythuyetxemay.Base;

import android.content.Context;
import android.content.SharedPreferences;

public class KeepLogin {

    public static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("newApp",Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }
    public static void setKeepLogin(Boolean isKeepLogin, Context context){
       getEditor(context).putBoolean("login",isKeepLogin).apply();
    }
    public static Boolean getKeepLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("newApp",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login",false);
    }
    public static void setUser(Integer user, Context context){
        getEditor(context).putInt("user",user).apply();
    }
    public  static int getUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("newApp",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user",0);
    }




//    public static SharedPreferences.Editor getEditor(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("android_003", Context.MODE_PRIVATE);
//        return sharedPreferences.edit();
//    }
//    public static void setKeepLogin(Boolean isKeepLogin, Context context) {
//        getEditor(context).putBoolean("is_keep_login", isKeepLogin).apply();
//    }
//
//    public static Boolean getKeepLogin(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("android_003", Context.MODE_PRIVATE);
//        return sharedPreferences.getBoolean("is_keep_login", false);
//    }
}
