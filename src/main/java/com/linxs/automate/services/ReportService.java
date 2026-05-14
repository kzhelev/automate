package com.linxs.automate.services;

import com.linxs.automate.errors.ApplicationException;
import com.linxs.automate.models.*;
import com.linxs.automate.persistence.AbstractReportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class ReportService {

    Logger logger = LoggerFactory.getLogger(ReportService.class);

    private CommunicatorFactory communicatorFactory;
    private PersistenceService persistenceService;

    @Autowired
    public ReportService(CommunicatorFactory communicatorFactory, PersistenceService persistenceService) {
        this.communicatorFactory = communicatorFactory;
        this.persistenceService = persistenceService;
    }

    public AbstractReportDto processRequest(AbstractRequest request) {

        Communicator communicator = communicatorFactory.createCommunicator(request);

        if (communicator == null) {
            logger.error("Communicator could not be created");
            return new AbstractReportDto(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MSG, request.getRequestType());
        }

        AbstractCommunicatorResult result = null;

        try {
            result = communicator.getData(request);
        } catch (ApplicationException e) {
            logger.error("Create AbstractReportDto with error data..");
            return new AbstractReportDto(e.getCode(), e.getCodeDescription(), request.getRequestType());
        }

        AbstractReportEntity reportEntity = persistenceService.saveResult(result.toEntity(request));

        AbstractReportDto report = reportEntity.toReportDto();

        report.setReportType(request.getRequestType());

        return report;
    }

    public Response processRequest(List<AbstractRequest> requests) {

        if (requests == null || requests.isEmpty()) {
            return new Response();
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(requests.size());
        Map<String, Future<AbstractReportDto>> futures = new HashMap<>();

        for (AbstractRequest request : requests) {
            Future<AbstractReportDto> future = threadPool.submit(() -> processRequest(request));
            futures.put(request.getRequestType(), future);
        }

        Map<String, AbstractReportDto> reports = new HashMap<>();

        for (Map.Entry<String, Future<AbstractReportDto>> futureEntry : futures.entrySet()) {

            AbstractReportDto report = null;

            try {
                report = futureEntry.getValue().get();
                reports.put(report.getReportType(), report);
            } catch (Exception e) {
                logger.error("Error occurred while getting result from future..", e);

                reports.put(report.getReportType(), new AbstractReportDto(Constants.INTERNAL_SERVER_ERROR,
                                                                            Constants.INTERNAL_SERVER_ERROR_MSG,
                                                                                futureEntry.getKey()));
            }
        }

        Response response = Response.buildResponse(reports);

        return response;
    }
}
