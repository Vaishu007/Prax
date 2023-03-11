
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Jobapply {

    @SerializedName("j_id")
    @Expose
    private String jId;
    @SerializedName("com_id")
    @Expose
    private String comId;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("j_uresume")
    @Expose
    private String jUresume;

    public String getjId() {
        return jId;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getjUresume() {
        return jUresume;
    }

    public void setjUresume(String jUresume) {
        this.jUresume = jUresume;
    }

}
