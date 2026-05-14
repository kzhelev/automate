package com.linxs.automate.models;

public abstract class AbstractRequest {

    private String requestType;

    public AbstractRequest(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
