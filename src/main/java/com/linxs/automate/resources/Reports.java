package com.linxs.automate.resources;

import com.linxs.automate.models.AbstractReportDto;
import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.FullCheckRequest;
import com.linxs.automate.models.Response;
import com.linxs.automate.models.obligations.ObligationsRequest;
import com.linxs.automate.models.toll.TollRequest;
import com.linxs.automate.models.vin.VINRequest;
import com.linxs.automate.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class Reports {

    @Autowired
    private ReportService reportService;

    @GetMapping("vin-report")
    public AbstractReportDto vinReport(@RequestParam String vin) {

        VINRequest request = new VINRequest(vin);

        AbstractReportDto report = reportService.processRequest(request);

        return report;
    }

    @GetMapping("traffic-fines-report")
    public AbstractReportDto obligationsReport(@RequestParam String idn,
                                                  @RequestParam String drivingLicenseNumber) {

        ObligationsRequest request = new ObligationsRequest(idn, drivingLicenseNumber);

        AbstractReportDto report = reportService.processRequest(request);

        return report;
    }

    @GetMapping("toll-report")
    public AbstractReportDto tollReport(@RequestParam String licensePlateNumber) {

        TollRequest request = new TollRequest(licensePlateNumber);

        AbstractReportDto report = reportService.processRequest(request);

        return report;
    }

    @PostMapping("full-check")
    public Response fullCheck(@RequestBody FullCheckRequest fullCheckRequest) {

        List<AbstractRequest> requests = fullCheckRequest.buildRequestList();

        Response responseReports = reportService.processRequest(requests);

        return responseReports;
    }
}
