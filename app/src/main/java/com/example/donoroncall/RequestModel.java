package com.example.donoroncall;

public class RequestModel {
    int requestid,announcementid;
    String requeststatus,requestdate,requesttype,donername,donationdate;

    public RequestModel(int requestid, String donername, int announcementid, String requeststatus, String requestdate, String requesttype,String donationdate) {
        this.requestid = requestid;
        this.donername = donername;
        this.announcementid = announcementid;
        this.requeststatus = requeststatus;
        this.requestdate = requestdate;
        this.requesttype = requesttype;
        this.donationdate=donationdate;
    }

    public String getDonationdate() {
        return donationdate;
    }

    public void setDonationdate(String donationdate) {
        this.donationdate = donationdate;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public String getDonername() {
        return donername;
    }

    public void setDonername(String donername) {
        this.donername = donername;
    }

    public int getAnnouncementid() {
        return announcementid;
    }

    public void setAnnouncementid(int announcementid) {
        this.announcementid = announcementid;
    }

    public String getRequeststatus() {
        return requeststatus;
    }

    public void setRequeststatus(String requeststatus) {
        this.requeststatus = requeststatus;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "requestid=" + requestid +
                ", donername=" + donername +
                ", announcementid=" + announcementid +
                ", requeststatus='" + requeststatus + '\'' +
                ", requestdate='" + requestdate + '\'' +
                ", requesttype='" + requesttype + '\'' +
                '}';
    }
}
