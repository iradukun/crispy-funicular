package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.BookingFee;
import com.hcm.backend_service.v2.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingFeeRepository extends JpaRepository<BookingFee, UUID> {
    List<BookingFee> findByHospital(Hospital hospital);
}
