package com.linxs.automate.models.vin;

import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.Constants;

public class VINRequest extends AbstractRequest {

    private String vin;

    public VINRequest() {
        super(Constants.VIN_REPORT);
    }

    public VINRequest(String vin) {
        super(Constants.VIN_REPORT);
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
