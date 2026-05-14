package com.linxs.automate.models.dto;

import com.linxs.automate.models.AbstractReportDto;

public class ObligationReportDto extends AbstractReportDto {

    private String idn;
    private boolean hasObligations;

    public ObligationReportDto(String idn, boolean hasObligations) {
        this.idn = idn;
        this.hasObligations = hasObligations;
    }

    public ObligationReportDto() {
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public boolean isHasObligations() {
        return hasObligations;
    }

    public void setHasObligations(boolean hasObligations) {
        this.hasObligations = hasObligations;
    }
}
