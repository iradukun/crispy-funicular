package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.CastingForm;
import com.prwebsitebackend.model.CastingFormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastingFormDataRepository extends JpaRepository<CastingFormData, Long> {
    @Query("SELECT cfd FROM CastingFormData cfd JOIN FETCH cfd.fieldValue WHERE cfd.formId = :formId")
    List<CastingFormData> findAllWithFieldValuesByFormId(@Param("formId") CastingForm formId);
    List<CastingFormData> findAllByFormId(CastingForm formId);
    @Query(value = "SELECT * FROM casting_form_data_field_value WHERE casting_form_data_id IN (:castingFormDataIds)", nativeQuery = true)
    List<Object> findByCastingFormDataIds(@Param("castingFormDataIds") List<Long> castingFormDataIds);
}
