package com.linxs.automate.persistence;

import com.linxs.automate.models.dto.VinReportDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vin_reports")
public class VinReportEntity extends AbstractReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vin;
    private LocalDate nextTechnicalTestDate;
    private LocalDate lastTechnicalTestDate;
    private String lastCheckKmCovered;

    public VinReportEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public VinReportDto toReportDto() {

        VinReportDto vinReportDto = new VinReportDto(vin, nextTechnicalTestDate, lastTechnicalTestDate, lastCheckKmCovered);

        return vinReportDto;
    }
}
