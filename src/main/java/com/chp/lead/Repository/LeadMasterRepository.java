package com.chp.lead.Repository;

import com.chp.lead.Entity.LeadMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LeadMasterRepository extends JpaRepository<LeadMaster, UUID> {
}
