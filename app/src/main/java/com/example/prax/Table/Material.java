
package com.example.prax.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Material {

    @SerializedName("m_id")
    @Expose
    private String mId;
    @SerializedName("m_name")
    @Expose
    private String mName;
    @SerializedName("m_branch")
    @Expose
    private String mBranch;
    @SerializedName("m_sem")
    @Expose
    private String mSem;
    @SerializedName("m_subject")
    @Expose
    private String mSubject;
    @SerializedName("m_subcode")
    @Expose
    private String mSubcode;
    @SerializedName("m_document")
    @Expose
    private String mDocument;
    @SerializedName("m_type")
    @Expose
    private String mType;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmBranch() {
        return mBranch;
    }

    public void setmBranch(String mBranch) {
        this.mBranch = mBranch;
    }

    public String getmSem() {
        return mSem;
    }

    public void setmSem(String mSem) {
        this.mSem = mSem;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmSubcode() {
        return mSubcode;
    }

    public void setmSubcode(String mSubcode) {
        this.mSubcode = mSubcode;
    }

    public String getmDocument() {
        return mDocument;
    }

    public void setmDocument(String mDocument) {
        this.mDocument = mDocument;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

}
