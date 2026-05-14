package com.linxs.automate.models;

import java.util.Map;

public class Response {

    private AbstractReportDto vinReport;

    private AbstractReportDto trafficFinesReport;

    private AbstractReportDto tollReport;

    public Response() {
    }

    public static Response buildResponse(Map<String, AbstractReportDto> abstractReportsMap) {

        Response response = new Response();

        response.vinReport = abstractReportsMap.get(Constants.VIN_REPORT);
        response.trafficFinesReport = abstractReportsMap.get(Constants.OBLIGATIONS_REPORT);
        response.tollReport = abstractReportsMap.get(Constants.TOLL_REPORT);

        return response;
    }

    public AbstractReportDto getVinReport() {
        return vinReport;
    }

    public void setVinReport(AbstractReportDto vinReport) {
        this.vinReport = vinReport;
    }

    public AbstractReportDto getTrafficFinesReport() {
        return trafficFinesReport;
    }

    public void setTrafficFinesReport(AbstractReportDto trafficFinesReport) {
        this.trafficFinesReport = trafficFinesReport;
    }

    public AbstractReportDto getTollReport() {
        return tollReport;
    }

    public void setTollReport(AbstractReportDto tollReport) {
        this.tollReport = tollReport;
    }
}
