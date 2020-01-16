package com.duongnb.a150lythuyetxemay.Model;

import java.io.Serializable;

public class Question implements Serializable {
   private Integer Id;
   private String cauHoi;
   private String cauTL1;
   private String cauTL2;
   private String cauTL3;
   private String cauTL4;
   private String chuThich;
   private String dapAn;
   private String image;

   public Question(Integer id, String cauHoi, String cauTL1, String cauTL2, String cauTL3, String cauTL4, String chuThich, String dapAn, String image) {
      Id = id;
      this.cauHoi = cauHoi;
      this.cauTL1 = cauTL1;
      this.cauTL2 = cauTL2;
      this.cauTL3 = cauTL3;
      this.cauTL4 = cauTL4;
      this.chuThich = chuThich;
      this.dapAn = dapAn;
      this.image = image;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public Integer getId() {
      return Id;
   }

   public void setId(Integer id) {
      Id = id;
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

   public String getChuThich() {
      return chuThich;
   }

   public void setChuThich(String chuThich) {
      this.chuThich = chuThich;
   }

   public String getDapAn() {
      return dapAn;
   }

   public void setDapAn(String dapAn) {
      this.dapAn = dapAn;
   }
}
