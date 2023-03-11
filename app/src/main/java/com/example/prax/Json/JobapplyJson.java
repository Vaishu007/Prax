
package com.example.prax.Json;

import java.util.List;

import com.example.prax.Table.Jobapply;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JobapplyJson {

    @SerializedName("Jobapply")
    @Expose
    private List<Jobapply> jobapply;

    public List<Jobapply> getJobapply() {
        return jobapply;
    }

    public void setJobapply(List<Jobapply> jobapply) {
        this.jobapply = jobapply;
    }

}
