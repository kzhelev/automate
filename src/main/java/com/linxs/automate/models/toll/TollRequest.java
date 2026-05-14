package com.linxs.automate.models.toll;

import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.Constants;

public class TollRequest extends AbstractRequest {

    private String licensePlateNumber;

    public TollRequest() {
        super(Constants.TOLL_REPORT);
    }

    public TollRequest(String licensePlateNumber) {
        super(Constants.TOLL_REPORT);
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}
