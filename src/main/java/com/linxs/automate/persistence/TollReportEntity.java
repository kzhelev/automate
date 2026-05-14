package com.linxs.automate.persistence;

import com.linxs.automate.models.dto.TollReportDto;
import jakarta.persistence.*;

@Entity
@Table(name = "toll_reports")
public class TollReportEntity extends AbstractReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlateNumber;
    private boolean hasVignette;
    private String validToDate;

    public TollReportEntity() {
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public boolean isHasVignette() {
        return hasVignette;
    }

    public void setHasVignette(boolean hasVignette) {
        this.hasVignette = hasVignette;
    }

    public String getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    @Override
    public String toString() {
        return "TollReportEntity{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", hasVignette=" + hasVignette +
                ", validToDate='" + validToDate + '\'' +
                '}';
    }

    public TollReportDto toReportDto() {
        TollReportDto dto = new TollReportDto(licensePlateNumber,validToDate,hasVignette);
        return dto;
    }
}
