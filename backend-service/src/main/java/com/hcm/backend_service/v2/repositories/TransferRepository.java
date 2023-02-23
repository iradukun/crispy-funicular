package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    public List<Transfer> getTransfersByFrom(Hospital hospital);
    public List<Transfer> getTransfersByTo(Hospital hospital);
}
