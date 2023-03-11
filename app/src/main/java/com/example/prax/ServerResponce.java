package com.example.prax;

import com.google.gson.annotations.SerializedName;

public class ServerResponce {
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}
