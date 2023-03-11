
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Coursein {

    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("c_name")
    @Expose
    private String cName;
    @SerializedName("c_type")
    @Expose
    private String cType;
    @SerializedName("c_image")
    @Expose
    private String cImage;
    @SerializedName("c_fees")
    @Expose
    private String cFees;
    @SerializedName("c_detail")
    @Expose
    private String cDetail;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getcFees() {
        return cFees;
    }

    public void setcFees(String cFees) {
        this.cFees = cFees;
    }

    public String getcDetail() {
        return cDetail;
    }

    public void setcDetail(String cDetail) {
        this.cDetail = cDetail;
    }

}
