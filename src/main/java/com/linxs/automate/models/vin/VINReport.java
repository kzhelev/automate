package com.linxs.automate.models.vin;

import com.linxs.automate.models.AbstractCommunicatorResult;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.vin.data.Inspection;
import com.linxs.automate.persistence.VinReportEntity;
import com.linxs.automate.utils.JsonUtil;

import java.time.LocalDate;
import java.util.List;

public class VINReport extends AbstractCommunicatorResult<VinReportEntity, VINRequest> {

    List<Inspection> inspections;

    public VINReport(List<Inspection> inspections) {
        super(Constants.VIN_REPORT);
        this.inspections = inspections;
    }

    public VINReport() {
        super(Constants.VIN_REPORT);
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public String toJson() {

        String toJson = JsonUtil.toJson(this);

        return toJson;
    }

    @Override
    public VinReportEntity toEntity(VINRequest request) {

        VinReportEntity vinReportEntity = new VinReportEntity();

        vinReportEntity.setVin(request.getVin());

        LocalDate nextTechInspectionDate = null;
        LocalDate lastTechInspectionDate = null;
        String kmCovered = null;

        for (Inspection inspection : inspections) {

            if (nextTechInspectionDate == null) {
                nextTechInspectionDate = inspection.getNextInspectionDate();
                lastTechInspectionDate = inspection.getDate();
                kmCovered = String.valueOf(inspection.getKmCovered());
                continue;
            }

            if (nextTechInspectionDate.isBefore(inspection.getNextInspectionDate())) {
                nextTechInspectionDate = inspection.getNextInspectionDate();
                lastTechInspectionDate = inspection.getDate();
                kmCovered = String.valueOf(inspection.getKmCovered());
            }
        }

        vinReportEntity.setNextTechnicalTestDate(nextTechInspectionDate);
        vinReportEntity.setLastTechnicalTestDate(lastTechInspectionDate);
        vinReportEntity.setLastCheckKmCovered(kmCovered);

        return vinReportEntity;
    }
}
