package com.linxs.automate;

import com.linxs.automate.models.AbstractReportDto;
import com.linxs.automate.persistence.AbstractReportEntity;
import com.linxs.automate.persistence.ObligationsReportEntity;
import com.linxs.automate.persistence.TollReportEntity;
import com.linxs.automate.persistence.VinReportEntity;
import com.linxs.automate.persistence.repositories.ObligationsRepo;
import com.linxs.automate.persistence.repositories.TollRepo;
import com.linxs.automate.persistence.repositories.VINReportRepo;
import com.linxs.automate.services.PersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersistenceServiceTest {

    @Mock
    private ObligationsRepo obligationsRepo;

    @Mock
    private VINReportRepo vinReportRepo;

    @Mock
    private TollRepo tollRepo;

    @InjectMocks
    private PersistenceService persistenceService;

    private ObligationsReportEntity obligationsEntity;
    private VinReportEntity vinEntity;
    private TollReportEntity tollEntity;

    @BeforeEach
    void setUp() {
        obligationsEntity = new ObligationsReportEntity();
        vinEntity         = new VinReportEntity();
        tollEntity        = new TollReportEntity();
    }

    @Nested
    @DisplayName("saveResult(AbstractReportEntity) – диспечер")
    class SaveResultDispatch {

        @Test
        @DisplayName("ObligationsReportEntity се делегира към obligationsRepo")
        void shouldDelegateToObligationsRepo() {
            ObligationsReportEntity saved = new ObligationsReportEntity();
            when(obligationsRepo.save(obligationsEntity)).thenReturn(saved);

            AbstractReportEntity result = persistenceService.saveResult(obligationsEntity);

            assertThat(result).isSameAs(saved);
            verify(obligationsRepo, times(1)).save(obligationsEntity);
            verifyNoInteractions(vinReportRepo, tollRepo);
        }

        @Test
        @DisplayName("VinReportEntity се делегира към vinReportRepo")
        void shouldDelegateToVinReportRepo() {
            VinReportEntity saved = new VinReportEntity();
            when(vinReportRepo.save(vinEntity)).thenReturn(saved);

            AbstractReportEntity result = persistenceService.saveResult(vinEntity);

            assertThat(result).isSameAs(saved);
            verify(vinReportRepo, times(1)).save(vinEntity);
            verifyNoInteractions(obligationsRepo, tollRepo);
        }

        @Test
        @DisplayName("TollReportEntity се делегира към tollRepo")
        void shouldDelegateToTollRepo() {
            TollReportEntity saved = new TollReportEntity();
            when(tollRepo.save(tollEntity)).thenReturn(saved);

            AbstractReportEntity result = persistenceService.saveResult(tollEntity);

            assertThat(result).isSameAs(saved);
            verify(tollRepo, times(1)).save(tollEntity);
            verifyNoInteractions(obligationsRepo, vinReportRepo);
        }

        @Test
        @DisplayName("Непознат подтип връща null без да вика никое хранилище")
        void shouldReturnNullForUnknownSubtype() {
            AbstractReportEntity unknown = new AbstractReportEntity() {
                @Override
                public AbstractReportDto toReportDto() {
                    return null;
                }
            };

            AbstractReportEntity result = persistenceService.saveResult(unknown);

            assertThat(result).isNull();
            verifyNoInteractions(obligationsRepo, vinReportRepo, tollRepo);
        }
    }

    @Nested
    @DisplayName("Върнатата стойност")
    class ReturnValue {

        @Test
        @DisplayName("Връща точно обекта, върнат от obligationsRepo")
        void returnsEntityFromObligationsRepo() {
            ObligationsReportEntity fromDb = new ObligationsReportEntity();
            when(obligationsRepo.save(any())).thenReturn(fromDb);

            AbstractReportEntity result = persistenceService.saveResult(obligationsEntity);

            assertThat(result).isInstanceOf(ObligationsReportEntity.class)
                    .isSameAs(fromDb);
        }

        @Test
        @DisplayName("Връща точно обекта, върнат от vinReportRepo")
        void returnsEntityFromVinRepo() {
            VinReportEntity fromDb = new VinReportEntity();
            when(vinReportRepo.save(any())).thenReturn(fromDb);

            AbstractReportEntity result = persistenceService.saveResult(vinEntity);

            assertThat(result).isInstanceOf(VinReportEntity.class)
                    .isSameAs(fromDb);
        }

        @Test
        @DisplayName("Връща точно обекта, върнат от tollRepo")
        void returnsEntityFromTollRepo() {
            TollReportEntity fromDb = new TollReportEntity();
            when(tollRepo.save(any())).thenReturn(fromDb);

            AbstractReportEntity result = persistenceService.saveResult(tollEntity);

            assertThat(result).isInstanceOf(TollReportEntity.class)
                    .isSameAs(fromDb);
        }
    }

    @Nested
    @DisplayName("Изолация на хранилищата")
    class RepositoryIsolation {

        @Test
        @DisplayName("При запис на Obligations – VIN и Toll хранилищата не се докосват")
        void obligationsSaveDoesNotTouchOtherRepos() {
            when(obligationsRepo.save(any())).thenReturn(obligationsEntity);

            persistenceService.saveResult(obligationsEntity);

            verifyNoInteractions(vinReportRepo, tollRepo);
        }

        @Test
        @DisplayName("При запис на VIN – Obligations и Toll хранилищата не се докосват")
        void vinSaveDoesNotTouchOtherRepos() {
            when(vinReportRepo.save(any())).thenReturn(vinEntity);

            persistenceService.saveResult(vinEntity);

            verifyNoInteractions(obligationsRepo, tollRepo);
        }

        @Test
        @DisplayName("При запис на Toll – Obligations и VIN хранилищата не се докосват")
        void tollSaveDoesNotTouchOtherRepos() {
            when(tollRepo.save(any())).thenReturn(tollEntity);

            persistenceService.saveResult(tollEntity);

            verifyNoInteractions(obligationsRepo, vinReportRepo);
        }
    }
}