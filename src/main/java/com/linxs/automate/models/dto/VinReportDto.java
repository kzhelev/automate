package com.linxs.automate.models.dto;

import com.linxs.automate.models.AbstractReportDto;

import java.time.LocalDate;

public class VinReportDto extends AbstractReportDto {

    private String vin;
    private LocalDate nextTechnicalTestDate;
    private LocalDate lastTechnicalTestDate;
    private String lastCheckKmCovered;

    public VinReportDto(String vin, LocalDate nextTechnicalTestDate, LocalDate lastTechnicalTestDate, String lastCheckKmCovered) {
        this.vin = vin;
        this.nextTechnicalTestDate = nextTechnicalTestDate;
        this.lastTechnicalTestDate = lastTechnicalTestDate;
        this.lastCheckKmCovered = lastCheckKmCovered;
    }

    public VinReportDto() {
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LocalDate getNextTechnicalTestDate() {
        return nextTechnicalTestDate;
    }

    public void setNextTechnicalTestDate(LocalDate nextTechnicalTestDate) {
        this.nextTechnicalTestDate = nextTechnicalTestDate;
    }

    public LocalDate getLastTechnicalTestDate() {
        return lastTechnicalTestDate;
    }

    public void setLastTechnicalTestDate(LocalDate lastTechnicalTestDate) {
        this.lastTechnicalTestDate = lastTechnicalTestDate;
    }

    public String getLastCheckKmCovered() {
        return lastCheckKmCovered;
    }

    public void setLastCheckKmCovered(String lastCheckKmCovered) {
        this.lastCheckKmCovered = lastCheckKmCovered;
    }
}
