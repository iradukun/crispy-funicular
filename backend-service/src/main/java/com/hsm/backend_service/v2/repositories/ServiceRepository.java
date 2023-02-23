package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    public Service findByService(String service);
    @Query(value = "SELECT s FROM Service s WHERE s.service_id IN :ids")

    List<Service> findByService_idIn(List<UUID> ids);
}
