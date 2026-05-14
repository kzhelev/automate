package com.linxs.automate;

import com.linxs.automate.models.AbstractRequest;
import com.linxs.automate.models.Communicator;
import com.linxs.automate.models.Constants;
import com.linxs.automate.models.obligations.ObligationsCommunicator;
import com.linxs.automate.models.toll.TollCommunicator;
import com.linxs.automate.models.vin.VINCommunicator;
import com.linxs.automate.services.CommunicatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunicatorFactoryTest {

    // -------------------------------------------------------------------------
    // Mocks
    // -------------------------------------------------------------------------

    @Mock private VINCommunicator         vinCommunicator;
    @Mock private ObligationsCommunicator obligationsCommunicator;
    @Mock private TollCommunicator        tollCommunicator;
    @Mock private AbstractRequest         request;

    private CommunicatorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new CommunicatorFactory(vinCommunicator, obligationsCommunicator, tollCommunicator);
    }

    // =========================================================================
    // createCommunicator – правилен тип по requestType
    // =========================================================================

    @Nested
    @DisplayName("createCommunicator – маршрутизация по requestType")
    class Routing {

        @Test
        @DisplayName("Връща VINCommunicator за VIN_REPORT")
        void returnsVinCommunicatorForVinReport() {
            when(request.getRequestType()).thenReturn(Constants.VIN_REPORT);

            Communicator result = factory.createCommunicator(request);

            assertThat(result).isInstanceOf(VINCommunicator.class);
        }

        @Test
        @DisplayName("Връща ObligationsCommunicator за OBLIGATIONS_REPORT")
        void returnsObligationsCommunicatorForObligationsReport() {
            when(request.getRequestType()).thenReturn(Constants.OBLIGATIONS_REPORT);

            Communicator result = factory.createCommunicator(request);

            assertThat(result).isInstanceOf(ObligationsCommunicator.class);
        }

        @Test
        @DisplayName("Връща TollCommunicator за TOLL_REPORT")
        void returnsTollCommunicatorForTollReport() {
            when(request.getRequestType()).thenReturn(Constants.TOLL_REPORT);

            Communicator result = factory.createCommunicator(request);

            assertThat(result).isInstanceOf(TollCommunicator.class);
        }

        @Test
        @DisplayName("Връща null за непознат requestType")
        void returnsNullForUnknownRequestType() {
            when(request.getRequestType()).thenReturn("UNKNOWN_TYPE");

            Communicator result = factory.createCommunicator(request);

            assertThat(result).isNull();
        }

        @Test
        @DisplayName("Връща null за null requestType")
        void returnsNullForNullRequestType() {
            when(request.getRequestType()).thenReturn(null);

            Communicator result = factory.createCommunicator(request);

            assertThat(result).isNull();
        }
    }

    // =========================================================================
    // setUpCommunicatorsMap – @PostConstruct идемпотентност
    // =========================================================================

    @Nested
    @DisplayName("setUpCommunicatorsMap – @PostConstruct идемпотентност")
    class PostConstruct {

        @Test
        @DisplayName("Повторно извикване не презаписва вече инициализираната map")
        void doubleCallDoesNotReinitialiseMap() {
            when(request.getRequestType()).thenReturn(Constants.VIN_REPORT);

            Communicator result = factory.createCommunicator(request);
            assertThat(result).isSameAs(vinCommunicator);
        }
    }
}