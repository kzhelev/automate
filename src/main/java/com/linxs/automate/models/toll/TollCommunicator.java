package com.linxs.automate.models.toll;

import com.linxs.automate.errors.ApplicationException;
import com.linxs.automate.models.Communicator;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.http.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class TollCommunicator implements Communicator<TollRequest, TollReport> {

    Logger logger = LoggerFactory.getLogger(TollCommunicator.class);

    private ObjectMapper objectMapper;

    @Autowired
    public TollCommunicator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public TollReport getData(TollRequest request) throws ApplicationException {

        String url = Constants.TOLL_URI + request.getLicensePlateNumber();

        HttpClient client = HttpClientBuilder.httpClient;

        HttpRequest outgoingRequest = HttpRequest.newBuilder()
                                                .GET()
                                                .uri(URI.create(url))
                                                .timeout(Duration.ofSeconds(30))
                                                .build();

        HttpResponse<String> outgoingResponse = null;

        try {

            outgoingResponse = client.send(outgoingRequest, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            logger.error("Error while sending outgoing request to: " + url, e);
            throw new ApplicationException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MSG, e);
        }

        String body = outgoingResponse.body();

        TollReport report = objectMapper.readValue(body, TollReport.class);

        return report;
    }
}
