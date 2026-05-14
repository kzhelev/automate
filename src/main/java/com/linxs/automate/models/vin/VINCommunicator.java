package com.linxs.automate.models.vin;

import com.linxs.automate.errors.ApplicationException;
import com.linxs.automate.models.Communicator;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.http.HttpClientBuilder;
import com.linxs.automate.models.toll.TollCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class VINCommunicator implements Communicator<VINRequest, VINReport> {

    Logger logger = LoggerFactory.getLogger(VINCommunicator.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public VINReport getData(VINRequest vinRequest) throws ApplicationException {

        HttpClient client = HttpClientBuilder.trustAllHttpsClient;

        StringBuilder uri = new StringBuilder();
        uri.append(Constants.VIN_URI);
        uri.append("vin=").append(vinRequest.getVin());
        uri.append("&");
        uri.append(Constants.VIN_URI_reCAPTCHA);

        client.sslContext();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri.toString()))
                .timeout(Duration.ofSeconds(30))
                .build();

        HttpResponse<String> httpResponse = null;

        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            logger.error("Error while sending outgoing request to: " + uri.toString(), e);
            throw new ApplicationException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MSG, e);
        }

        StringBuilder response = new StringBuilder();
        response.append("{\"inspections\":");
        response.append(httpResponse.body());
        response.append("}");

        VINReport vinReport = mapper.readValue(response.toString(), VINReport.class);

        return vinReport;
    }
}
