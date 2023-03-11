
package com.example.prax.Json;

import java.util.List;

import com.example.prax.Table.Praxregi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regisjson {

    @SerializedName("praxregi")
    @Expose
    private List<Praxregi> praxregi;

    public List<Praxregi> getPraxregi() {
        return praxregi;
    }

    public void setPraxregi(List<Praxregi> praxregi) {
        this.praxregi = praxregi;
    }

}
