
package com.example.prax.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Faq {

    @SerializedName("fq_id")
    @Expose
    private String fqId;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("fq_question")
    @Expose
    private String fqQuestion;
    @SerializedName("fq_answer")
    @Expose
    private String fqAnswer;

    public String getFqId() {
        return fqId;
    }

    public void setFqId(String fqId) {
        this.fqId = fqId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getFqQuestion() {
        return fqQuestion;
    }

    public void setFqQuestion(String fqQuestion) {
        this.fqQuestion = fqQuestion;
    }

    public String getFqAnswer() {
        return fqAnswer;
    }

    public void setFqAnswer(String fqAnswer) {
        this.fqAnswer = fqAnswer;
    }

}
