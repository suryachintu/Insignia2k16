package com.example.surya.insignia2k16.chat.model;

/**
 * Created by surya on 04-07-2016.
 */
public class Users {

    String userName;
    String email;
    String photoUrl;
    Messages messages;

    public Users() {
    }

    public Users(String userName, String email, String photoUrl, Messages messages) {
        this.userName = userName;
        this.email = email;
        this.photoUrl = photoUrl;
        messages = new Messages();
        this.messages = messages;
    }

    public Messages getMessages() {
        return messages;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
