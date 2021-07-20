package org.rrcat.arpf.ui.entity;

public class RequestLogin {

    String uid;
    String password;

    public RequestLogin(String UID, String password) {
        this.uid = UID;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }
}
