
package com.example.prax.Json;

import java.util.List;

import com.example.prax.Table.Exam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Examjson {

    @SerializedName("Exam")
    @Expose
    private List<Exam> exam;

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
    }

}
