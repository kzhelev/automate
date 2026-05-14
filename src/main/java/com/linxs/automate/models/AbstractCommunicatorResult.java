package com.linxs.automate.models;

import com.linxs.automate.persistence.AbstractReportEntity;

public abstract class AbstractCommunicatorResult<T extends AbstractReportEntity, R> {

    private String reportType;

    public AbstractCommunicatorResult(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public abstract String toJson();

    public abstract T toEntity(R request);
}
