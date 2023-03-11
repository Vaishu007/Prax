
package com.example.prax.Table;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Exam {

    @SerializedName("e_id")
    @Expose
    private String eId;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("e_question")
    @Expose
    private String eQuestion;
    @SerializedName("e_opt1")
    @Expose
    private String eOpt1;
    @SerializedName("e_opt2")
    @Expose
    private String eOpt2;
    @SerializedName("e_opt3")
    @Expose
    private String eOpt3;
    @SerializedName("e_opt4")
    @Expose
    private String eOpt4;
    @SerializedName("e_ans")
    @Expose
    private String eAns;

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String geteQuestion() {
        return eQuestion;
    }

    public void seteQuestion(String eQuestion) {
        this.eQuestion = eQuestion;
    }

    public String geteOpt1() {
        return eOpt1;
    }

    public void seteOpt1(String eOpt1) {
        this.eOpt1 = eOpt1;
    }

    public String geteOpt2() {
        return eOpt2;
    }

    public void seteOpt2(String eOpt2) {
        this.eOpt2 = eOpt2;
    }

    public String geteOpt3() {
        return eOpt3;
    }

    public void seteOpt3(String eOpt3) {
        this.eOpt3 = eOpt3;
    }

    public String geteOpt4() {
        return eOpt4;
    }

    public void seteOpt4(String eOpt4) {
        this.eOpt4 = eOpt4;
    }

    public String geteAns() {
        return eAns;
    }

    public void seteAns(String eAns) {
        this.eAns = eAns;
    }

}
