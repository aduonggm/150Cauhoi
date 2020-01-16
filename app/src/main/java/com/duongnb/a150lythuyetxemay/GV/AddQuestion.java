package com.duongnb.a150lythuyetxemay.GV;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.Network.API;
import com.duongnb.a150lythuyetxemay.Network.RetrofitClient;
import com.duongnb.a150lythuyetxemay.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestion extends AppCompatActivity {
    EditText edtCauHoi,edtCauTL1,edtCauTL2,edtCauTL3,edtCauTL4 , edtChuThich;
    CheckBox cb1,cb2,cb3,cb4;
    Button btnAdd , btnUpdate , btnDelete;
    ImageView img;
    Questions question;
    Bitmap bitmap;
    Uri filePath;
    boolean ImgResult = false ;
    int  uploadImg = 1;
    String ImgUrl , base64_string;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        Intent intent = getIntent();
        int a = intent.getIntExtra("add",0);
          question = (Questions) intent.getSerializableExtra("question");

        if (a ==1){
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Tạo Câu Hỏi");
        }else if (a==2){
            setView(question);
            btnDelete.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Chỉnh Sửa");
        }else {

        }


        OnclickButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }

    void init(){
        edtChuThich = findViewById(R.id.edt_chu_thich);
        edtCauHoi = findViewById(R.id.edt_cau_hoi);
        edtCauTL1 = findViewById(R.id.edt_cau_tl_1);
        edtCauTL2 = findViewById(R.id.edt_cau_tl_2);
        edtCauTL3 = findViewById(R.id.edt_cau_tl_3);
        edtCauTL4 = findViewById(R.id.edt_cau_tl_4);
        cb1 = findViewById(R.id.cb_1);
        cb2 = findViewById(R.id.cb_2);
        cb3 = findViewById(R.id.cb_3);
        cb4 = findViewById(R.id.cb_4);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate  = findViewById(R.id.btn_update);
        img  = findViewById(R.id.img_ex);

    }


    void OnclickButton(){

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ImgResult == true){
                    pushImage();
                    if (uploadImg == 1){
                        ImgUrl = "http://lolautochess.tk/newApp/upload/"+getFileName(filePath);
                    }else ImgUrl = "";
                }else ImgUrl = "";

                Log.d("1222", "onClick: " + uploadImg +" "+ ImgResult);


                 StringBuilder stringBuilder = new StringBuilder();

                if (cb1.isChecked()){
                    stringBuilder.append(1);
                    stringBuilder.append(".");
                }
                if (cb2.isChecked()){
                    stringBuilder.append(2);
                    stringBuilder.append(".");

                }
                if (cb3.isChecked()){
                    stringBuilder.append(3);
                    stringBuilder.append(".");

                }
                if (cb4.isChecked()){
                    stringBuilder.append(4);
                    stringBuilder.append(".");
                }

                if (edtCauHoi.getText().toString().isEmpty() || edtCauTL1.getText().toString().isEmpty()
                        || edtCauTL2.getText().toString().isEmpty()
                        || stringBuilder.toString().isEmpty()){
                    Toast.makeText(AddQuestion.this, "Vui Lòng Nhập Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else AddQuesstion(stringBuilder,"");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ImgResult == true){

                    pushImage();
                    if (uploadImg == 0){
                        ImgUrl = "";
                    }else {
                        ImgUrl = "http://lolautochess.tk/newApp/upload/"+getFileName(filePath);
                    }
                }else if (question.getImage() != null){
                    ImgUrl = question.getImage();
                }else ImgUrl = "";

                StringBuilder stringBuilder = new StringBuilder();

                if (cb1.isChecked()){
                    stringBuilder.append(1);
                    stringBuilder.append(".");
                }
                if (cb2.isChecked()){
                    stringBuilder.append(2);
                    stringBuilder.append(".");

                }
                if (cb3.isChecked()){
                    stringBuilder.append(3);
                    stringBuilder.append(".");

                }
                if (cb4.isChecked()){
                    stringBuilder.append(4);
                    stringBuilder.append(".");
                }
                String Id = String.valueOf(question.getId());

                if (edtCauHoi.getText().toString().isEmpty() || edtCauTL1.getText().toString().isEmpty()
                        || edtCauTL2.getText().toString().isEmpty()
                        || stringBuilder.toString().isEmpty()){
                    Toast.makeText(AddQuestion.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else AddQuesstion(stringBuilder,Id);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              DialodDelete();
            }
        });

    }


    void pushImage(){
            getStringImage(bitmap);
        RetrofitClient.getCilent().create(API.class).addImage(getFileName(filePath),base64_string).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {
                Toast.makeText(AddQuestion.this, "Upload Ảnh Thành Công", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                uploadImg --;
                Toast.makeText(AddQuestion.this, " Upload  Ảnh không thành công", Toast.LENGTH_SHORT).show();
            }
        });
