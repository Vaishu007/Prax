
package com.example.prax.Json;


import com.example.prax.Table.Faq;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Faqjson {

    @SerializedName("faq")
    @Expose
    private List<Faq> faq = null;

    public List<Faq> getFaq() {
        return faq;
    }

    public void setFaq(List<Faq> faq) {
        this.faq = faq;
    }

}
