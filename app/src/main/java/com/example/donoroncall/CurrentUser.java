package com.example.donoroncall;

public class CurrentUser {


    public String getCurrentUser() {
        return GlobalVar1;
    }

    public void setGlobalVar1(String GlobalVar1) {
        this.GlobalVar1 = GlobalVar1;
    }

    private String GlobalVar1 = "";

    static CurrentUser instance = new CurrentUser();

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        return CurrentUser.instance;
    }
}