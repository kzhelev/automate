package com.linxs.automate.models.obligations;

import com.linxs.automate.models.AbstractCommunicatorResult;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.obligations.data.ObligationsData;
import com.linxs.automate.persistence.ObligationsReportEntity;
import com.linxs.automate.utils.JsonUtil;

import java.util.List;

public class ObligationsReport extends AbstractCommunicatorResult<ObligationsReportEntity, ObligationsRequest> {

    private List<ObligationsData> obligationsData;

    public ObligationsReport() {
        super(Constants.OBLIGATIONS_REPORT);
    }

    public ObligationsReport(List<ObligationsData> obligationsData) {
        super(Constants.OBLIGATIONS_REPORT);
        this.obligationsData = obligationsData;
    }

    public List<ObligationsData> getObligationsData() {
        return obligationsData;
    }

    public void setObligationsData(List<ObligationsData> obligationsData) {
        this.obligationsData = obligationsData;
    }

    @Override
    public String toJson() {

        String toJson = JsonUtil.toJson(this);

        return toJson;
    }

    @Override
    public ObligationsReportEntity toEntity(ObligationsRequest request) {

        ObligationsReportEntity entity = new ObligationsReportEntity();

        entity.setIdn(request.getIdn());

        boolean hasTrafficFines = false;

        for (ObligationsData obligationsData : obligationsData) {

            if (!obligationsData.getObligations().isEmpty()){
                hasTrafficFines = true;
            }
        }

        entity.setHasObligations(hasTrafficFines);

        return entity;
    }
}
