
package com.example.prax.Json;

import java.util.List;

import com.example.prax.Table.Coursein;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Courseins {

    @SerializedName("courseins")
    @Expose
    private List<Coursein> courseins = null;

    public List<Coursein> getCourseins() {
        return courseins;
    }

    public void setCourseins(List<Coursein> courseins) {
        this.courseins = courseins;
    }

}
