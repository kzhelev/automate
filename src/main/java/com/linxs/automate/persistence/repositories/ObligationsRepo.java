package com.linxs.automate.persistence.repositories;

import com.linxs.automate.persistence.ObligationsReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObligationsRepo extends JpaRepository<ObligationsReportEntity, Integer> {
}
