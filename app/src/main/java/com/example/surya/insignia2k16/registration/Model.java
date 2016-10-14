package com.example.surya.insignia2k16.registration;

/**
 * Created by Surya on 13-10-2016.
 */
public class Model {
    String gpHead,mem1,mem2,mem3,email,number;

    public Model() {
    }

    public Model(String gpHead, String mem1, String mem2, String mem3, String email, String number) {
        this.gpHead = gpHead;
        this.mem1 = mem1;
        this.mem2 = mem2;
        this.mem3 = mem3;
        this.email = email;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {

        return email;
    }

    public String getGpHead() {
        return gpHead;
    }

    public String getMem1() {
        return mem1;
    }

    public String getMem2() {
        return mem2;
    }

    public String getMem3() {
        return mem3;
    }
}