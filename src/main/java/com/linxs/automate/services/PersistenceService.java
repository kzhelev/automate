package com.linxs.automate.services;

import com.linxs.automate.persistence.AbstractReportEntity;
import com.linxs.automate.persistence.ObligationsReportEntity;
import com.linxs.automate.persistence.TollReportEntity;
import com.linxs.automate.persistence.VinReportEntity;
import com.linxs.automate.persistence.repositories.ObligationsRepo;
import com.linxs.automate.persistence.repositories.TollRepo;
import com.linxs.automate.persistence.repositories.VINReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {

    private ObligationsRepo obligationsRepo;
    private VINReportRepo vinReportRepo;
    private TollRepo tollRepo;

    @Autowired
    public PersistenceService(ObligationsRepo obligationsRepo, VINReportRepo vinReportRepo, TollRepo tollRepo) {
        this.obligationsRepo = obligationsRepo;
        this.vinReportRepo = vinReportRepo;
        this.tollRepo = tollRepo;
    }

    public AbstractReportEntity saveResult(AbstractReportEntity abstractReport) {

        AbstractReportEntity report = null;

        if (abstractReport instanceof ObligationsReportEntity obligationsReportEntity) {
            report = saveResult(obligationsReportEntity);
        } else if (abstractReport instanceof VinReportEntity vinReportEntity) {
            report = saveResult(vinReportEntity);
        } else if (abstractReport instanceof TollReportEntity tollReportEntity) {
            report = saveResult(tollReportEntity);
        }

        return report;
    }

    private VinReportEntity saveResult(VinReportEntity result) {

        VinReportEntity savedReport = vinReportRepo.save(result);

        return savedReport;
    }

    private ObligationsReportEntity saveResult(ObligationsReportEntity result) {

        ObligationsReportEntity savedReport = obligationsRepo.save(result);

        return savedReport;
    }

    private TollReportEntity saveResult(TollReportEntity result) {

        TollReportEntity savedReport = tollRepo.save(result);

        return savedReport;
    }
}
