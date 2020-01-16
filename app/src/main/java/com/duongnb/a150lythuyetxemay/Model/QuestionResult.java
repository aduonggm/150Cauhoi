package com.duongnb.a150lythuyetxemay.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResult {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("result")
    @Expose
    private List<Questions> result = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Questions> getResult() {
        return result;
    }

    public void setResult(List<Questions> result) {
        this.result = result;
    }
}
