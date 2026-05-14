package com.linxs.automate.persistence.repositories;

import com.linxs.automate.persistence.TollReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TollRepo extends JpaRepository<TollReportEntity, Integer> {
}
