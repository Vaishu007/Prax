
package com.example.prax.Json;

import java.util.List;


import com.example.prax.Table.Content;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Contentjson {

    @SerializedName("Content")
    @Expose
    private List<Content> content = null;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
