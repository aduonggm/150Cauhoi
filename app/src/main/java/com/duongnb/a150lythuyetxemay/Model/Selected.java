package com.duongnb.a150lythuyetxemay.Model;

public class Selected {

    private String causo;
    private boolean traloi1;
    private boolean traloi2;
    private boolean traloi3;
    private boolean traloi4;

    public Selected(String causo, boolean traloi1, boolean traloi2, boolean traloi3, boolean traloi4) {
        this.causo = causo;
        this.traloi1 = traloi1;
        this.traloi2 = traloi2;
        this.traloi3 = traloi3;
        this.traloi4 = traloi4;
    }

    public Selected(String causo) {
        this.causo = causo;
    }

    public String getCauso() {
        return causo;
    }

    public void setCauso(String causo) {
        this.causo = causo;
    }

    public boolean isTraloi1() {
        return traloi1;
    }

    public void setTraloi1(boolean traloi1) {
        this.traloi1 = traloi1;
    }

    public boolean isTraloi2() {
        return traloi2;
    }

    public void setTraloi2(boolean traloi2) {
        this.traloi2 = traloi2;
    }

    public boolean isTraloi3() {
        return traloi3;
    }

    public void setTraloi3(boolean traloi3) {
        this.traloi3 = traloi3;
    }

    public boolean isTraloi4() {
        return traloi4;
    }

    public void setTraloi4(boolean traloi4) {
        this.traloi4 = traloi4;
    }

    public void setTraLoi(int i, boolean z) {
        if (i == 1) {
            this.traloi1 = z;
        }
        if (i == 2) {
            this.traloi2 = z;
        }
        if (i == 3) {
            this.traloi3 = z;
        }
        if (i == 4) {
            this.traloi4 = z;
        }
    }

    public boolean kiemTraKetQua(String str) {
        if ((str.contains("1") && !isTraloi1()) || (!str.contains("1") && isTraloi1())) {
            return false;
        }
        if ((str.contains("2") && !isTraloi2()) || (!str.contains("2") && isTraloi2())) {
            return false;
        }
        if ((str.contains("3") && !isTraloi3()) || (!str.contains("3") && isTraloi3())) {
            return false;
        }
        if ((!str.contains("4") || isTraloi4()) && (str.contains("4") || !isTraloi4())) {
            return false;
        }
        return false;
    }


}
