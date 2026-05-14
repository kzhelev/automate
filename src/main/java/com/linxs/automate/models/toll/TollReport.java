package com.linxs.automate.models.toll;

import com.linxs.automate.models.AbstractCommunicatorResult;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.toll.data.Status;
import com.linxs.automate.models.toll.data.Vignette;
import com.linxs.automate.persistence.TollReportEntity;

public class TollReport extends AbstractCommunicatorResult<TollReportEntity, TollRequest> {

    private Vignette vignette;
    private boolean ok;
    private Status status;

    public TollReport() {
        super(Constants.TOLL_REPORT);
    }

    public Vignette getVignette() {
        return vignette;
    }

    public void setVignette(Vignette vignette) {
        this.vignette = vignette;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toJson() {
        return "";
    }

    @Override
    public TollReportEntity toEntity(TollRequest request) {

        TollReportEntity report = new TollReportEntity();

        report.setHasVignette(this.ok);
        report.setLicensePlateNumber(request.getLicensePlateNumber());

        if (this.vignette != null && vignette.getValidityDateTo() != null) {
            report.setValidToDate(this.vignette.getValidityDateTo());
        }

        return report;
    }
}
