package com.linxs.automate.models.obligations;

import com.linxs.automate.errors.ApplicationException;
import com.linxs.automate.models.Communicator;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.http.HttpClientBuilder;
import com.linxs.automate.models.toll.TollCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ObligationsCommunicator implements Communicator<ObligationsRequest, ObligationsReport> {

    Logger logger = LoggerFactory.getLogger(ObligationsCommunicator.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public ObligationsReport getData(ObligationsRequest obligationsRequest) throws ApplicationException {

        StringBuilder uri = new StringBuilder();
        uri.append(Constants.OBLIGATIONS_URI);
        uri.append("obligedPersonIdent=").append(obligationsRequest.getIdn());
        uri.append("&");
        uri.append("drivingLicenceNumber=").append(obligationsRequest.getDrivingLicenseNumber());

        HttpClient client = HttpClientBuilder.httpClient;

        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(uri.toString()))
                                        .GET()
                                        .timeout(Duration.ofSeconds(30))
                                        .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            logger.error("Error while sending outgoing request to: " + uri.toString(), e);
            throw new ApplicationException(Constants.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MSG, e);
        }

        ObligationsReport obligationsReport = mapper.readValue(response.body(), ObligationsReport.class);

        return obligationsReport;
    }
}
