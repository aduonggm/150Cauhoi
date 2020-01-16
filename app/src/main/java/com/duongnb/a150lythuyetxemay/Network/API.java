package com.duongnb.a150lythuyetxemay.Network;

import com.duongnb.a150lythuyetxemay.Model.QuestionResult;
import com.duongnb.a150lythuyetxemay.Model.Questions;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {




    @POST("getList.php")
    Call<QuestionResult> getLisst();

    @FormUrlEncoded
    @POST("addImage.php")
    Call<Integer> addImage(@Field("name") String name,
                           @Field("img") String base64);
    @FormUrlEncoded
    @POST("login.php")
    Call<Integer> login(@Field("userName") String userName,
                           @Field("passWord") String passWord);


    @FormUrlEncoded
    @POST("test.php")
    Call<Integer> update(@Field("Id") String ID,
                        @Field("cauHoi") String CauHoi,
                        @Field("cauTL1") String CauTl1,
                        @Field("cauTL2") String CauTL2,
                        @Field("cauTL3") String CauTL3,
                        @Field("cauTL4") String CauTL4,
                        @Field("chuThich") String ChuThich,
                        @Field("dapAn") String DapAn,
                        @Field("Image") String base64
                        );
}
