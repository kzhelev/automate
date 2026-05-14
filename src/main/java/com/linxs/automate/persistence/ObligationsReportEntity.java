package com.linxs.automate.persistence;

import com.linxs.automate.models.dto.ObligationReportDto;
import jakarta.persistence.*;

@Entity
@Table(name = "traffic_fines")
public class ObligationsReportEntity extends AbstractReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String idn;
    private boolean hasObligations;

    public ObligationsReportEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ObligationReportDto toReportDto() {

        ObligationReportDto reportDto = new ObligationReportDto(idn, hasObligations);

        return reportDto;
    }
}
