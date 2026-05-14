package com.linxs.automate.models.dto;

import com.linxs.automate.models.AbstractReportDto;
import com.linxs.automate.models.AbstractReportDto;

public class TollReportDto extends AbstractReportDto {

    private String licensePlateNumber;
    private String validToDate;
    private boolean hasVignette;

    public TollReportDto(String licensePlateNumber, String validToDate, boolean hasVignette) {
        this.licensePlateNumber = licensePlateNumber;
        this.validToDate = validToDate;
        this.hasVignette = hasVignette;
    }

    public TollReportDto() {
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    public boolean isHasVignette() {
        return hasVignette;
    }

    public void setHasVignette(boolean hasVignette) {
        this.hasVignette = hasVignette;
    }
}
