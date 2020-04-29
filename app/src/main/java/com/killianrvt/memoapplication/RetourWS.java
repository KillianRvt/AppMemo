package com.killianrvt.memoapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Example {

    @SerializedName("form")
    @Expose
    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}

class Form {
    @SerializedName("memo")
    @Expose
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}