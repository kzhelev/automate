package com.linxs.automate.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linxs.automate.models.obligations.ObligationsRequest;
import com.linxs.automate.models.toll.TollRequest;
import com.linxs.automate.models.vin.VINRequest;

import java.util.ArrayList;
import java.util.List;

public class FullCheckRequest {

    private VINRequest vinRequest;
    private TollRequest tollRequest;
    private ObligationsRequest trafficFineRequest;

    public FullCheckRequest() {
    }

    public VINRequest getVinRequest() {
        return vinRequest;
    }

    public void setVinRequest(VINRequest vinRequest) {
        this.vinRequest = vinRequest;
    }

    public TollRequest getTollRequest() {
        return tollRequest;
    }

    public void setTollRequest(TollRequest tollRequest) {
        this.tollRequest = tollRequest;
    }

    public ObligationsRequest getTrafficFineRequest() {
        return trafficFineRequest;
    }

    public void setTrafficFineRequest(ObligationsRequest trafficFineRequest) {
        this.trafficFineRequest = trafficFineRequest;
    }

    public List<AbstractRequest> buildRequestList() {

        List<AbstractRequest> requestList = new ArrayList<>();
        requestList.add(vinRequest);
        requestList.add(tollRequest);
        requestList.add(trafficFineRequest);

        return requestList;
    }
}
