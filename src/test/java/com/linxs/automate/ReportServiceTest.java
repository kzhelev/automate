package com.linxs.automate;

import com.linxs.automate.errors.ApplicationException;
import com.linxs.automate.models.*;
import com.linxs.automate.persistence.AbstractReportEntity;
import com.linxs.automate.services.CommunicatorFactory;
import com.linxs.automate.services.PersistenceService;
import com.linxs.automate.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock private CommunicatorFactory communicatorFactory;
    @Mock private PersistenceService persistenceService;

    @InjectMocks
    private ReportService reportService;

    // ═══════════════════════════════════════════════════════════════════════
    //  processRequest(AbstractRequest) – single request
    // ═══════════════════════════════════════════════════════════════════════

    @Nested
    @DisplayName("processRequest(AbstractRequest)")
    class SingleRequestTests {

        @Mock private Communicator communicator;
        @Mock private AbstractRequest request;
        @Mock private AbstractCommunicatorResult communicatorResult;
        @Mock private AbstractReportEntity reportEntity;

        @BeforeEach
        void setUp() {
            when(request.getRequestType()).thenReturn(Constants.VIN_REPORT);
        }

        @Test
        @DisplayName("връща error DTO когато factory върне null communicator")
        void nullCommunicator_returnsErrorDto() {
            when(communicatorFactory.createCommunicator(request)).thenReturn(null);

            AbstractReportDto result = reportService.processRequest(request);

            assertThat(result.getErrorCode()).isEqualTo(Constants.INTERNAL_SERVER_ERROR);
            assertThat(result.getErrorMessage()).isEqualTo(Constants.INTERNAL_SERVER_ERROR_MSG);
            assertThat(result.getReportType()).isEqualTo(Constants.VIN_REPORT);
            verifyNoInteractions(persistenceService);
        }

        @Test
        @DisplayName("връща error DTO когато communicator хвърли ApplicationException")
        void communicatorThrows_returnsErrorDto() throws ApplicationException {
            when(communicatorFactory.createCommunicator(request)).thenReturn(communicator);
            when(communicator.getData(request))
                    .thenThrow(new ApplicationException(400, "Bad Request", "getData failed"));

            AbstractReportDto result = reportService.processRequest(request);

            assertThat(result.getErrorCode()).isEqualTo(400);
            assertThat(result.getErrorMessage()).isEqualTo("Bad Request");
            assertThat(result.getReportType()).isEqualTo(Constants.VIN_REPORT);
            verifyNoInteractions(persistenceService);
        }

        @Test
        @DisplayName("записва резултата и връща попълнен DTO при успешен път")
        void happyPath_persistsAndReturnsDto() throws ApplicationException {
            AbstractReportDto expectedDto = new AbstractReportDto();
            expectedDto.setReportType(Constants.VIN_REPORT);

            when(communicatorFactory.createCommunicator(request)).thenReturn(communicator);
            when(communicator.getData(request)).thenReturn(communicatorResult);
            when(communicatorResult.toEntity(request)).thenReturn(reportEntity);
            when(persistenceService.saveResult(reportEntity)).thenReturn(reportEntity);
            when(reportEntity.toReportDto()).thenReturn(expectedDto);

            AbstractReportDto result = reportService.processRequest(request);

            verify(persistenceService).saveResult(reportEntity);
            assertThat(result.getReportType()).isEqualTo(Constants.VIN_REPORT);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  processRequest(List<AbstractRequest>) – batch
    // ═══════════════════════════════════════════════════════════════════════

    @Nested
    @DisplayName("processRequest(List<AbstractRequest>)")
    class BatchRequestTests {

        @Test
        @DisplayName("VIN заявка → попълва vinReport в Response")
        void vinRequest_populatesVinReport() throws ApplicationException {
            AbstractRequest vinRequest = stubHappyPath(Constants.VIN_REPORT);

            Response response = reportService.processRequest(List.of(vinRequest));

            assertThat(response.getVinReport()).isNotNull();
            assertThat(response.getVinReport().getReportType()).isEqualTo(Constants.VIN_REPORT);
            assertThat(response.getTrafficFinesReport()).isNull();
            assertThat(response.getTollReport()).isNull();
        }

        @Test
        @DisplayName("OBLIGATIONS заявка → попълва trafficFinesReport в Response")
        void obligationsRequest_populatesTrafficFinesReport() throws ApplicationException {
            AbstractRequest obligationsRequest = stubHappyPath(Constants.OBLIGATIONS_REPORT);

            Response response = reportService.processRequest(List.of(obligationsRequest));

            assertThat(response.getTrafficFinesReport()).isNotNull();
            assertThat(response.getTrafficFinesReport().getReportType()).isEqualTo(Constants.OBLIGATIONS_REPORT);
            assertThat(response.getVinReport()).isNull();
            assertThat(response.getTollReport()).isNull();
        }

        @Test
        @DisplayName("TOLL заявка → попълва tollReport в Response")
        void tollRequest_populatesTollReport() throws ApplicationException {
            AbstractRequest tollRequest = stubHappyPath(Constants.TOLL_REPORT);

            Response response = reportService.processRequest(List.of(tollRequest));

            assertThat(response.getTollReport()).isNotNull();
            assertThat(response.getTollReport().getReportType()).isEqualTo(Constants.TOLL_REPORT);
            assertThat(response.getVinReport()).isNull();
            assertThat(response.getTrafficFinesReport()).isNull();
        }

        @Test
        @DisplayName("три заявки наведнъж → попълва всички полета в Response")
        void allThreeRequests_populatesAllReports() throws ApplicationException {
            AbstractRequest vinRequest         = stubHappyPath(Constants.VIN_REPORT);
            AbstractRequest obligationsRequest = stubHappyPath(Constants.OBLIGATIONS_REPORT);
            AbstractRequest tollRequest        = stubHappyPath(Constants.TOLL_REPORT);

            Response response = reportService.processRequest(
                    List.of(vinRequest, obligationsRequest, tollRequest));

            assertThat(response.getVinReport()).isNotNull();
            assertThat(response.getTrafficFinesReport()).isNotNull();
            assertThat(response.getTollReport()).isNotNull();
        }

        @Test
        @DisplayName("null communicator за VIN → vinReport съдържа error DTO")
        void nullCommunicatorForVin_vinReportContainsErrorDto() {
            AbstractRequest vinRequest = mock(AbstractRequest.class);
            when(vinRequest.getRequestType()).thenReturn(Constants.VIN_REPORT);
            when(communicatorFactory.createCommunicator(vinRequest)).thenReturn(null);

            Response response = reportService.processRequest(List.of(vinRequest));

            AbstractReportDto vinReport = response.getVinReport();
            assertThat(vinReport.getErrorCode()).isEqualTo(Constants.INTERNAL_SERVER_ERROR);
            assertThat(vinReport.getErrorMessage()).isEqualTo(Constants.INTERNAL_SERVER_ERROR_MSG);
        }

        @Test
        @DisplayName("ApplicationException за TOLL → tollReport е error, vinReport е ОК")
        void applicationExceptionForToll_onlyTollReportIsError() throws ApplicationException {
            AbstractRequest vinRequest  = stubHappyPath(Constants.VIN_REPORT);
            AbstractRequest tollRequest = mock(AbstractRequest.class);

            when(tollRequest.getRequestType()).thenReturn(Constants.TOLL_REPORT);
            Communicator failingComm = mock(Communicator.class);
            when(communicatorFactory.createCommunicator(tollRequest)).thenReturn(failingComm);
            when(failingComm.getData(tollRequest))
                    .thenThrow(new ApplicationException(503, "Service Unavailable", "connection refused"));

            Response response = reportService.processRequest(List.of(vinRequest, tollRequest));

            assertThat(response.getVinReport()).isNotNull();

            AbstractReportDto tollReport = response.getTollReport();
            assertThat(tollReport.getErrorCode()).isEqualTo(503);
            assertThat(tollReport.getErrorMessage()).isEqualTo("Service Unavailable");
        }

        @Test
        @DisplayName("празен списък → Response с null полета")
        void emptyList_responseWithNullFields() {
            Response response = reportService.processRequest(List.of());

            assertThat(response).isNotNull();
            assertThat(response.getVinReport()).isNull();
            assertThat(response.getTrafficFinesReport()).isNull();
            assertThat(response.getTollReport()).isNull();
        }

        // ── helper ──────────────────────────────────────────────────────────

        private AbstractRequest stubHappyPath(String reportType) throws ApplicationException {
            AbstractRequest req            = mock(AbstractRequest.class);
            Communicator comm              = mock(Communicator.class);
            AbstractCommunicatorResult res = mock(AbstractCommunicatorResult.class);
            AbstractReportEntity entity    = mock(AbstractReportEntity.class);

            AbstractReportDto dto = new AbstractReportDto();
            dto.setReportType(reportType);

            when(req.getRequestType()).thenReturn(reportType);
            when(communicatorFactory.createCommunicator(req)).thenReturn(comm);
            when(comm.getData(req)).thenReturn(res);
            when(res.toEntity(req)).thenReturn(entity);
            when(persistenceService.saveResult(entity)).thenReturn(entity);
            when(entity.toReportDto()).thenReturn(dto);

            return req;
        }
    }
}