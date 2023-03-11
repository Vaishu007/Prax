
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Praxregi {

    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("u_gender")
    @Expose
    private String uGender;
    @SerializedName("u_dob")
    @Expose
    private String uDob;
    @SerializedName("u_address")
    @Expose
    private String uAddress;
    @SerializedName("u_email")
    @Expose
    private String uEmail;
    @SerializedName("u_contact")
    @Expose
    private String uContact;
    @SerializedName("u_image")
    @Expose
    private String uImage;
    @SerializedName("u_password")
    @Expose
    private String uPassword;
    @SerializedName("u_college")
    @Expose
    private String uCollege;
    @SerializedName("u_caddress")
    @Expose
    private String uCaddress;
    @SerializedName("u_branch")
    @Expose
    private String uBranch;
    @SerializedName("u_sem")
    @Expose
    private String uSem;
    @SerializedName("u_cduration")
    @Expose
    private String uCduration;
    @SerializedName("u_cdate")
    @Expose
    private String uCdate;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    public String getuDob() {
        return uDob;
    }

    public void setuDob(String uDob) {
        this.uDob = uDob;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuContact() {
        return uContact;
    }

    public void setuContact(String uContact) {
        this.uContact = uContact;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuCollege() {
        return uCollege;
    }

    public void setuCollege(String uCollege) {
        this.uCollege = uCollege;
    }

    public String getuCaddress() {
        return uCaddress;
    }

    public void setuCaddress(String uCaddress) {
        this.uCaddress = uCaddress;
    }

    public String getuBranch() {
        return uBranch;
    }

    public void setuBranch(String uBranch) {
        this.uBranch = uBranch;
    }

    public String getuSem() {
        return uSem;
    }

    public void setuSem(String uSem) {
        this.uSem = uSem;
    }

    public String getuCduration() {
        return uCduration;
    }

    public void setuCduration(String uCduration) {
        this.uCduration = uCduration;
    }

    public String getuCdate() {
        return uCdate;
    }

    public void setuCdate(String uCdate) {
        this.uCdate = uCdate;
    }

}
