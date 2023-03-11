
package com.example.prax.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Content {

    @SerializedName("con_id")
    @Expose
    private String conId;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("con_name")
    @Expose
    private String conName;
    @SerializedName("con_chno")
    @Expose
    private String conChno;
    @SerializedName("con_chname")
    @Expose
    private String conChname;
    @SerializedName("con_document")
    @Expose
    private String conDocument;
    @SerializedName("con_type")
    @Expose
    private String conType;

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConChno() {
        return conChno;
    }

    public void setConChno(String conChno) {
        this.conChno = conChno;
    }

    public String getConChname() {
        return conChname;
    }

    public void setConChname(String conChname) {
        this.conChname = conChname;
    }

    public String getConDocument() {
        return conDocument;
    }

    public void setConDocument(String conDocument) {
        this.conDocument = conDocument;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

}
