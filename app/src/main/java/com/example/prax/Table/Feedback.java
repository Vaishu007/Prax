
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Feedback {

    @SerializedName("f_id")
    @Expose
    private String fId;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("f_description")
    @Expose
    private String fDescription;
    @SerializedName("f_rat")
    @Expose
    private String fRat;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfRat() {
        return fRat;
    }

    public void setfRat(String fRat) {
        this.fRat = fRat;
    }

}
