package com.linxs.automate.models.obligations.data;

import java.util.List;

public class ObligationsData {

    private int unitGroup;
    private boolean errorNoDataFound;
    private boolean errorReadingData;
    private List<Object> obligations;

    public ObligationsData() {
    }

    public int getUnitGroup() {
        return unitGroup;
    }

    public void setUnitGroup(int unitGroup) {
        this.unitGroup = unitGroup;
    }

    public boolean isErrorNoDataFound() {
        return errorNoDataFound;
    }

    public void setErrorNoDataFound(boolean errorNoDataFound) {
        this.errorNoDataFound = errorNoDataFound;
    }

    public boolean isErrorReadingData() {
        return errorReadingData;
    }

    public void setErrorReadingData(boolean errorReadingData) {
        this.errorReadingData = errorReadingData;
    }

    public List<Object> getObligations() {
        return obligations;
    }

    public void setObligations(List<Object> obligations) {
        this.obligations = obligations;
    }
}