//        String url = "http://lolautochess.tk/newApp/addImage.php";
//         RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(AddQuestion.this, response.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<>();
//
//                params.put("img",ImgUrl);
//                params.put("name",nameImg);
//
//
//                return params;
//
//            }
//        };
//        requestQueue.add(stringRequest);

    }

    void AddQuesstion(final StringBuilder stringBuilder, final String Id){
        final String cauhoi = edtCauHoi.getText().toString().trim();
        final String cautl1 = edtCauTL1.getText().toString().trim();
        final String cautl2 = edtCauTL2.getText().toString().trim();
        final String cautl3 = edtCauTL3.getText().toString().trim();
        final String cautl4 = edtCauTL4.getText().toString().trim();
        final String ChuThich = edtChuThich.getText().toString().trim();

        RetrofitClient.getCilent().create(API.class).update(Id,cauhoi,
                cautl1,cautl2,cautl3,cautl4,
                ChuThich,stringBuilder.toString(),ImgUrl).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

             if (response.body().toString().contains("1")){
                 Toast.makeText(AddQuestion.this, "Thêm thành Công", Toast.LENGTH_SHORT).show();
                 BackA();
             }
             if (response.body().toString().contains("2")){
                 Toast.makeText(AddQuestion.this, "Câu Hỏi Đã Tồn Tại Không Thể Thêm", Toast.LENGTH_SHORT).show();
             }
                if (response.body().toString().contains("0")){
                    Toast.makeText(AddQuestion.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
                if (response.body().toString().contains("-1")){
                    Toast.makeText(AddQuestion.this, "Đã Xóa", Toast.LENGTH_SHORT).show();
                    BackA();
                }
                if (response.body().toString().contains("3")){
                    Toast.makeText(AddQuestion.this, "Đã Cập Nhật", Toast.LENGTH_SHORT).show();
                    BackA();
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Lỗi", Toast.LENGTH_SHORT).show();

            }
        });



    }
    void setView(Questions question){
        edtCauHoi.setText(question.getCauHoi()+"");
        edtCauTL1.setText(question.getCauTL1()+"");
        edtCauTL2.setText(question.getCauTL2()+"");
        edtCauTL3.setText(question.getCauTL3()+"");
        edtCauTL4.setText(question.getCauTL4()+"");
        edtChuThich.setText(question.getChuThich()+"");

        if (question.getImage().isEmpty()){

        }else {
            Picasso.with(getApplicationContext()).load(question.getImage()).error(R.drawable.ic_camera).into(img);
        }
        String str = question.getDapAn();
        String[] array  = str.split("\\.");


        for (int i = 0 ; i< array.length ; i++){
            switch (Integer.parseInt(array[i])){
                case 1:
                    cb1.setChecked(true);
                    break;
                case 2:
                    cb2.setChecked(true);
                    break;
                case 3:
                    cb3.setChecked(true);
                    break;
                case 4:
                    cb4.setChecked(true);
                    break;
            }
        }
        Log.d("aaaa", "setView: " + array + "string " + str);
       

    }

 void DialodDelete(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddQuestion.this);
        alertDialog.setMessage("Bạn Có Chắc Muốn Xóa Câu Hỏi Này");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("aa");
                AddQuesstion(stringBuilder ,"-1");
                Intent intent = new Intent(getApplicationContext(),ListQuestion.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
 }


 void BackA(){
//        Intent intent = new Intent(getApplicationContext(),ListQuestion.class);
//        startActivity(intent);
        finish();
 }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                ImgResult = true;
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                img.setImageBitmap(bitmap);
                double x = (int) bitmap.getWidth();
                double y = (int) bitmap.getHeight();
                double tyle = x / y;
                bitmap = Bitmap.createScaledBitmap(bitmap, 600, (int) (600/tyle), true);
//                max_size = byteSizeOf(bitmap);
//                fileName = getFileName(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //Chuyen bitmap image sang string bang base64
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        base64_string = encodedImage;
        return encodedImage;
    }
    //getNameImg
    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


}
