package com.example.donoroncall;

public class DonationRecordeModel {
    int recordid;
    int quantity;
    String recorddate;
    String hospitalname;
    String donername;

    public DonationRecordeModel(int recordid, int quantity, String recorddate, String hospitalname, String donername) {
        this.recordid = recordid;
        this.quantity = quantity;
        this.recorddate = recorddate;
        this.hospitalname = hospitalname;
        this.donername = donername;
    }

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getDonername() {
        return donername;
    }

    public void setDonername(String donername) {
        this.donername = donername;
    }

    @Override
    public String toString() {
        return "DonationRecordeModel{" +
                "recordid=" + recordid +
                ", quantity=" + quantity +
                ", recorddate='" + recorddate + '\'' +
                ", hospitalname='" + hospitalname + '\'' +
                ", donername='" + donername + '\'' +
                '}';
    }
}
