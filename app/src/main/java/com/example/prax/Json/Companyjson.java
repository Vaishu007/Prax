
package com.example.prax.Json;

import java.util.List;


import com.example.prax.Table.Company;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Companyjson {

    @SerializedName("companyjson")
    @Expose
    private List<Company> companyjson = null;

    public List<Company> getCompanyjson() {
        return companyjson;
    }

    public void setCompanyjson(List<Company> companyjson) {
        this.companyjson = companyjson;
    }

}
