package com.linxs.automate.services;

import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.Communicator;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.obligations.ObligationsCommunicator;
import com.linxs.automate.models.toll.TollCommunicator;
import com.linxs.automate.models.vin.VINCommunicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommunicatorFactory {

    private final VINCommunicator vinCommunicator;
    private final ObligationsCommunicator obligationsCommunicator;
    private final TollCommunicator tollCommunicator;
    private static Map<String, Communicator> communicators;

    @Autowired
    public CommunicatorFactory(VINCommunicator vinCommunicator, ObligationsCommunicator obligationsCommunicator,
                               TollCommunicator tollCommunicator) {
        this.vinCommunicator = vinCommunicator;
        this.obligationsCommunicator = obligationsCommunicator;
        this.tollCommunicator = tollCommunicator;

        if (communicators == null) {
            communicators = new HashMap<>();
            communicators.put(Constants.VIN_REPORT, this.vinCommunicator);
            communicators.put(Constants.OBLIGATIONS_REPORT, this.obligationsCommunicator);
            communicators.put(Constants.TOLL_REPORT, this.tollCommunicator);
        }
    }

//    @PostConstruct
//    private void setUpCommunicatorsMap() {
//
//        if (communicators == null) {
//            communicators = new HashMap<>();
//            communicators.put(Constants.VIN_REPORT, vinCommunicator);
//            communicators.put(Constants.OBLIGATIONS_REPORT, obligationsCommunicator);
//            communicators.put(Constants.TOLL_REPORT, tollCommunicator);
//        }
//    }

    public Communicator createCommunicator(AbstractRequest request) {

        return communicators.get(request.getRequestType());
    }
}
