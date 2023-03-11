
package com.example.prax.Json;

import com.example.prax.Table.Material;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Materialjson {

    @SerializedName("material")
    @Expose
    private List<Material> material = null;

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

}
