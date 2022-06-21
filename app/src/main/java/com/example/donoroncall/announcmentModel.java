package com.example.donoroncall;

public class announcmentModel {
    private int ID;
    private String Announcement_date;
    private int Quantity;
    private String bloodtype;
    private String h_name;
    private String h_location;
    private String Announcement_status;


    public announcmentModel(int ID, String announcement_date, int quantity, String bloodtype, String h_name, String h_location, String announcement_status) {
        this.ID = ID;
        Announcement_date = announcement_date;
        Quantity = quantity;
        this.bloodtype = bloodtype;
        this.h_name = h_name;
        this.h_location = h_location;
        this.Announcement_status = announcement_status;
    }

    public String getAnnouncement_status() {
        return Announcement_status;
    }

    public void setAnnouncement_status(String announcement_status) {
        Announcement_status = announcement_status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnnouncement_date() {
        return Announcement_date;
    }

    public void setAnnouncement_date(String announcement_date) {
        Announcement_date = announcement_date;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_location() {
        return h_location;
    }

    public void setH_location(String h_location) {
        this.h_location = h_location;
    }


    @Override
    public String toString() {
        return "announcmentModel{" +
                "ID=" + ID +
                ", Announcement_date='" + Announcement_date + '\'' +
                ", Quantity=" + Quantity +
                ", bloodtype='" + bloodtype + '\'' +
                ", h_name='" + h_name + '\'' +
                ", h_location='" + h_location + '\'' +
                ", Announcement_status='" + Announcement_status + '\'' +
                '}';
    }
}

