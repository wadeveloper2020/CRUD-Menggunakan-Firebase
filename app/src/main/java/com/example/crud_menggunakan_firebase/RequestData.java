package com.example.crud_menggunakan_firebase;

import java.io.Serializable;

public class RequestData implements Serializable {

    private String nama;
    private String email;
    private String desc;
    private String key;

    public RequestData(){

    }

    public RequestData(String nama, String email, String desc) {
        this.nama = nama;
        this.email = email;
        this.desc = desc;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
        @Override
    public String toString (){
        return
                " "+nama+"\n"+
                " "+email+"\n" +
                " "+desc;
    }
}
