
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Company {

    @SerializedName("com_id")
    @Expose
    private String comId;
    @SerializedName("com_name")
    @Expose
    private String comName;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("com_detail")
    @Expose
    private String comDetail;
    @SerializedName("com_contact")
    @Expose
    private String comContact;
    @SerializedName("com_email")
    @Expose
    private String comEmail;
    @SerializedName("com_address")
    @Expose
    private String comAddress;
    @SerializedName("com_vacancy")
    @Expose
    private String comVacancy;
    @SerializedName("com_status")
    @Expose
    private String comStatus;
    @SerializedName("com_image")
    @Expose
    private String comImage;

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getComDetail() {
        return comDetail;
    }

    public void setComDetail(String comDetail) {
        this.comDetail = comDetail;
    }

    public String getComContact() {
        return comContact;
    }

    public void setComContact(String comContact) {
        this.comContact = comContact;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComVacancy() {
        return comVacancy;
    }

    public void setComVacancy(String comVacancy) {
        this.comVacancy = comVacancy;
    }

    public String getComStatus() {
        return comStatus;
    }

    public void setComStatus(String comStatus) {
        this.comStatus = comStatus;
    }

    public String getComImage() {
        return comImage;
    }

    public void setComImage(String comImage) {
        this.comImage = comImage;
    }

}
