package com.duongnb.a150lythuyetxemay.GV;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.duongnb.a150lythuyetxemay.Base.AdapterListQuestion;
import com.duongnb.a150lythuyetxemay.Base.KeepLogin;
import com.duongnb.a150lythuyetxemay.LoginActivity;
import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.Model.QuestionResult;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.Network.API;
import com.duongnb.a150lythuyetxemay.Network.RetrofitClient;
import com.duongnb.a150lythuyetxemay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListQuestion extends AppCompatActivity {
    AdapterListQuestion adapter;
        ArrayList<Questions> arrData = new ArrayList<>();
        SwipeRefreshLayout swipeRefreshLayout;

        ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        init();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                ReadJson("http://lolautochess.tk/newApp/getList.php");
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReadJson("http://lolautochess.tk/newApp/getList.php");
                swipeRefreshLayout.setRefreshing(true);
            }
        });



   }

   void init(){
       adapter =new AdapterListQuestion(arrData , getApplicationContext());
       lv = findViewById(R.id.lv);
       swipeRefreshLayout = findViewById(R.id.sw_list);
       lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
               intent.putExtra("add",2);
               intent.putExtra("question", arrData.get(i));
               startActivity(intent);

           }
       });

   }

   private void ReadJson(String url){


       RetrofitClient.getCilent().create(API.class).getLisst().enqueue(new Callback<QuestionResult>() {
           @Override
           public void onResponse(Call<QuestionResult> call, Response<QuestionResult> response) {
               arrData.clear();
               arrData.addAll(response.body().getResult());
               adapter.notifyDataSetChanged();
               swipeRefreshLayout.setRefreshing(false);
           }

           @Override
           public void onFailure(Call<QuestionResult> call, Throwable t) {
               Toast.makeText(ListQuestion.this, "Lỗi", Toast.LENGTH_SHORT).show();
               swipeRefreshLayout.setRefreshing(false);
           }
       });
//       RequestQueue requestQueue = Volley.newRequestQueue(this);
//       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//           @Override
//           public void onResponse(JSONArray response) {
//                arrData.clear();
//               for (int i=0 ; i< response.length() ; i++){
//                   try {
//                       JSONObject object = response.getJSONObject(i);
//                       arrData.add(new Question(
//                               object.getInt("Id"),
//                               object.getString("cauHoi"),
//                               object.getString("cauTL1"),
//                               object.getString("cauTL2"),
//                               object.getString("cauTL3"),
//                               object.getString("cauTL4"),
//                               object.getString("chuThich"),
//                               object.getString("dapAn"),
//                               object.getString("Image")));
//                   } catch (JSONException e) {
//
//                   }
//                    adapter.notifyDataSetChanged();
//                   swipeRefreshLayout.setRefreshing(false);
//               }
//           }
//       }, new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError error) {
//               Toast.makeText(ListQuestion.this, "Lỗi :" + error.toString() , Toast.LENGTH_SHORT).show();
//               swipeRefreshLayout.setRefreshing(false);
//
//           }
//       });
//       requestQueue.add(jsonArrayRequest);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_question,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){
           case R.id.mnu_add:
               Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
               intent.putExtra("add",1);
               startActivity(intent);
               break;
           case R.id.mnu_logout:
               LogOut();
               break;


       }

        return super.onOptionsItemSelected(item);
    }

    void LogOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListQuestion.this);
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
                Intent intent = new Intent(ListQuestion.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }
}
