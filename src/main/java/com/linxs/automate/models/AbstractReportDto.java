package com.linxs.automate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;

public class AbstractReportDto {

    private int errorCode;
    private String errorMessage;

    @JsonIgnore
    private String reportType;

    public AbstractReportDto(int errorCode, String errorMessage, String reportType) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.reportType = reportType;
    }

    public AbstractReportDto(String reportType) {
        this.reportType = reportType;
    }

    public AbstractReportDto() {
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
