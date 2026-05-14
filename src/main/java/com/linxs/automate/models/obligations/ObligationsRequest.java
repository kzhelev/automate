package com.linxs.automate.models.obligations;

import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.Constants;

public class ObligationsRequest extends AbstractRequest {

    private String idn;
    private String drivingLicenseNumber;

    public ObligationsRequest(String idn, String drivingLicenseNumber) {
        super(Constants.OBLIGATIONS_REPORT);
        this.idn = idn;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public ObligationsRequest() {
        super(Constants.OBLIGATIONS_REPORT);
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
