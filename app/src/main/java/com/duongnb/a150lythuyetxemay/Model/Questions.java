package com.duongnb.a150lythuyetxemay.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Questions implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("cauHoi")
    @Expose
    private String cauHoi;
    @SerializedName("cauTL1")
    @Expose
    private String cauTL1;
    @SerializedName("cauTL2")
    @Expose
    private String cauTL2;
    @SerializedName("cauTL3")
    @Expose
    private String cauTL3;
    @SerializedName("cauTL4")
    @Expose
    private String cauTL4;
    @SerializedName("dapAn")
    @Expose
    private String dapAn;
    @SerializedName("chuThich")
    @Expose
    private String chuThich;
    @SerializedName("Image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getCauTL1() {
        return cauTL1;
    }

    public void setCauTL1(String cauTL1) {
        this.cauTL1 = cauTL1;
    }

    public String getCauTL2() {
        return cauTL2;
    }

    public void setCauTL2(String cauTL2) {
        this.cauTL2 = cauTL2;
    }

    public String getCauTL3() {
        return cauTL3;
    }

    public void setCauTL3(String cauTL3) {
        this.cauTL3 = cauTL3;
    }

    public String getCauTL4() {
        return cauTL4;
    }

    public void setCauTL4(String cauTL4) {
        this.cauTL4 = cauTL4;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
